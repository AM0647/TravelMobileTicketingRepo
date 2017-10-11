package com.example.smagionas.redbustickets;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class SetupActivity extends AppCompatActivity
{

    EditText ktelID;
    EditText key ;
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

            SendDataToServer(ktelID_input,key_input);

            {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);       // Make sure android soft keyboard gets closed
                imm.hideSoftInputFromWindow(ktelID.getWindowToken(), 0);
            }


            Intent intent = new Intent(view.getContext(),SignInActivity.class);                                     // TODO Remove from here
            startActivity(intent);



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


    public void SendDataToServer(final String field1, final String field2){

        // Instantiate the RequestQueue.



        String url ="http://10.10.1.192/MTRWebSvc/MTRWebService.asmx/GetWebServiceURL";                         //TODO Provide web service url








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




}

