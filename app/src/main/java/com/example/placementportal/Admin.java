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

public class Admin extends AppCompatActivity {
    EditText adminid,edtpass;
    Button btn;
    CheckBox cb;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        adminid = findViewById(R.id.admin);
        edtpass = findViewById(R.id.pass);
        btn = findViewById(R.id.btnsign);
        cb = findViewById(R.id.cbox);
        tv = findViewById(R.id.student);

        SharedPreferences sharedPreferences;
        final SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("MY APP",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String admin = adminid.getText().toString().trim();
                String password = edtpass.getText().toString().trim();

                if (admin.equals("") || password.equals("")) {
                    if (admin.equals(""))
                        adminid.setError("Enter Admin ID");
                    if (password.equals(""))
                        edtpass.setError("Enter Password");
                    Toast.makeText(Admin.this, "Fill All the Details", Toast.LENGTH_SHORT).show();
                }else if (password.length() < 6){
                    edtpass.setError("password should be more than 6 charcters");
                }else{
                    if (admin.equals("admin") && password.equals("admin123")){
                        Intent i = new Intent(Admin.this , Adminpage.class);
                        i.putExtra("aid",admin);
                        startActivity(i);
                        finish();
                        if (cb.isChecked()){
                            editor.putString("aid",admin);
                            editor.putString("apass",password);
                            editor.putBoolean("is_checked",cb.isChecked());
                            editor.commit();
                        }
                    }
                    else{
                        Toast.makeText(Admin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        if (sharedPreferences.contains("aid"))
            adminid.setText(sharedPreferences.getString("aid",""));
        if (sharedPreferences.contains("apass"))
            edtpass.setText(sharedPreferences.getString("apass",""));

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Admin.this,Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
