/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  Text,
  StatusBar,
} from 'react-native';
import NewModuleButton from './src/components/NewModuleButton';
import SendMoney from "./src/components/SendMoney";

const App: () => React$Node = () => {
  return (
    <>
      <View
        style={{
          flex: 1,
          justifyContent: 'center',
        }}>
        <SendMoney/>
      </View>
    </>
  );
};

export default App;
