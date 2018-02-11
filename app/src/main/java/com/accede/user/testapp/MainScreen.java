package com.accede.user.testapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class MainScreen extends Activity {

    Button farechart,policecontrol,where2go,nearestitems;
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_screen);
        Intent intent=getIntent();
        city = intent.getStringExtra("city");
        farechart=(Button)findViewById(R.id.btn_farechart);
        policecontrol=(Button)findViewById(R.id.btn_policecontrol);
        where2go=(Button)findViewById(R.id.btn_where2go);
        nearestitems=(Button)findViewById(R.id.btn_nearestitems);

        farechart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this,FareChartActivity.class);
                intent.putExtra("city",city);
                startActivity(intent);

            }
        });
        policecontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this,PoliceControlActivity.class);
                intent.putExtra("city",city);
                startActivity(intent);

            }
        });
        where2go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this,Where2goActivity.class);
                intent.putExtra("city",city);
                startActivity(intent);

            }
        });
        nearestitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this,NearetItemActivity.class);
                intent.putExtra("city",city);
                startActivity(intent);

            }
        });
    }
}
