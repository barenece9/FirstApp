package com.accede.user.testapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.accede.user.googledistancematrix.DirectionJSONParser;
import com.accede.user.googledistancematrix.Jsonparser;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

@SuppressWarnings("ALL")
public class ShowRoute extends FragmentActivity {

    GoogleMap map;
    ArrayList<LatLng> markerPoints;
    String source="",destiny="",distext="",durtext="",city="";
    String sourcelatitute,sourcelongitute,destinylatitute,destinylongitute;
    String sourcelatlngstatus="",destinylatlngstatus="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route);
        Intent intent=getIntent();
        source = intent.getStringExtra("source");
        destiny= intent.getStringExtra("destiny");
        city=intent.getStringExtra("city");

        // Initializing
        markerPoints = new ArrayList<LatLng>();

        // Getting reference to SupportMapFragment of the activity_main
        SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting Map for the SupportMapFragment
        map = fm.getMap();

        if(map!=null){

            // Enable MyLocation Button in the Map
            map.setMyLocationEnabled(true);
            new Getlatlng().execute();

            // Setting onclick event listener for the map
           /* map.setOnMapClickListener(new OnMapClickListener() {

                @Override
                public void onMapClick(LatLng point) {

                    // Already two locations
                    if(markerPoints.size()>1){
                        markerPoints.clear();
                        map.clear();
                    }

                    // Adding new item to the ArrayList
                    markerPoints.add(point);

                    // Creating MarkerOptions
                    MarkerOptions options = new MarkerOptions();

                    // Setting the position of the marker
                    options.position(point);

                    *//**
                     * For the start location, the color of marker is GREEN and
                     * for the end location, the color of marker is RED.
                     *//*
                    if(markerPoints.size()==1){
                        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    }else if(markerPoints.size()==2){
                        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    }


                    // Add new marker to the Google Map Android API V2
                    map.addMarker(options);

                    // Checks, whether start and end locations are captured
                    if(markerPoints.size() >= 2){
                        LatLng origin = markerPoints.get(0);
                        LatLng dest = markerPoints.get(1);

                        // Getting URL to the Google Directions API
                        String url = getDirectionsUrl(origin, dest);

                        DownloadTask downloadTask = new DownloadTask();

                        // Start downloading json data from Google Directions API
                        downloadTask.execute(url);
                    }

                }
            });*/
        }
    }
    public class Getlatlng extends AsyncTask<String,Void,String> {

        // Hashmap for ListView
        ArrayList<HashMap<String, String>> studentList;
        ProgressDialog proDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            proDialog = new ProgressDialog(ShowRoute.this);
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
            {
                double slatitude = Double.parseDouble(sourcelatitute);
                double slongitude = Double.parseDouble(sourcelongitute);
                LatLng origin = new LatLng(slatitude, slongitude);
               // LatLng origin = markerPoints.get(0);
               // LatLng dest = markerPoints.get(1);
                double dlatitude = Double.parseDouble(destinylatitute);
                double dlongitude = Double.parseDouble(destinylongitute);
                LatLng dest = new LatLng(slatitude, slongitude);

                // Getting URL to the Google Directions API
                String url = getDirectionsUrl(origin, dest);

                DownloadTask downloadTask = new DownloadTask();

                // Start downloading json data from Google Directions API
                downloadTask.execute(url);
            }
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

    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;


        return url;
    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
           // Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }



    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>{

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionJSONParser parser = new DirectionJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);
                lineOptions.color(Color.RED);

            }

            // Drawing polyline in the Google Map for the i-th route
            map.addPolyline(lineOptions);
        }
    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
}