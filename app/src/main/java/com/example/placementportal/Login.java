package com.example.placementportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.placementportal.database.DBHelper;

public class Login extends AppCompatActivity {
    DBHelper db;
    EditText edtreg,edtpass;
    Button btnsign;
    CheckBox checkBox;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = new DBHelper(this);
        edtreg = findViewById(R.id.Registration);
        edtpass = findViewById(R.id.password);
        btnsign = findViewById(R.id.signin);
        checkBox = findViewById(R.id.check);
        tv = findViewById(R.id.admin);

        SharedPreferences sharedPreferences;
        final SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("MY_APP",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regid = edtreg.getText().toString().trim();
                String pass = edtpass.getText().toString().trim();
                Boolean checkidpass = db.idpass(regid,pass);

                if (checkidpass == true) {
                    Intent i = new Intent(Login.this, Maincontent.class);
                    startActivity(i);
                    finish();
                    if (checkBox.isChecked()) {
                        editor.putString("myid", regid);
                        editor.putString("mypass", pass);
                        editor.commit();
                    }
                    Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Wrong ID or Pass", Toast.LENGTH_SHORT).show();
                
//                if (regid.equals("")||pass.equals("")){
//                    if (regid.equals(""))
//                        edtreg.setError("Enter Id");
//                    if (pass.equals(""))
//                        edtpass.setError("Enter Password");
//                Toast.makeText(getApplicationContext(), "Fill All The Fields", Toast.LENGTH_SHORT).show();
//                }else if (pass.length()<6) {
//                    edtpass.setError("Password Length Must be More than 6 characters");
//                }else
//                    if (regid.equals("shubham") && pass.equals("123456")) {

                    }
        });
        if (sharedPreferences.contains("myid")) {
            edtreg.setText(sharedPreferences.getString("myid", ""));
        }
        if (sharedPreferences.contains("mypass")) {
            edtpass.setText(sharedPreferences.getString("mypass", ""));
        }

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Admin.class);
                startActivity(i);
                finish();
            }
        });
    }
}
