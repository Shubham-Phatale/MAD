package com.example.placementportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.placementportal.database.DBHelper;

public class Adminpage extends AppCompatActivity {
    DBHelper db;
    EditText edtid,edtpass,cnfpass;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpage);

        db = new DBHelper(this);
        edtid = findViewById(R.id.stdid);
        edtpass = findViewById(R.id.stdpass);
        btn = findViewById(R.id.btnreg);
        cnfpass = findViewById(R.id.cfrpass);
        tv = findViewById(R.id.log);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etid = edtid.getText().toString().trim();
                String etpass = edtpass.getText().toString().trim();
                String cfpass = cnfpass.getText().toString().trim();

                if (etid.equals("") || etpass.equals("") || cfpass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (etpass.equals(cfpass)) {
                        Boolean check = db.checkid(etid);
                        if (check == true) {
                            Boolean insert = db.insert(etid, etpass);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registered sucessfully", Toast.LENGTH_SHORT).show();
                                Log.i("SHUB","Values Entered Sucessfully");
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "ID already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Adminpage.this, Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
