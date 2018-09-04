package com.example.jaishreepal.thenetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class upload_download extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_download);
        final Button b1,b2;
        final Intent intent=getIntent();
final String link3=intent.getStringExtra("link");
       final String link4=intent.getStringExtra("link2");
        b1=findViewById(R.id.upload);
        b2=findViewById(R.id.download);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Webview.class);
                intent.putExtra("link5",link3);

                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getApplicationContext(),webview5.class);
                intent1.putExtra("link6",link4);


               startActivity(intent1);

            }
        });

    }
}
