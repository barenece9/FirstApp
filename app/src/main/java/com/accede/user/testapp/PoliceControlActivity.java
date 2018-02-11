package com.accede.user.testapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.accede.user.adapter.PoliceControlAdapter;

public class PoliceControlActivity extends Activity {
    ListView list;
    String city;
    String[] web = {"Lalbazar Control Room:",
    "Traffic Control Room:",
   "Wireless Control Room:",
    "North Divn. Control Room:",
    "Central Divn. Control Room:",
    "South Divn. Control Room:",
    "ESD Control Room:",
    "Port Divn. Control Room:",
    "South Sub. Divn. C. Room:",
    "South East Divn. C. Room:",
    "South West Divn. C. Room:",
    "B G Lines Control Room:",
            "4th Bn. Control Room:",
            "5th Bn. Control Room:",
    "B T Road Control Room:",
            "S B Control Room:",
            "D D Crime Control Room:",
            "S C O Sub Control:",
            "E B Sub Control:",} ;

    String[] imageId = {

            	"(91-33) 2214-3024 / 2214-3230 / 2214-1310 (91-33) 2214-5512 [Fax]",
            "(91-33) 2214-3644 / 2242-7248",
            "(91-33) 2214-1288",
            "(91-33) 2360-6405 / 2360-6417",
           "(91-33) 2228-5209 / 0944",
            "(91-33) 2283-7051",
            "(91-33) 2363-3835 / 2374-6436 / 2363-3476",
            "(91-33) 2409-3109",
            "(91-33) 2499-4704",
            "(91-33) 2290-4661",
            "(91-33) 2499-4700",
            "(91-33) 2409-9057",
            "(91-33) 2337-4533 / 2321-3952",
            "(91-33) 2355-7949 / 2355-5412",
            "(91-33) 2530-0809 / 0891 / 0892",
            "(91-33) 2283-7017 / 2283-7016",
            "(91-33) 2214-1431 / 2250-5166",
            "(91-33) 2283-7089",
            "(91-33) 2228-2172 / 2173 / 2126"
};
    String [] bengaluruname={"Bengaluru Traffic Helpline"};String[]bengaluruph={"1095 080-2294 3030 131"};
    String [] mumbainame={"Mumbai Traffic Control Room "};String[]mumbaiph={"+(91)-22-24937755, 24937746, 24937747, 24939717  +(91)-8454999999"};
    String [] delhiname={"Delhi Traffic Helpline"};String[]delhiph={"25844444/1095"};
    String [] channiname={"Police Traffic Control Room"};String[]channiph={"+(91)-44-23452362, +(91)-9003130103"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_police_control);
        Intent intent=getIntent();
        city = intent.getStringExtra("city");
        if(city.equalsIgnoreCase("Delhi")) {
            PoliceControlAdapter adapter = new
                    PoliceControlAdapter(PoliceControlActivity.this, delhiname, delhiph);
            list = (ListView) findViewById(R.id.listView);
            list.setAdapter(adapter);
        }
        else if(city.equalsIgnoreCase("Chennai")){
            PoliceControlAdapter adapter = new
                    PoliceControlAdapter(PoliceControlActivity.this, channiname, channiph);
            list = (ListView) findViewById(R.id.listView);
            list.setAdapter(adapter);
        }
        else if(city.equalsIgnoreCase("Mumbai")){
            PoliceControlAdapter adapter = new
                    PoliceControlAdapter(PoliceControlActivity.this,mumbainame, mumbaiph);
            list = (ListView) findViewById(R.id.listView);
            list.setAdapter(adapter);
        }
        else if(city.equalsIgnoreCase("Bengaluru")){
            PoliceControlAdapter adapter = new
                    PoliceControlAdapter(PoliceControlActivity.this, bengaluruname, bengaluruph);
            list = (ListView) findViewById(R.id.listView);
            list.setAdapter(adapter);
        }
        else if (city.equalsIgnoreCase("Kolkata")){
            PoliceControlAdapter adapter = new
                    PoliceControlAdapter(PoliceControlActivity.this, web, imageId);
            list = (ListView) findViewById(R.id.listView);
            list.setAdapter(adapter);
        }
    }
}
