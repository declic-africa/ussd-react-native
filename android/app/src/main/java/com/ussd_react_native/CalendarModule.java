package com.ussd_react_native;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.Map;
import java.util.HashMap;

public class CalendarModule extends ReactContextBaseJavaModule{

    CalendarModule(ReactApplicationContext context){
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "CalendarModule";
    }

    public void createCalendarEvent(String name, String location, Promise promise){
        Log.d("CalendarModule", "Create event called with name: "+ name +" and location: "+location);
        try{
            Integer eventID=10;
            promise.resolve(eventID);
        }catch (Exception e){
            promise.reject("Create Event Error", e);
        }
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String,Object> constants= new HashMap<>();
        constants.put("DEFAULT_EVENT_NAME", "new Event");
        return constants;
    }

    @ReactMethod
    public void  Hello(){
        Toast.makeText(getReactApplicationContext(),"Hello world",Toast.LENGTH_SHORT).show();
    }

    private void sendEvent(ReactContext context, String eventName, WritableMap params){
        context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName,params);
    }


}