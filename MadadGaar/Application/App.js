import * as React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import SignUp from './Screens/SignUp';
import SignIn from './Screens/SignIn';
import { ImageBackground } from 'react-native';

const Stack = createNativeStackNavigator();


function SplashScreen({navigation}){
  setTimeout(() =>{
    navigation.replace("SignIn");
  },3000);
  return(
    <ImageBackground style={{flex:1}} source = {require('./assets/Splash.jpg')}/>
  );
}

const MyStack = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name = "Splash" component={SplashScreen} options={{headerShown:false}}/>
        <Stack.Screen name="SignIn" component={SignIn} options={{title: 'Sign In'}} options ={{headerShown:false}}/>
        <Stack.Screen name="SignUp" component={SignUp} options={{title:'Sign Up'}} options={{headerShown:false}} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};
export default MyStack;