import * as React from "react";
import {TextInput,StyleSheet,Text,Pressable,Image,SafeAreaView, KeyboardAvoidingView} from "react-native";
import { useState } from "react";



const SignUp = ({navigation}) => {
  return (
    <SafeAreaView style={styles.SafeAreaView}>
        <Image
        style={styles.Img}
        resizeMode="cover"
        source={require("../assets/logo.png")} />
        <TextInput
        style={styles.frameTextInput}
        placeholder="Phone Number"
        keyboardType="number-pad"
        placeholderTextColor="#7c7b87" />
        <TextInput
            style={styles.FullName}
            placeholder="Full Name"
            keyboardType="default"
            placeholderTextColor="#7c7b87" />
        <TextInput
            style={styles.Email}
            placeholder="Email Address"
            keyboardType="email-address"
            placeholderTextColor="#7c7b87" />
        <TextInput
            style={styles.Password}
            placeholder="Password"
            keyboardType="default"
            secureTextEntry
            placeholderTextColor="#7c7b87"  />
        <TextInput
            style={styles.frameTextInput4}
            placeholder="Confirm Password"
            keyboardType="default"
            placeholderTextColor="#7c7b87" />
        <Pressable
            style={styles.SignIn}
            onPress={() => navigation.navigate("SignIn")} >
            <Text style={styles.logIn}>
            Already have an account? Tap here to sign in!
            </Text>
        </Pressable>
        
        <Pressable
            style={styles.SignUp_Button}
            onPress={() => navigation.navigate("Maps")} >
            <Text style={styles.signUp}>Sign Up</Text>
        </Pressable>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  frameTextInput: {
    position: "absolute",
    top: 362,
    textAlign:'center',
    borderRadius: 10,
    backgroundColor: "#fff",
    borderStyle: "solid",
    borderColor: "#d2d2d2",
    borderWidth: 1,
    width: 343,
    height: 46,
    overflow: "hidden",
  },
  FullName: {
    position: "absolute",
    top: 308,
    textAlign:'center',
    borderRadius: 10,
    backgroundColor: "#fff",
    borderStyle: "solid",
    borderColor: "#d2d2d2",
    borderWidth: 1,
    width: 343,
    height: 46,
    overflow: "hidden",
  },
  Email: {
    position: "absolute",
    top: 416,
    textAlign:'center',
    borderRadius: 10,
    backgroundColor: "#fff",
    borderStyle: "solid",
    borderColor: "#d2d2d2",
    borderWidth: 1,
    width: 343,
    height: 46,
    overflow: "hidden",
  },
  Password: {
    position: "absolute",
    top: 470,
    textAlign:'center',
    borderRadius: 10,
    backgroundColor: "#fff",
    borderStyle: "solid",
    borderColor: "#d2d2d2",
    borderWidth: 1,
    width: 343,
    height: 46,
    overflow: "hidden",
  },
  frameTextInput4: {
    position: "absolute",
    top: 524,
    textAlign:'center',
    borderRadius: 10,
    backgroundColor: "#fff",
    borderStyle: "solid",
    borderColor: "#d2d2d2",
    borderWidth: 1,
    width: 343,
    height: 46,
    overflow: "hidden",
  },
  logIn: {
    position: "relative",
    fontSize: 16,
    lineHeight: 28,
    color: "#343454",
    textAlign: "center",
  },
  SignIn: {
    position: "absolute",
    top: 591,
    flexDirection: "row",
    padding: 10,
    alignItems: "center",
    justifyContent: "center",
  },
  Img: {
    position: "absolute",
    top: 129,
    width: 318,
    height: 148,
  },
  signUp: {
    position: "absolute",
    fontSize: 18,
    color: "#fff",
    textAlign: "center",
    justifyContent:'center',
    zIndex: 0,
  },
  SignUp_Button: {
    position: "absolute",
    top: 660,
    borderRadius: 25,
    backgroundColor: "#343454",
    borderStyle: "solid",
    borderColor: "#000",
    borderWidth: 1,
    width: 97,
    height: 55,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
  },
  SafeAreaView: {
    position: "relative",
    backgroundColor: "#7c7b87",
    flex: 1,
    width: "100%",
    overflow: "hidden",
    alignItems:'center',
    justifyContent:'center',
    paddingBottom:100
  },
});

export default SignUp;
