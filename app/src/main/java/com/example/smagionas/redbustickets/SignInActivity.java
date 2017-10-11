package com.example.smagionas.redbustickets;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity
{

    public TextView displayed_code;
    public String code_to_display="";
    public String encrypted_code_to_display = "";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        displayed_code = findViewById(R.id.numpad_screen);
        displayed_code.setTextSize(TypedValue.COMPLEX_UNIT_PX,45);

    }

    @Override
    public void onResume()
    {
        super.onResume();
        //displayed_code.setText("");
    }

    public void OnButtonPressed_0(View view)
    {
        code_to_display = code_to_display+"0";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_1(View view)
    {
        code_to_display = code_to_display+"1";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_2(View view)
    {
        code_to_display = code_to_display+"2";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_3(View view)
    {
        code_to_display = code_to_display+"3";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_4(View view)
    {
        code_to_display = code_to_display+"4";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_5(View view)
    {
        code_to_display = code_to_display+"5";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_6(View view)
    {
        code_to_display = code_to_display+"6";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_7(View view)
    {
        code_to_display = code_to_display+"7";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_8(View view)
    {
        code_to_display = code_to_display+"8";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_9(View view)
    {
        code_to_display = code_to_display+"9";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_clear(View view)
    {
        code_to_display = "";
        encrypted_code_to_display = "";
        displayed_code.setText(encrypted_code_to_display);
    }


    public void OnButtonPressed_Backspace(View view)
    {
        if (code_to_display != null && code_to_display.length() > 0 )
        {
            code_to_display = code_to_display.substring(0, code_to_display.length() - 1);
            encrypted_code_to_display = encrypted_code_to_display.substring(0, encrypted_code_to_display.length() - 1);
        }
        displayed_code.setText(encrypted_code_to_display);
    }


    public void OnButtonPressed_settings(View view)
    {
        displayed_code.setText(code_to_display);
    }


    public void OnButtonPressed_OK(View view)
    {



        if(code_to_display.equals("12345")){                                                        // TODO implement sign in logic

            encrypted_code_to_display="";
            code_to_display="";
            displayed_code.setText("");
            Intent intent = new Intent(this,RoutesActivity.class);
            startActivity(intent);


        }else{

            encrypted_code_to_display="";
            code_to_display="";
            displayed_code.setText("");

            Toast.makeText(getApplicationContext(), "Wrong Password. Please try again..", Toast.LENGTH_LONG).show();



        }


    }


    @Override
    public void onBackPressed() {

        // Do nothing. After leaving the activity responsible for the initial device setup, the user cannot return to that activity

    }



}