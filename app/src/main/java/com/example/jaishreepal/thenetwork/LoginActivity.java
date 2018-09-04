package com.example.jaishreepal.thenetwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class LoginActivity extends AppCompatActivity {
    private Button button1;
    private EditText editText1,editText2;
    private FirebaseAuth mAuth;
    ImageView imageView;

    private SharedPreferences pref;
    public static final String STR_PREF_NAME = "ChiggiPref";

    private String namevalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editText1=(EditText)findViewById(R.id.et1);
        imageView=findViewById(R.id.iv);
        editText2=(EditText)findViewById(R.id.et2);
        button1 =(Button)findViewById(R.id.bt1);
        mAuth= FirebaseAuth.getInstance();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);


            }
        });

        pref = this.getSharedPreferences(STR_PREF_NAME, MODE_PRIVATE);

        if(!pref.getBoolean(STR_USER_LOGGED_IN, true))
        {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("id", pref.getString(STR_USER_ID, "demo"));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        else {
            if (button1 != null)
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email = editText1.getText().toString();
                        String password = editText2.getText().toString();
                        mAuth.signInWithEmailAndPassword(email, password).
                                addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {



                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            retainUser(editText1.getText().toString());
                                            Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_LONG).show();
                                 //   startActivity(new Intent(getApplicationContext(),option.class));
                                            Intent intent = new Intent(getApplicationContext(), Main22.class);

                                            namevalue=editText1.getText().toString();


                                            intent.putExtra("name",namevalue);


                                            intent.putExtra("id", editText1.getText());
                                           startActivity(intent);
                                            finish();

                                        } else {
                                            Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                    }
                });
        }

    }

    public static final String STR_USER_ID = "userId";
    public static final String STR_USER_LOGGED_IN = "loggedIn";
    private void retainUser(String id){
        pref.edit().putBoolean(STR_USER_LOGGED_IN , true).commit();
        pref.edit().putString(STR_USER_ID, id).commit();
        pref.edit().putString("UserName",namevalue).commit();
    }

}
