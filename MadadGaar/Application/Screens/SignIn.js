import * as React from "react";
import { TextInput,StyleSheet,Text,Pressable,ImageBackground,SafeAreaView, KeyboardAvoidingView} from "react-native";
import { useState } from "react";

const SignIn = ({navigation}) => {
  const [frameTextInput, setFrameTextInput] = useState("string");

  return (
    <SafeAreaView style={{flex:1}}>
      <KeyboardAvoidingView style={styles.SafeAreaView}>
      <TextInput
        style={styles.frameTextInput}
        placeholder="Email Address"
        keyboardType="email-address"
        onChangeText={setFrameTextInput}
        placeholderTextColor="#7c7b87"
      />
      <TextInput
        style={styles.Password}
        placeholder="Password"
        keyboardType="default"
        autoCapitalize="none"
        secureTextEntry
        placeholderTextColor="#7c7b87"
      />
      <Pressable
        style={styles.SignUp}
        onPress={() => navigation.navigate("SignUp")}
      >
        <Text style={styles.SignUp_Text}>
          Don’t have an account? Tap here to sign up!
        </Text>
      </Pressable>
      <Pressable
        style={styles.SignIn_Button}
        onPress={() => navigation.navigate("Maps")}
      >
        <Text style={styles.signIn}>Sign In</Text>
      </Pressable>
      <ImageBackground
        style={styles.bg}
        resizeMode="cover"
        source={require("../assets/bg.jpg")}
      />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  frameTextInput: {
    position: "absolute",
    textAlign:'center',
    top: 413,
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
    textAlign:'center',
    top: 469,
    borderRadius: 10,
    backgroundColor: "#fff",
    borderStyle: "solid",
    borderColor: "#d2d2d2",
    borderWidth: 1,
    width: 343,
    height: 46,
    overflow: "hidden",
  },
  SignUp_Text: {
    fontSize: 16,
    lineHeight: 28,
    color: "#343454",
    textAlign: "center",
  },
  SignUp: {
    position: "absolute",
    top: 541,
  },
  signIn: {
    position: "absolute",
    alignItems:'center',
    justifyContent:'center',
    fontSize: 18,
    lineHeight: 28,
    color: "#fff",
    textAlign: "center",
    zIndex: 0,
  },
  SignIn_Button: {
    position: "absolute",
    top: 595,
    alignItems:'center',
    justifyContent:'center',
    borderRadius: 25,
    backgroundColor: "#343454",
    borderStyle: "solid",
    borderColor: "#000",
    borderWidth: 1,
    width: 97,
    height: 55,
    flexDirection: "row",
    paddingHorizontal: 10,
    paddingBottom: 10,
  },
  bg: {
    position: "absolute",
    top: 239,
    width: 318,
    height: 148,
  },
  SafeAreaView: {
    position: "relative",
    backgroundColor: "#7c7b87",
    flex: 1,
    width: "100%",
    height: 932,
    overflow: "hidden",
    alignItems:'center',
    justifyContent:'center'
  },
});

export default SignIn;
