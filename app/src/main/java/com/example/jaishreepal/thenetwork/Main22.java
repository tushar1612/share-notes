package com.example.jaishreepal.thenetwork;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.jaishreepal.thenetwork.LoginActivity.STR_PREF_NAME;
import static com.example.jaishreepal.thenetwork.LoginActivity.STR_USER_ID;

public class Main22 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences pref;
    TextView textView;
    Button b1,b2,b3,b4;

    /*Button button;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pref=this.getSharedPreferences(STR_PREF_NAME,MODE_PRIVATE);
/*button=(Button)findViewById(R.id.chat);*/
        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);
        b3=findViewById(R.id.btn3);
        b4=findViewById(R.id.btn4);
b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getApplicationContext(),upload_download.class);
        intent.putExtra("link","https://arorakashish333.wixsite.com/mysite");
        intent.putExtra("link2","https://arorakashish333.wixsite.com/mysite/download");
        startActivity(intent);
    }
});
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),upload_download.class);
                intent.putExtra("link","https://arorakashish333.wixsite.com/mysite/ece-home");
                intent.putExtra("link2","https://arorakashish333.wixsite.com/mysite/ece-notes");
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),upload_download.class);
                intent.putExtra("link","https://arorakashish333.wixsite.com/mysite/manufacturing");
                intent.putExtra("link2","https://arorakashish333.wixsite.com/mysite/mechenical-notes");

                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),upload_download.class);
                intent.putExtra("link","https://arorakashish333.wixsite.com/mysite/civil-home");
                intent.putExtra("link2","https://arorakashish333.wixsite.com/mysite/civil-notes");

                startActivity(intent);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


View header=navigationView.getHeaderView(0);
textView=(TextView)header.findViewById(R.id.textView);
        String id=pref.getString(STR_USER_ID,"User");
textView.setText(id);
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });*/


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main22, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent=new Intent(getApplicationContext(),Webview.class);
            intent.putExtra("link","https://arorakashish333.wixsite.com/mysite");
            startActivity(intent);
            // Handle the camera action
        }
        else if (id == R.id.nav_logout) {
            Toast.makeText(this, "WANNA LOGOUT?", Toast.LENGTH_SHORT).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(Main22.this);
            builder.setTitle("logout !");
            builder.setMessage("do you really want to logout");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    pref.edit().clear().commit();
                    Intent intent = new Intent(Main22.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        return true;
        } else if (id == R.id.nav_share) {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_TEXT,"https://arorakashish333.wixsite.com/mysite");
         //   emailIntent.putExtra(Intent.EXTRA_EMAIL,"androidrajpoot@gmail.com");
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,"SHARE NOTES");
           // emailIntent.putExtra(Intent.EXTRA_STREAM,fileUri);

            startActivity(emailIntent);
            Toast.makeText(getApplicationContext(), "File sent", Toast.LENGTH_LONG).show();

        }  else if (id == R.id.nav_message) {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
