package com.example.jaishreepal.thenetwork;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    Button btn;
    TextView textView,otp;
    EditText uname;
    EditText upass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth= FirebaseAuth.getInstance();
        btn=findViewById(R.id.sign_up);
        textView=findViewById(R.id.login);
        uname=findViewById(R.id.email);
        upass=findViewById(R.id.password);
        otp=(TextView)findViewById(R.id.otp);
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PhoneAuthe.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this,"REGISTRATION SUCESSFUL",Toast.LENGTH_LONG);
                String email = uname.getText().toString();
                String password = upass.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password).
                        addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }

        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });

    }
}
