package com.accede.user.testapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class FareChartActivity extends Activity {
    ListView listView;
    String[] mobileArray = {"2.0 | 25.0","2.5 | 31.0","3.0 | 37.0","3.5 | 43.0","4.0 | 49.0","4.5 | 55.0", "5.0 | 61.0",
            "5.5 | 67.0", "6.0 | 73.0", "6.5 | 79.0", "7.0 | 85.0", "7.5 | 91.0", "8.0 | 97.0", "8.5 | 103.0", "9.0 | 109.0",
          "9.5 | 115.0","10.0 | 121.0", "10.5 | 127.0","11.0 | 133.0","11.5 | 139.0","12.0 | 145.0","12.5 | 151.0","13.0 | 157.0",
            "13.5 | 163.0","14.0 | 169.0","14.5 | 175.0","15.0 | 181.0"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fare_chart);
        listView=(ListView)findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, mobileArray);


        listView.setAdapter(adapter);
    }
}
