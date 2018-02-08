package com.example.brenda.gokadatest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Activity_Register extends AppCompatActivity implements View.OnClickListener{

    //Defining views
    private EditText editTextName;
    private EditText editTextDesg;
    private EditText editTextSal;
    private EditText editTextDesg1;
    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up);

        //Initializing views
        editTextName = (EditText) findViewById(R.id.et_uName);
        editTextDesg = (EditText) findViewById(R.id.et_email);
        editTextSal = (EditText) findViewById(R.id.et_pwd);

        editTextDesg1 = (EditText) findViewById(R.id.et_address);
        buttonAdd = (Button) findViewById(R.id.et_reg);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    //Adding an employee
    private void addEmployee(){

        final String name = editTextName.getText().toString().trim();
        final String desg = editTextDesg.getText().toString().trim();
        final String sal = editTextSal.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Activity_Register.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Activity_Register.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME,name);
                params.put(Config.KEY_EMP_DESG,desg);
                params.put(Config.KEY_EMP_SAL,sal);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }


    }
}
