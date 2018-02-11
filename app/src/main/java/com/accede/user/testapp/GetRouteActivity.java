package com.accede.user.testapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.accede.user.googledistancematrix.Jsonparser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetRouteActivity extends Activity {
    String source="",destiny="",distext="",durtext="",city="";
    String sourcelatitute,sourcelongitute,destinylatitute,destinylongitute;
    List<String> path;
    ListView routeway;
    TextView toptext;
    String sourcelatlngstatus="",destinylatlngstatus="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_get_route);
        Intent intent=getIntent();
        source = intent.getStringExtra("source");
        destiny= intent.getStringExtra("destiny");
        city=intent.getStringExtra("city");
        routeway=(ListView)findViewById(R.id.route_way);
        toptext=(TextView)findViewById(R.id.toptext);
        System.out.println(source+ "^^^^^^^^^^^^^ " +destiny);

        // System.out.println(source.trim()+" "+distiny.trim());
        // request.call();
        //if(!source.equalsIgnoreCase(null)||distiny.equalsIgnoreCase(null))
        new Getlatlng().execute();

    }
    ///asyn task calll
    public class Getlatlng extends AsyncTask<String,Void,String> {

        // Hashmap for ListView
        ArrayList<HashMap<String, String>> studentList;
        ProgressDialog proDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            proDialog = new ProgressDialog(GetRouteActivity.this);
            proDialog.setMessage("Please wait...");
            proDialog.setCancelable(false);
            proDialog.show();
        }
        @Override
        protected String doInBackground(String... arg0) {
            ///for source place
          //  String url2 ="http://maps.googleapis.com/maps/api/geocode/json?address="+source+"kolkata,india&sensor=false%22";
            String url2 ="http://maps.googleapis.com/maps/api/geocode/json?address="+source+""+city+",india&sensor=false%22";
            Jsonparser webreq2 = new Jsonparser();
            //String url1=arg0[0];
            System.out.println("SSSSSSSSSSSSSSSSSSSSSSSS "+url2);
            // Making a request to url and getting response
            String jsonStr2 = webreq2.makeWebServiceCall(url2, Jsonparser.POSTRequest);
            Log.d("Response: ", "> " + jsonStr2);
            // String  student = ParseJSON(jsonStr);
            System.out.println("|||||||||||||||"+jsonStr2);
            try {
                JSONObject jsonObj = new JSONObject(jsonStr2);
                JSONArray students = jsonObj.getJSONArray("results");
                sourcelatlngstatus=jsonObj.getString("status");
                JSONObject c = students.getJSONObject(0);
                JSONObject d =c.getJSONObject("geometry");
                JSONObject e = d.getJSONObject("location");
                sourcelatitute = e.getString("lat");
                sourcelongitute= e.getString("lng");
                System.out.println(sourcelatitute+"+++++++++++++++"+sourcelongitute);
            }
            catch (Exception e){
                System.out.println("SSSSSSSEEEEEEEEXXXXXXXXXXXPPPPPPPPPPTTTIIIIOOOONNN");
            }
            ////end source place


            ////for destiny place/////
            String url1 = "http://maps.googleapis.com/maps/api/geocode/json?address="+destiny+""+city+",india&sensor=false%22";
            Jsonparser webreq = new Jsonparser();
            // String url1=arg0[0];
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDD "+url1);
            // Making a request to url and getting response
            String jsonStr = webreq.makeWebServiceCall(url1, Jsonparser.POSTRequest);
            Log.d("Response: ", "> " + jsonStr);
            // String  student = ParseJSON(jsonStr);
            System.out.println("|||||||||||||||"+jsonStr);


            try {
                 JSONObject jsonObj = new JSONObject(jsonStr);
                 JSONArray students = jsonObj.getJSONArray("results");
                 destinylatlngstatus=jsonObj.getString("status");
                 JSONObject c = students.getJSONObject(0);
                 JSONObject d =c.getJSONObject("geometry");
                 JSONObject e = d.getJSONObject("location");
                 destinylatitute = e.getString("lat");
                 destinylongitute= e.getString("lng");
                 System.out.println(destinylatitute+"+++++++++++++++"+destinylongitute);
            }
            catch (Exception e){
                System.out.println("SSSSSSSEEEEEXXXXXCCCCEEEEPPPPTTTTIIIOOONNNN");
            }
            return null;
        }


        protected void onPostExecute(String requestresult) {
            super.onPostExecute(requestresult);
// Dismiss the progress dialog
            if (proDialog.isShowing())
                proDialog.dismiss();
            if(sourcelatlngstatus.equalsIgnoreCase("Ok")&&destinylatlngstatus.equalsIgnoreCase("OK"))
            new GetRoute().execute();
            else{
                Toast.makeText(getApplicationContext(), "Please Enter Valid Place",
                        Toast.LENGTH_LONG).show();
            }

            // distance.setText("Total Distance is : "+distvalue);
            //  duration.setText("Total Time Taken : "+durvalue);
            //  if(Integer.valueOf(distext)<=2000)
            //     fare.setText("Approx Fare : Rs.25/-");
           /* else if(Integer.valueOf(distext)<=2500)
                fare.setText("Approx Fare : "+farevalue);*/
            // else{
            //      int price=Integer.valueOf(distext);
            //     int p1=price*12;
            //     int p2=p1/1000;
            //     farevalue= Integer.toString(p2);
            //     fare.setText("Approx Fare : Rs."+farevalue+"/-");
        }
