package com.chaitanyakusurkar.facebooklogin;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    LoginButton fbLoginButton;
    TextView emailTextView,passwordTextView;
    Button signInButton;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // printKeyHash();
        init();
        login();
    }
    public void init(){
        fbLoginButton=findViewById(R.id.fb_login_button);
        emailTextView=findViewById(R.id.emailTextView);
        passwordTextView=findViewById(R.id.passwordTextView);
        signInButton=findViewById(R.id.signInButton);
    }
    public void login(){
        callbackManager=CallbackManager.Factory.create();
        fbLoginButton.setReadPermissions(Arrays.asList(EMAIL));
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goToWelcomePage();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"Login Cancelled",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),"Login Error: "+error,Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public void goToWelcomePage(){
        Intent intent=new Intent(MainActivity.this,WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    /*public void printKeyHash(){
        try{
            PackageInfo info= getPackageManager().getPackageInfo("com.chaitanyakusurkar.facebooklogin", PackageManager.GET_SIGNATURES);
            for(Signature signature:info.signatures){
                MessageDigest md=MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }*/
}
