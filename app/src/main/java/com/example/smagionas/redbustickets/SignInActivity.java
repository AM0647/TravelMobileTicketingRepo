package com.example.smagionas.redbustickets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class SignInActivity extends AppCompatActivity
{

//////////////////// START OF GLOBAL VARIABLES/////////////////////////
    public TextView displayed_code;
    RealmConfiguration config;
    Realm realm;


    public String code_to_display="";
    public String encrypted_code_to_display = "";
//////////////////// END OF GLOBAL VARIABLES///////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        displayed_code = findViewById(R.id.numpad_screen);

        Realm.init(this);



        config = new RealmConfiguration.Builder()
                .name("myrealm")
                //.encryptionKey(key)
                .directory(getExternalFilesDir(null))
                .build();
        Realm.setDefaultConfiguration(config);

        realm = Realm.getDefaultInstance();

        RealmResults<GUID> Results = realm.where(GUID.class).findAll();

        for(GUID GUID : Results) {
            Log.d("TAG----SIGN_IN----", GUID.getUnique_id_code());
        }

        RealmResults<ConnectionInfo> connection_results = realm.where(ConnectionInfo.class).findAll();
        ConnectionInfo connectionInfo = connection_results.first();

        Toast.makeText(getApplicationContext(),connectionInfo.get_ktel_server_url() , Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),connectionInfo.get_ktel_server_token() , Toast.LENGTH_LONG).show();


    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    public void OnButtonPressed_0(View view)
    {
        code_to_display = code_to_display + "0";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_1(View view)
    {
        code_to_display = code_to_display + "1";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_2(View view)
    {
        code_to_display = code_to_display + "2";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_3(View view)
    {
        code_to_display = code_to_display + "3";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_4(View view)
    {
        code_to_display = code_to_display + "4";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_5(View view)
    {
        code_to_display = code_to_display + "5";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_6(View view)
    {
        code_to_display = code_to_display + "6";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_7(View view)
    {
        code_to_display = code_to_display + "7";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_8(View view)
    {
        code_to_display = code_to_display + "8";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_9(View view)
    {
        code_to_display = code_to_display + "9";
        encrypted_code_to_display = encrypted_code_to_display + "*";
        displayed_code.setText(encrypted_code_to_display);
    }

    public void OnButtonPressed_clear(View view)
    {
        code_to_display = "" ;
        encrypted_code_to_display = "" ;
        displayed_code.setText(encrypted_code_to_display);
    }


    public void OnButtonPressed_Backspace(View view)
    {
        if (code_to_display != null && code_to_display.length() > 0 )
        {
            code_to_display = code_to_display.substring(0, code_to_display.length() - 1 );
            encrypted_code_to_display = encrypted_code_to_display.substring(0, encrypted_code_to_display.length() - 1 );
        }
        displayed_code.setText(encrypted_code_to_display);
    }


    public void OnButtonPressed_settings(View view)
    {

        Intent intent = new Intent(this,InformationFromSignInActivity.class);
        intent.putExtra("Direction Forth",true);
        startActivity(intent);

    }


    public void OnButtonPressed_OK(View view)
    {



        if(code_to_display.equals("12345")){                                                        // TODO implement sign in logic

            encrypted_code_to_display="";
            code_to_display="";
            displayed_code.setText("");
            Intent intent = new Intent(this,RoutesActivity.class);
            intent.putExtra("Direction Forth",true);
            startActivity(intent);


        }else{

            encrypted_code_to_display= "" ;
            code_to_display= "" ;
            displayed_code.setText( "" ) ;

            Toast.makeText(getApplicationContext(), "Λάθος κωδικός. Προσπαθήστε ξανά.", Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public void onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);                                                   // Exit app. Go to Home Screen.
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

}