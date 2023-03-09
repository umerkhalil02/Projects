import * as React from 'react';

import { Dimensions, ImageBackground,StyleSheet,Button } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

function Splash({navigation}){
    setTimeout(() => {
        navigation.navigate("SignIn")
    }, 1000);
    return (
        <SafeAreaView >
            <ImageBackground source={require('../assets/Splash.png')} style = {styles.bg} >
                {/* <Button 
                title='Press'
                 onPress={()=>{ navigation.navigate("SignUp")}}
                 /> */}
            </ImageBackground>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    bg:
    {
        width:Dimensions.get('window').width,
        height:Dimensions.get('window').height,

    }
});

export default Splash;