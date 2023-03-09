import React from "react";
import { Text, SafeAreaView, ImageBackground, StyleSheet, Dimensions, TextInput, Pressable } from "react-native";
import { KeyboardAwareScrollView } from "react-native-keyboard-aware-scroll-view";
import { Button } from "react-native-paper";

function SignIn({navigation}){
    const [email_icon,setEmailIcon] = React.useState('email')
    const [pass_icon,setPassIcon] = React.useState('lock')
    return (
        <SafeAreaView>
            <KeyboardAwareScrollView>
            <ImageBackground source={require('../assets/bg.png')} style = {styles.bg}>
                
                <Text style = {styles.title}>
                    PBUH
                </Text>
                <TextInput 
                placeholder= "Email Address"
                placeholderTextColor='white'
                textAlign="center"
                selectionColor= '#f9b410'
                underlineColorAndroid='#f9b410'
                keyboardType="email-address"
                inlineImageLeft = {email_icon}
                inlineImagePadding = {-10}
                style = {styles.input}
                onFocus = {()=>{
                    setEmailIcon('email_focused')
                }}
                onBlur = {()=>{
                    setEmailIcon('email')
                }}
                />
                <TextInput 
                placeholder = "Password"
                placeholderTextColor='white'
                textAlign="center"
                selectionColor= '#f9b410'
                underlineColorAndroid='#f9b410'
                secureTextEntry
                inlineImageLeft = {pass_icon}
                inlineImagePadding = {-10}
                style = {styles.input}
                onFocus = {()=>{
                    setPassIcon('lock_focused')
                }}
                onBlur = {()=>{
                    setPassIcon('lock')
                }}
                />
                <Text style = {styles.forgot}>
                    Forgot Password?
                </Text>
                <Button 
                mode="contained"
                buttonColor="white"
                textColor="#273c86"
                style = {styles.login}
                >
                    Log in
                </Button>
                <Pressable onPress={() => {
                    navigation.navigate("SignUp")
                }}>
                    <Text style = {styles.signup}>
                        Don't have an account? Sign Up!
                    </Text>
                </Pressable>
                
            </ImageBackground>
            </KeyboardAwareScrollView>
        </SafeAreaView>
    )
    
    
};
const styles = StyleSheet.create({
    bg:
    {
        width:Dimensions.get('window').width,
        height:Dimensions.get('window').height,
        justifyContent:'center',
        alignItems:'center'
    },
    title:{
        textAlign:'center',
        color:'#f9b410',
        fontSize:Dimensions.get('window').width*.2,
        fontWeight:'bold'

    },
    input:{
        width:Dimensions.get('window').width*.7,
        height:Dimensions.get('window').height*.07,
        // backgroundColor:'white',
        textAlign:'center',
        marginTop:10,
        color:'white',
        // borderColor: '#f9b410', 
        // borderWidth: 3,
        // borderRadius:20
    },
    forgot:{
        marginTop:10,
        color:'white',
        fontSize:Dimensions.get('window').width*.05
    },
    login:{
        marginTop:10
    },
    signup:{
        marginTop:10,
        color:'white',
        fontSize:Dimensions.get('window').width*.04
    }

});

export default SignIn;