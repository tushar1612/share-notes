package com.example.jaishreepal.thenetwork;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE=1;


    FloatingActionButton fab;


    Toolbar toolbar;

    private FirebaseListAdapter<ChatMessage> adapter;

    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
            return;
        }


        toolbar=findViewById(R.id.tb);

        setSupportActionBar(toolbar);

        fab=findViewById(R.id.fab);

        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText input=findViewById(R.id.et_input);

                        FirebaseDatabase.getInstance().getReference().push().setValue(new ChatMessage(input.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getDisplayName()));
                        input.setText("");
                    }
                });

        relativeLayout=findViewById(R.id.myplace);

        if (FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
        }

        else
        {

            Snackbar.make(relativeLayout,"Welcome  "+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();

        }

        //load content

        displayChatMessage();
    }

    private void displayChatMessage() {

        ListView listView=findViewById(R.id.list_of_message);

        adapter=new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference()) {

            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // get reference of view

                TextView messageUser,messageText,messageTime;

                messageText=v.findViewById(R.id.message_text);
                messageUser=v.findViewById(R.id.message_user);
                messageTime=v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy(HH:mm:ss)",model.getMessageTime()));


            }
        };

        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                Snackbar.make(relativeLayout,"Successfull Signed in Welcome |",Snackbar.LENGTH_SHORT).show();
                displayChatMessage();
            }

            else {
                Snackbar.make(relativeLayout,"Sorry Could not  sign in ....",Snackbar.LENGTH_SHORT).show();

                finish();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.menu_sign_out)
        {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    Snackbar.make(relativeLayout,"Signout Successfully",Snackbar.LENGTH_SHORT).show();
                    finish();

                }
            });
        }

        return true;
    }
}
