package com.example.smagionas.redbustickets;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.gson.Gson;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import java.security.SecureRandom;
import java.util.UUID;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class SetupActivity extends AppCompatActivity
{


//////////////////// START OF GLOBAL VARIABLES/////////////////////////
    EditText ktelID;
    EditText key ;

    BagOfPrimitives obj = new BagOfPrimitives();
    Gson gson = new Gson();
    RealmConfiguration config;
    Realm realm;

    static String result;
    static int STORAGE_PERMISSION_CODE = 23;
//////////////////// END OF GLOBAL VARIABLES///////////////////////////


    @Override
    public void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Realm.init(this);


        //File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS); // When using this as realm directory, app crashes... Default directory works fine

        //byte[] key = new byte[64];                                                                    // if we want authentication. Of course it won't be done by SecureRandom()
        //new SecureRandom().nextBytes(key);

        config = new RealmConfiguration.Builder()
                .name("myrealm")
                //.encryptionKey(key)
                .directory(getExternalFilesDir(null))
                .build();
        Realm.setDefaultConfiguration(config);

        checkFirstRun();                                                                                // Check if this is the first time the tablet runs the app.


        findViewById(R.id.SetupLayout).setOnTouchListener(new View.OnTouchListener() {                  // Hides the keyboard when user touches outside of the editText fields.
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


        if (savedVersionCode == DOESNT_EXIST)
        {

            DoFirstRun();                                                                           // First time the app runs on this device...

        } else{
                                                                                                    // Just a normal run...
            DoNormalRun();

        }
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }



    private void DoNormalRun()
    {

        Toast.makeText(getApplicationContext(), "Κανονικό τρέξιμο!!!", Toast.LENGTH_LONG).show();


        realm = Realm.getDefaultInstance();

        RealmResults<GUID> Results = realm.where(GUID.class).findAll();                             // Find the saved GUID and display it for debug purposes

        for(GUID GUID : Results) {
            Log.d("TAG-------", GUID.getUnique_id_code());
        }



    }




    private void DoFirstRun(){

        String uniqueID = UUID.randomUUID().toString();


        realm = Realm.getInstance(config);


        Log.d("------PATH-------",realm.getPath());

        realm.beginTransaction();                                                                   // Save the GUID to the database
        GUID guid = realm.createObject(GUID.class);
        guid.setUnique_id_code(uniqueID);
        realm.commitTransaction();
        Toast.makeText(getApplicationContext(), "Πρώτο τρέξιμο!!!", Toast.LENGTH_LONG).show();

        DoNormalRun();

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

            //Toast.makeText(getApplicationContext(), obj.url, Toast.LENGTH_LONG).show();


            Boolean IsURL;
            IsURL = URLUtil.isValidUrl(obj.url);

            //String token_result = obj.token;
            //Toast.makeText(getApplicationContext(), token_result, Toast.LENGTH_LONG).show();

            realm.beginTransaction();
            ConnectionInfo con_info = realm.createObject(ConnectionInfo.class);                                      // Save ktel_url and ktel_token to the database
            con_info.set_ktel_server_url(obj.url);
            con_info.set_ktel_server_token(obj.token);
            realm.commitTransaction();




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
    String token;

    BagOfPrimitives() {


    }
}



class myThread extends Thread {

    private String Value1;
    private String Value2;

    myThread(String Value1, String Value2) {
        this.Value1 = Value1;
        this.Value2 = Value2;
    }


    @Override
    public void run() {

       // APTMTRWebServiceSoap service = new APTMTRWebServiceSoap();

        String str = "empty";
        String SOAP_ACTION = "http://maimuri.org/GetWebServiceURL";
        String METHOD_NAME = "GetWebServiceURL";
        String NAMESPACE = "http://maimuri.org/";
        String URL = "http://10.10.1.192/MTRWebSvc/MTRWebService.asmx";


        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("customerKey", Value1);
            request.addProperty("licenseKey", Value2);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            androidHttpTransport.call(SOAP_ACTION, envelope);

            SoapPrimitive result = (SoapPrimitive)envelope.getResponse();


            str = result.toString();

        }
        catch (Exception e) {

        }

        SetupActivity.result = str;

    }

}








