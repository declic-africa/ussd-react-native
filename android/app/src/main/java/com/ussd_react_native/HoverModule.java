package com.ussd_react_native;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.hover.sdk.api.Hover;
import com.hover.sdk.api.HoverParameters;

import java.lang.reflect.Array;

import javax.annotation.RegEx;

public class HoverModule extends ReactContextBaseJavaModule {

    private Promise activityPromise;


    @NonNull
    @Override
    public String getName() {
        return "HoverModule";
    }

    HoverModule(ReactApplicationContext context){
        super(context);
        ActivityEventListener activityEventListener = new BaseActivityEventListener() {
            @Override
            public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
                super.onActivityResult(activity, requestCode, resultCode, data);
                if (requestCode == 1) {
                    if (activityPromise != null) {
                        if (resultCode == Activity.RESULT_OK) {
                            StringBuilder message = new StringBuilder();
                            String[] sessionTextArr = data.getStringArrayExtra("session_messages");
                            if (sessionTextArr != null) {
                                for (String me : sessionTextArr) {
                                    message.append(me);
                                }

                                activityPromise.resolve(message);
                            }
                        } else if (resultCode == Activity.RESULT_CANCELED) {
                            activityPromise.resolve("Canceled");
                            if (data != null) {
                                Log.d("MainActivity", "Error: " + data.getStringExtra("error"));
                            }
                        }
                    }
                }
            }

        };
        context.addActivityEventListener(activityEventListener);
    }

    @ReactMethod
    public void CheckMoney(final Promise promise){
        Activity currentActivity= getCurrentActivity();
        assert currentActivity!=null;
        activityPromise=promise;
        try{
            Hover.initialize(getReactApplicationContext());
            Log.d("MainActivity", "Sims are= " + Hover.getPresentSims(getReactApplicationContext()));
            Log.d("MainActivity", "Hover actions are=  " + Hover.getAllValidActions(getReactApplicationContext()));

            Intent i = new HoverParameters.Builder(getReactApplicationContext())
                    .request("c98397c9")
                    .setHeader("Transaction en cours")
                    .initialProcessingMessage("Veuillez patientez...")
                    .buildIntent();

            currentActivity.startActivityForResult(i,1);

        }catch (Exception e){
            activityPromise.resolve("Failed");
            Log.d("MainActivity", "hiver exception", e);
            activityPromise=null;
        }
    }


}
