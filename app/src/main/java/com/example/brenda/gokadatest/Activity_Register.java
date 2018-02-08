package com.example.brenda.gokadatest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import MainHelpers.GMailSender;
import helper.SQLiteHandler;
import helper.SessionManager;
import volley.AppController;
import volley.Config_URL;

import static com.example.brenda.gokadatest.R.id.email;

public class Activity_Register extends Activity {
    private static final String TAG = Activity_Register.class.getSimpleName();
    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private String output;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       //inputFullName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(email);
       inputPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
      //  if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
       //     Intent intent = new Intent(Activity_Register.this,
       //             Activity_Main.class);
        //    startActivity(intent);
        //    finish();
       // }


        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                String name = inputFullName.getText().toString();
                String email = inputEmail.getText().toString();
                String password=inputEmail.getText().toString();

                if (!email.isEmpty()) {

                    registerUser(email, password);
                    sendMessage();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        Activity_Login.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }
    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser( final String email,
                              final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                Config_URL.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();


                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        //String name = user.getString("name");

                       String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
//                        db.addUser(email, uid, created_at);

                        // Launch login activity
                        Intent intent = new Intent(
                                Activity_Register.this,
                                Activity_Login.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
                StringBuilder sb = new StringBuilder();
                Random random = new Random();
                for (int i = 0; i < 3; i++) {
                    char c = chars[random.nextInt(chars.length)];
                    sb.append(c);

                    output= sb.toString();
                   // System.out.println(output);
                }
                // Posting params to register url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("tag", "register");
                    params.put("email", email);
                    params.put("password", output);
                System.out.println(output);
                    return params;
                }



        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void sendMessage() {
       // final ProgressDialog dialog = new ProgressDialog(Activity_Register.this);
       // dialog.setTitle("Sending Email");
       // dialog.setMessage("Please wait");
        //dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("sydneychigbuedeveloper@gmail.com","favour1,");
                    sender.sendMail("Flutterwave Food order App",
                            output.toString(),
                            "sydneychigbuedeveloper@gmail.com",
                            inputEmail.getText().toString());
                   // dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
}