package com.accede.user.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    AutoCompleteTextView city;
    String[] citylist={"Bengaluru","Chennai","Delhi","Kolkata","Mumbai"};
    TextView go;
    String choosecity="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        go=(TextView)findViewById(R.id.textgo);
        city=(AutoCompleteTextView)findViewById(R.id.etcity);
        city.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,citylist));
        city.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                 choosecity=city.getText().toString();
            }
        });

        city.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                city.showDropDown();

            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!choosecity.trim().equalsIgnoreCase("")||
                        choosecity.trim().equalsIgnoreCase(null))
                {
                    Intent intent = new Intent(MainActivity.this, MainScreen.class);
                    intent.putExtra("city", choosecity);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Please Select Your City",
                         Toast.LENGTH_LONG).show();

            }
        });
      //  else
        //    Toast.makeText(getApplicationContext(), "Please Select Your City",
           //         Toast.LENGTH_LONG).show();

    }

}
