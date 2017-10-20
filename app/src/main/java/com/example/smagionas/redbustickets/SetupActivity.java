package com.example.smagionas.redbustickets;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.gson.Gson;


public class SetupActivity extends AppCompatActivity
{


//////////////////// START OF GLOBAL VARIABLES/////////////////////////
    EditText ktelID;
    EditText key ;

    BagOfPrimitives obj = new BagOfPrimitives();
    Gson gson = new Gson();

    static String result;
//////////////////// END OF GLOBAL VARIABLES///////////////////////////


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);




        checkFirstRun();                                                                            // Check if this is the first time the tablet runs the app.


        findViewById(R.id.SetupLayout).setOnTouchListener(new View.OnTouchListener() {              // Hides the keyboard when user touches outside of the editText fields.
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                return true;
            }
        });




    }


    @Override
    public void onResume()
    {
        super.onResume();
        ktelID = findViewById(R.id.editTextKtelId);
        key = findViewById(R.id.editTextKey);
        ktelID.setText("");
        key.setText("");
    }





    private void checkFirstRun()                                                                    // TODO Add real logic behind this method.
    {
        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        int currentVersionCode = BuildConfig.VERSION_CODE;                                          // Get current version code

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);                   // Get saved version code
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);


        if (currentVersionCode == savedVersionCode)
        {                                                                                           // Just a normal run...
            DoNormalRun();
            //return;
        } else if (savedVersionCode == DOESNT_EXIST)
        {                                                                                           // First time the app runs on this device...
            DoFirstRun();

        } else if (currentVersionCode > savedVersionCode)
        {                                                                                           // App was updated...

              //do nothing
        }

        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }



    private void DoNormalRun()
    {



        TopSetupFragment topSetupFragment = new TopSetupFragment();                                 // Header
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.SetupTopLayout, topSetupFragment)
                .commit();

        CenterSetupFragment centerSetupFragment = new CenterSetupFragment();                        // Body
        manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.SetupCenterLayout, centerSetupFragment)
                .commit();

        BottomSetupFragment bottomSetupFragment = new BottomSetupFragment();                        // Footer
        manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.SetupBottomLayout,bottomSetupFragment)
                .commit();

    }




    private void DoFirstRun(){



    }



    public void onDoneSetupClicked(View view)                                                                       // User pressed the done button
    {
        final String ktelID_input = ktelID.getText().toString();                                                    // Get the user input
        final String key_input = key.getText().toString();


        if(!(ktelID_input.isEmpty())  &&  !(key_input.isEmpty()))                                                   // Fields are not empty
        {

            AuthenticateDevice(ktelID_input,key_input);                                                             // Send data to server and wait for the response


            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);           // Make sure android soft keyboard gets closed
            imm.hideSoftInputFromWindow(ktelID.getWindowToken(), 0);



            Boolean IsURL;
            IsURL = URLUtil.isValidUrl(obj.url);

            if(IsURL){
                Intent intent = new Intent(view.getContext(),SignInActivity.class);
                startActivity(intent);

            }else{

                Toast.makeText(getApplicationContext(), "Λάθος στοιχεία. Προσπαθήστε ξανά", Toast.LENGTH_LONG).show();
                ktelID.setText("");
                key.setText("");
                ktelID.requestFocus();


            }
        }else                                                                                                       // Fields are empty
         {

            Toast.makeText(getApplicationContext(), "Συμπληρώστε τα πεδία.", Toast.LENGTH_LONG).show();

            ktelID.setText("");
            key.setText("");
            ktelID.requestFocus();

         }
    }


    public void onClearSetupClicked(View view)                                                                      // User pressed the clear button
    {
        ImageView clear = findViewById(R.id.clear);
        clear.animate().rotationY(clear.getRotationY()+360).setDuration(1000);
        ktelID.setText("");
        key.setText("");

    }


    public void AuthenticateDevice(final String field1, final String field2){                                       // Authentication of device


        result = "Not  initialized";
        myThread thread = new myThread(field1,field2);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        obj = gson.fromJson(result, BagOfPrimitives.class);

    }




}


class BagOfPrimitives {
    String url;

    BagOfPrimitives() {


    }
}



class myThread extends Thread {

    String Value1;
    String Value2;

    myThread(String Value1, String Value2) {
        this.Value1 = Value1;
        this.Value2 = Value2;
    }


    @Override
    public void run() {

        APTMTRWebServiceSoap service = new APTMTRWebServiceSoap();

        String str = "empty";


        try {
            str = service.GetWebServiceURL(Value1, Value2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SetupActivity.result = str;

    }

}




