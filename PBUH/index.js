/**
 * @format
 */

import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';
import Splash from './Screens/Splash';
import SignIn from './Screens/SignIn';
import SignUp from './Screens/SignUp';

const Stack = createNativeStackNavigator();

function MyStack (){
    return (
        <NavigationContainer>
            <Stack.Navigator>
                <Stack.Screen name='Splash' component={Splash} options = {{ headerShown:false }} />
                <Stack.Screen name='SignIn' component={SignIn} options = {{ headerShown:false }} />
                <Stack.Screen name='SignUp' component={SignUp} options = {{ headerShown:false }} />
            </Stack.Navigator>
        </NavigationContainer>
    )
    
}

AppRegistry.registerComponent(appName, () => MyStack);