/**         else if(Integer.valueOf(distext)<=2)
 * Updating received data from JSON into ListView
 * */
           /* ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, studentList,
                    R.layout.list_item, new String[]{TAG_STUDENT_NAME, TAG_EMAIL,
                    TAG_STUDENT_PHONE_MOBILE}, new int[]{R.id.name,
                    R.id.email, R.id.mobile});

            setListAdapter(adapter);*/
    }
    public class GetRoute extends AsyncTask<String,Void,String> {

        // Hashmap for ListView
        ArrayList<HashMap<String, String>> studentList;
        ProgressDialog proDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            proDialog = new ProgressDialog(GetRouteActivity.this);
            proDialog.setMessage("Please wait...");
            proDialog.setCancelable(false);
            proDialog.show();
        }
        @Override
        protected String doInBackground(String... arg0) {
            ///for source place
           // String url1 = "http://maps.googleapis.com/maps/api/geocode/json?address="+destiny+"kolkata,india&sensor=false%22";

            String url21 ="http://maps.google.com/maps/api/directions/json?origin="+sourcelatitute+"," +
                    ""+sourcelongitute+"&destination="+destinylatitute+"," +
                    ""+destinylongitute+"&sensor=false&&mode=driving&language=en-EN&units=metric";
            Jsonparser webreq21 = new Jsonparser();
            //String url1=arg0[0];
            System.out.println("ROUTE URL SSSSSSSSSSS "+url21);
            // Making a request to url and getting response
            String jsonStr21 = webreq21.makeWebServiceCall(url21, Jsonparser.POSTRequest);
            Log.d("Response: ", "> " + jsonStr21);
            // String  student = ParseJSON(jsonStr);
            System.out.println("|||||||||||||||"+jsonStr21);
            try {
                JSONObject jsonObj = new JSONObject(jsonStr21);
                JSONArray routes = jsonObj.getJSONArray("routes");
                JSONObject o = routes.getJSONObject(0);
                JSONArray legs =o.getJSONArray("legs");
                JSONObject o1 = legs.getJSONObject(0);
                JSONObject distance=o1.getJSONObject("distance");
                distext=distance.getString("text");
                JSONObject duration=o1.getJSONObject("duration");
                durtext=duration.getString("text");
                JSONArray steps=o1.getJSONArray("steps");
                path = new ArrayList<String>();
                for(int i=0;i<steps.length();i++)
                {
                    JSONObject items = steps.getJSONObject(i);
                    JSONObject distance2=items.getJSONObject("distance");
                    String subdistance=distance2.getString("text");
                    JSONObject duration2=items.getJSONObject("duration");
                    String subduration=duration2.getString("text");
                    String way=items.getString("html_instructions");
                    String travelmode=items.getString("travel_mode");
                    path.add(way.replaceAll("<\\/?[bi]>", "").replaceAll("[<](/)?div[^>]*[>]","") +" Distance "+subdistance+" Time Taken "+subduration);
                   // path.add(subdistance);
                   // path.add(subduration);
                }

               // System.out.println(way+"+++++++++++++++"+sourcelongitute);
            }
            catch (Exception e){
                System.out.println("DRECTIONEEEEEEEEXXXXXXXXXXXPPPPPPPPPPTTTIIIIOOOONNN");
            }
            ////end source place

            return null;
        }


        protected void onPostExecute(String requestresult) {
            super.onPostExecute(requestresult);
// Dismiss the progress dialog
            if (proDialog.isShowing())
                proDialog.dismiss();
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    GetRouteActivity.this,
                    android.R.layout.simple_list_item_1,path);

            routeway.setAdapter(arrayAdapter);
            toptext.setText("Source: "+source.replaceAll("%20"," ")+", Destination: "+destiny.replaceAll("%20"," ")+", Total Duration "+durtext+", Total Distance "+distext);
            // new GetRoute().execute();
            // distance.setText("Total Distance is : "+distvalue);
            //  duration.setText("Total Time Taken : "+durvalue);
            //  if(Integer.valueOf(distext)<=2000)
            //     fare.setText("Approx Fare : Rs.25/-");
           /* else if(Integer.valueOf(distext)<=2500)
                fare.setText("Approx Fare : "+farevalue);*/
            // else{
            //      int price=Integer.valueOf(distext);
            //     int p1=price*12;
            //     int p2=p1/1000;
            //     farevalue= Integer.toString(p2);
            //     fare.setText("Approx Fare : Rs."+farevalue+"/-");
        }
/**         else if(Integer.valueOf(distext)<=2)
 * Updating received data from JSON into ListView
 * */
           /* ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, studentList,
                    R.layout.list_item, new String[]{TAG_STUDENT_NAME, TAG_EMAIL,
                    TAG_STUDENT_PHONE_MOBILE}, new int[]{R.id.name,
                    R.id.email, R.id.mobile});

            setListAdapter(adapter);*/
    }

}
