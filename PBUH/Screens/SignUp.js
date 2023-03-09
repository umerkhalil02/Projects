import React from "react";
import { Text,SafeAreaView, ImageBackground, StyleSheet,Dimensions, TextInput, Pressable } from "react-native";
import { KeyboardAwareScrollView } from "react-native-keyboard-aware-scroll-view";
import { Button } from "react-native-paper";

function SignUp({navigation}){
    const [name_icon,setNameIcon] = React.useState('name')
    const [email_icon,setEmailIcon] = React.useState('email')
    const [pass_icon,setPassIcon] = React.useState('lock')
    const [confirm_icon,setConfirmIcon] = React.useState('lock')
    return (
        <SafeAreaView>
            <KeyboardAwareScrollView>
            <ImageBackground source={require('../assets/bg.png')} style = {styles.bg}>
                
                <Text style = {styles.title}>
                    PBUH
                </Text>
                <TextInput 
                placeholder= "Full Name"
                placeholderTextColor='white'
                textAlign="center"
                selectionColor= '#f9b410'
                keyboardType="email-address"
                underlineColorAndroid='#F9B410'
                inlineImageLeft = {name_icon}
                inlineImagePadding = {-10}
                style = {styles.input}
                onFocus = {()=>{
                    setNameIcon('name_focused')
                }}
                onBlur = {()=>{
                    setNameIcon('name')
                }}
                />
                <TextInput 
                placeholder= "Email Address"
                placeholderTextColor='white'
                textAlign="center"
                selectionColor= '#f9b410'
                keyboardType="email-address"
                underlineColorAndroid='#F9B410'
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
                secureTextEntry
                underlineColorAndroid='#F9B410'
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
                <TextInput 
                placeholder = "Confirm Password"
                placeholderTextColor='white'
                textAlign="center"
                selectionColor= '#f9b410'
                underlineColorAndroid='#F9B410'
                secureTextEntry
                inlineImageLeft = {confirm_icon}
                inlineImagePadding = {-10}
                style = {styles.input}
                onFocus = {()=>{
                    setConfirmIcon('lock_focused')
                }}
                onBlur = {()=>{
                    setPassIcon('lock')
                }}
                />
                <Button 
                mode="contained"
                buttonColor="white"
                textColor="#273c86"
                style = {styles.login}
            
                >
                    Sign up
                </Button>
                <Pressable onPress={()=> {
                    navigation.navigate("SignIn")
                }}>
                    <Text style = {styles.signin}>
                        Already have an account? Sign In!
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
        borderRadius:20
    },
    login:{
        marginTop:10
    },
    signin:{
        marginTop:10,
        color:'white',
        fontSize:Dimensions.get('window').width*.04
    }

});

export default SignUp;