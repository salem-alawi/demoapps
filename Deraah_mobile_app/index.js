import * as React from 'react';
import { AppRegistry, View } from 'react-native';
import { Provider as PaperProvider } from 'react-native-paper';
import { name as appName } from './app.json';
import App from './App';
import { I18nManager } from "react-native";
import RNRestart from "react-native-restart";

const isRTL = I18nManager.isRTL;
if(!isRTL){
  I18nManager.forceRTL(true);
  RNRestart.Restart();
}

export default function Main() {
  return (
    <PaperProvider>
      {/* <App /> */}
    <App />
   </PaperProvider>
  );
}

AppRegistry.registerComponent(appName, () => Main);