package com.example.jaishreepal.thenetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PhoneAuthActivity";
    private FirebaseAuth mAuth;


    // http://maddeveloperweb.blogspot.in/2017/12/blog-post.html

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button=findViewById(R.id.continu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Main22.class);
                startActivity(intent);
            }
        });
        Button mSignOutButton = (Button) findViewById(R.id.sign_out_button);
        TextView fireBaseId = (TextView) findViewById(R.id.detail);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth!=null){
            fireBaseId.setText(mAuth.getCurrentUser().getPhoneNumber());
        }
        mSignOutButton.setOnClickListener(this);
    }

    @Override    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_out_button:
                mAuth.signOut();
                startActivity(new Intent(MainActivity2.this, PhoneAuthe.class));
                finish();
                break;

        }
    }
}

