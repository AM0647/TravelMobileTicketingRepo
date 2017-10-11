package com.example.smagionas.redbustickets;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class SetupActivity extends AppCompatActivity
{

    EditText ktelID;
    EditText key ;
    static String result;
    BagOfPrimitives obj = new BagOfPrimitives();
    Gson gson = new Gson();
    //TextView Server_URL;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);




        checkFirstRun();                                                                            // Check if this is the first time the tablet runs the app...
    }


    @Override
    public void onResume()
    {
        super.onResume();
        ktelID = findViewById(R.id.editTextKtelId);
        key = findViewById(R.id.editTextKey);
        //Server_URL = (TextView) findViewById(R.id.server_url);
        ktelID.setText("");
        key.setText("");
    }





    private void checkFirstRun()
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


        }

        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }



    private void DoNormalRun()
    {

        TopSetupFragment topSetupFragment = new TopSetupFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.LocationLayout, topSetupFragment)
                .commit();


        BottomFragment BottomFragment = new BottomFragment();
        manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.DestinationsLayout,BottomFragment)
                .commit();

    }




    private void DoFirstRun(){



    }



    public void onDoneSetupClicked(View view)
    {                                                                                                               // Here user tries to sign up
        final String ktelID_input = ktelID.getText().toString();                                                    // Get the user input
        final String key_input = key.getText().toString();


        if(!(ktelID_input.isEmpty())  &&  !(key_input.isEmpty()))                                                   // Check if fields are empty
        {

            AuthenticateDevice(ktelID_input,key_input);

            {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);       // Make sure android soft keyboard gets closed
                imm.hideSoftInputFromWindow(ktelID.getWindowToken(), 0);
            }


            Boolean IsURL = false;
            IsURL = URLUtil.isValidUrl(obj.url);

            if(IsURL){
                Intent intent = new Intent(view.getContext(),SignInActivity.class);
                startActivity(intent);

            }






        }else
         {

            Toast.makeText(getApplicationContext(), "Please fill in the fields", Toast.LENGTH_LONG).show();         // fields empty

            ktelID.setText("");
            key.setText("");
            ktelID.requestFocus();

         }
    }


    public void onClearSetupClicked(View view)
    {
        ImageView clear = findViewById(R.id.clear);
        clear.animate().rotationY(clear.getRotationY()+360).setDuration(1000);

        //EditText ktelID = (EditText) findViewById(R.id.editTextKtelId);
        //EditText key = (EditText) findViewById(R.id.editTextKey);

        ktelID.setText("");
        key.setText("");
        ktelID.requestFocus();

    }


    public void AuthenticateDevice(final String field1, final String field2){


        result = "Not  initialized";
        //Runthread1();



        myThread thread = new myThread(field1,field2);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        obj = gson.fromJson(result, BagOfPrimitives.class);




        Toast.makeText(getApplicationContext(), obj.url, Toast.LENGTH_LONG).show();

//        String url ="http://10.10.1.192/MTRWebSvc/MTRWebService.asmx/GetWebServiceURL";                         //TODO Provide web service url
//
//        APTMTRWebServiceSoap service = new APTMTRWebServiceSoap();
//
//        String result = "Not  initialized";
//        try {
//            result = service.GetWebServiceURL("test","1234");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            result = service.GetWebServiceURL("test","1234");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

//        SampleService srv1 = new SampleService();
//        Request req = new Request();
//        req.companyId = "1";
//        req.userName = "userName";
//        req.password = "pas";
//        Response response =    srv1.ServiceSample(req);








//        // Request a string response from the provided URL.
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response) {
////
//
//
//                        ParseUrlFromResponse(response);
//
//
//
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();                           // fields not empty
//                        //Toast.makeText(getApplicationContext(), "No real error...", Toast.LENGTH_LONG).show();                           // fields not empty
//                        //Server_URL.setText(error.getMessage());
//                        //Log.d("Error.Response", error.getMessage());
//                        //Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("customerKey", field1);
//                params.put("licenseKey", field2);
//
//                return params;
//            }
//
//
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> headers = new HashMap<String, String>();
//                //headers.put("Content-Type", "application/x-www-form-urlencoded");
//                return headers;
//            }
//        };
//
//
//
//        //postRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(postRequest);
//
//        //queue.start();
//
//
//
//
//    }
//
//
//    void ParseUrlFromResponse(String input){
//
//        JSONObject jresponse = null;
//        try {
//            jresponse = new JSONObject(input);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String str_value = null;
//        try {
//            str_value = jresponse.getString("url");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        //Toast.makeText(getApplicationContext(), str_value, Toast.LENGTH_LONG).show();
//
//        {
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);       // Make sure android soft keyboard gets closed
//            imm.hideSoftInputFromWindow(ktelID.getWindowToken(), 0);
//        }
//
//
//        if(str_value.equals("http://111.222.333.444/AnotherWebService")){                                       // TODO implement setup logic
//
//            View view = findViewById(android.R.id.content);
//            Intent intent = new Intent(view.getContext(),SignInActivity.class);
//            startActivity(intent);
//
//        }else{
//
//            Toast.makeText(getApplicationContext(), "Wrong inputs. Please try again..", Toast.LENGTH_LONG).show();         // fields empty
//
//            ktelID.setText("");
//            key.setText("");
//            ktelID.requestFocus();
//
//
//
//        }
//
//
//
//
//
    }


//    public void Runthread1( ){
//
//        Thread thread = new Thread(new Runnable(){
//            public void run() {
//
//                APTMTRWebServiceSoap service = new APTMTRWebServiceSoap();
//
//                String str = "empty";
//
//
//                try {
//                    str = service.GetWebServiceURL(field1,field2);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                result = str;
//                //String value2;
//
//                //result = obj.value2;
//                //result = str;
//                //obj.value2
//
//            }
//        });
//
//
//
//
//
//
//
//
//    }








}


class BagOfPrimitives {
    public String url;

    BagOfPrimitives() {


    }
}

class myThread extends Thread {

    String Value1;
    String Value2;

    public myThread(String Value1, String Value2) {
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




