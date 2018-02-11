package com.accede.user.testapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.accede.user.googledistancematrix.GoogleMatrixRequest;
import com.accede.user.googledistancematrix.Jsonparser;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Where2goActivity extends Activity {
    Button getfare,getroute,showroute;
    TextView displayfare,distance,duration,fare,cityname;
    AutoCompleteTextView autocompletesource,autocompletedestiny;
    String city;
    String sourcestatus="",disstatus="";
    private static final String API_KEY = "AIzaSyD-wiGxo9-oAqBg254N7iza688yfTv9NaQ";
    GoogleMatrixRequest request;
    String[] arr = {"AJC Bose Road", "APC Road", "Amherst Street", "Aurobinda Sarani",
            "Ballygunge Circular Road","Bangur Avenue","Barrackpore Trunk Road",
            "Bepin Behari Ganguly Street","Bidhan Sarani","Camac Street",
            "Chittaranjan Avenue","College Street","E.M. Bypass","Garia Main Road",
            "Gariahat Road","Grey Street Road","Gurusaday Dutta Road",
            "Jawaharlal Nehru Road","Jessore Road","Jodhpur Park","Lalbazar",
            "Lindsay Street","Lower Circular Road","Mahatma Gandhi Road",
            "Mirza Ghalib Street","Netaji Subash Chandra Bose Road","Netaji Subhas Road",
            "Park Street","Prince Anwar Shah Road","Raja Nabakrishna Street",
            "Raja SC Mullick Road","Rashbehari Avenue","Sarat Bose Road",
            "Shakespeare Sarani","Southern Avenue","Strand Road","Sudder Street",
            "VIP Road","Vivekananda Road","sarsuna,behala","chowrasta,behala",
            "behala tramdepot","sakher bazar,behala","silpara,behala","thakurpukur",
            "bakhrahat road","joka","pailan","kalitala","sakuntala park,behala",
            "shibrampur,behala","taratala","budge budge","bata nagar","brace bridge",
            "majherhat","mominpur","ekbalpur","Kidderpore","khidirpur","race cource",
            "esplanade,kolkata","tollygunge","ranikuthi","bansdroni","garia station road",
            "garia","patuli","jadavpur","baghajatin","jodhpur park","dhakuria","golpark",
            "ballygunge","kasba","haltu","rubi park","kalighat","hazra","deshapriya park",
            "bhowanipore","rabidra sadan","minto park","park circus","chandni chowk",
            "bbd bag","sealdah","howrah railway station","belur","bara bazar kolkata",
            "lake gardens","garden reach","rabindra sarobar","tolllygunge phari",
            "tolllygunge metro station","mahabirtala","new alipore","chetla","red road",
            "babughat","maidan","shyambazar","shobhabazar","girish park","central",
            "raja bazar","Dum Dum","kankurgachi","bidhannagar","lake town","kestopur",
            "baguiati kolkata","dumdum airport","sealdah railway station","metiabruz",
            "salt lake,sector 1","salt lake,sector 5","salt lake,sector 3","salt lake",
            "rajarhat","salt lake,sector 2","haridevpur","tangra","topsia","Bagbazar",
            "Beleghata","Narkeldanga","Entally","Circus Avenue","Fort William","Hastings",
            "Alipore","Sarat Bose Road","Baranagar","Belgachia","Naktala","Shreebhumi",
            "Bangur Avenue","Belghoria","Parnasree","Rabindranagar","Middleton Row",
            "Prinsep Street","Dakshineshwar","Golf Green","Kalikapur","science city",
            "Narendrapur","Dhapa","Malancha,tollygunge","G.P.O","jorasanko","Princep Ghat",
            "Nicco Park","Salt Lake Stadium","Chowringhee","Ajoy Nagar","golf green",
            "victoria memorial"};
    String[] arr1={};

    String distext,distvalue="",durtext,durvalue="",farevalue="";
    //public static  String source="";
    //public static  String  distiny="";
    //public  static String url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="+source+"Kolkata|WB&destinations="+distiny+"Kolkata|WB&mode=driving&language=en-EN&sensor=false";
   // String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Dumdum+Kolkata|WestBengal&destinations=SaltLake+Kolkata|WestBengal&mode=driving&language=en&key=" + API_KEY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_where2go);
        Intent intent=getIntent();
        city = intent.getStringExtra("city");
        getroute=(Button)findViewById(R.id.btngetroute);
        getfare=(Button)findViewById(R.id.btngetfare);
        showroute=(Button)findViewById(R.id.btnshowroute);
        displayfare=(TextView)findViewById(R.id.textfare);
        distance=(TextView)findViewById(R.id.textdistance);
        duration=(TextView)findViewById(R.id.textduration);
        fare=(TextView)findViewById(R.id.textfare);
        cityname=(TextView)findViewById(R.id.displaycity);
        cityname.setText("City Name : "+city);
        request = new GoogleMatrixRequest();
        autocompletesource = (AutoCompleteTextView)
                findViewById(R.id.autoCompletesource);
        autocompletedestiny = (AutoCompleteTextView)
                findViewById(R.id.autoCompletedestiny);


        if(city.equalsIgnoreCase("Kolkata")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, android.R.layout.select_dialog_item, arr);
            autocompletesource.setThreshold(2);
            autocompletesource.setAdapter(adapter);


            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                    (this, android.R.layout.select_dialog_item, arr);
            autocompletedestiny.setThreshold(2);
            autocompletedestiny.setAdapter(adapter2);
        }
        else{
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, android.R.layout.select_dialog_item, arr1);
            autocompletesource.setThreshold(2);
            autocompletesource.setAdapter(adapter);


            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                    (this, android.R.layout.select_dialog_item, arr1);
            autocompletedestiny.setThreshold(2);
            autocompletedestiny.setAdapter(adapter2);

        }

        getfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String source=autocompletesource.getText().toString().trim().replaceAll(" ","%20");
                String distiny=autocompletedestiny.getText().toString().trim().replaceAll(" ","%20");
                String source1=source.trim();
                String distiny1=distiny.trim();

               //  String url = "http://maps.googleapis.com/maps/api/distancematrix/" +
                   //     "json?origins="+source1.trim()+"Kolkata|WB&destinations="+distiny1.trim()+"Kolkata|WB&mode=driving&language=en-EN&sensor=false";

                String url = "http://maps.googleapis.com/maps/api/distancematrix/" +
                        "json?origins="+source1.trim()+""+city+"|India&destinations="+distiny1.trim()+""+city+"|India&mode=driving&language=en-EN&sensor=false";


                System.out.println(source.trim()+" "+distiny.trim());
                   // request.call();
               /* if(!source.equalsIgnoreCase(null)||distiny.equalsIgnoreCase(null)||
                        source.equalsIgnoreCase("")||distiny.equalsIgnoreCase("")||
                source.equalsIgnoreCase("%20")||distiny.equalsIgnoreCase("%20"))
                new GetStudents().execute(url);
                else
                    Toast.makeText(getApplicationContext(), "Please Enter The Place",
                            Toast.LENGTH_LONG).show();*/

                if(TextUtils.isEmpty(source)) {
                    autocompletesource.setError("Enter Source Place");
                    return;
                }
                else if (TextUtils.isEmpty(distiny)) {
                    autocompletedestiny.setError("Enter Destiny Place");
                    return;
                }
                else{
                    new GetStudents().execute(url);
                }
              // request.call();
               /* Location locationA = new Location("point A");

                locationA.setLatitude(17.372102);
                locationA.setLongitude(78.484196);

                Location locationB = new Location("point B");

                locationB.setLatitude(17.375775);
                locationB.setLongitude(78.469218);

                float distance = locationA.distanceTo(locationB);
               // displayfare.setText();
                System.out.println("8888888888888"+distance);*/

            }
        });
        getroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String source=autocompletesource.getText().toString().trim().replaceAll(" ","%20");
                String distiny=autocompletedestiny.getText().toString().trim().replaceAll(" ","%20");
                String source1=source.trim();
                String distiny1=distiny.trim();
                if(TextUtils.isEmpty(source)) {
                    autocompletesource.setError("Enter Source Place");
                    return;
                }
                else if (TextUtils.isEmpty(distiny)) {
                    autocompletedestiny.setError("Enter Destiny Place");
                    return;
                }
                else {
                    Intent intent = new Intent(Where2goActivity.this, GetRouteActivity.class);
                    intent.putExtra("source", source);
                    intent.putExtra("destiny", distiny);
                    intent.putExtra("city", city);
                    startActivity(intent);
                    System.out.println("ppppppppppppppppppppppppppppppppp");
                }

            }
        });//behala tramdepot
        showroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String source=autocompletesource.getText().toString().trim().replaceAll(" ","%20");
                String distiny=autocompletedestiny.getText().toString().trim().replaceAll(" ","%20");
                String source1=source.trim();
                String distiny1=distiny.trim();
                if(TextUtils.isEmpty(source)) {
                    autocompletesource.setError("Enter Source Place");
                    return;
                }
                else if (TextUtils.isEmpty(distiny)) {
                    autocompletedestiny.setError("Enter Destiny Place");
                    return;
                }
                else {
                    Intent intent = new Intent(Where2goActivity.this, ShowRoute.class);
                    intent.putExtra("source", source);
                    intent.putExtra("destiny", distiny);
                    intent.putExtra("city", city);
                    startActivity(intent);
                }
            }
        });
    }
    public class GetStudents extends AsyncTask<String,Void,String> {

        // Hashmap for ListView
        ArrayList<HashMap<String, String>> studentList;
        ProgressDialog proDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
// Showing progress loading dialog
            proDialog = new ProgressDialog(Where2goActivity.this);
            proDialog.setMessage("Please wait...");
            proDialog.setCancelable(false);
            proDialog.show();
        }
        @Override
        protected String doInBackground(String... arg0) {
// Creating service handler class instance
            Jsonparser webreq = new Jsonparser();
            String url1=arg0[0];
            System.out.println("75229470 "+url1);
// Making a request to url and getting response
            String jsonStr = webreq.makeWebServiceCall(url1, Jsonparser.POSTRequest);

            Log.d("Response: ", "> " + jsonStr);

           // String  student = ParseJSON(jsonStr);
            System.out.println("|||||||||||||||"+jsonStr);
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                JSONArray students = jsonObj.getJSONArray("rows");
                JSONObject c = students.getJSONObject(0);
                JSONArray d =c.getJSONArray("elements");
                JSONObject e = d.getJSONObject(0);
                disstatus=e.getString("status");
                JSONObject f=d.getJSONObject(1);
                sourcestatus=f.getString("status");
                JSONObject distance = e.getJSONObject("distance");
                JSONObject duration = e.getJSONObject("duration");
                 distext = distance.getString("value");
                 distvalue = distance.getString("text");
                 durtext = duration.getString("value");
                 durvalue= duration.getString("text");
                System.out.println(distext+"+++++++++++++++"+durtext);
            }
            catch (Exception e){

            }
            return null;
        }


        protected void onPostExecute(String requestresult) {
            super.onPostExecute(requestresult);
// Dismiss the progress dialog
            if (proDialog.isShowing())
                proDialog.dismiss();
            if(city.equalsIgnoreCase("Kolkata")) {
                if (sourcestatus.equalsIgnoreCase("OK") && disstatus.equalsIgnoreCase("OK")) {
                    distance.setText("Total Distance is : " + distvalue);
                    duration.setText("Total Time Taken : " + durvalue);
                    if (Integer.valueOf(distext) <= 2000)
                        fare.setText("Approx Fare : Rs.25/-");
           /* else if(Integer.valueOf(distext)<=2500)
                fare.setText("Approx Fare : "+farevalue);*/
                    else {
                        int price = Integer.valueOf(distext);
                        int p1 = price * 12;
                        int p2 = p1 / 1000;
                        farevalue = Integer.toString(p2);
                        fare.setText("Approx Fare : Rs." + farevalue + "/-");
                    }
                } else {
                    distance.setText("Total Distance is : 0");
                    duration.setText("Total Time Taken : 0");
                    fare.setText("Approx Fare : Rs.0/-");
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Input",
                            Toast.LENGTH_LONG).show();
                }
            }
            else {
                if (sourcestatus.equalsIgnoreCase("OK") && disstatus.equalsIgnoreCase("OK")) {
                    distance.setText("Total Distance is : " + distvalue);
                    duration.setText("Total Time Taken : " + durvalue);
                   // if (Integer.valueOf(distext) <= 2000)
                        fare.setText("Approx Fare : Not Found/-");
           /* else if(Integer.valueOf(distext)<=2500)
                fare.setText("Approx Fare : "+farevalue);*/
                   /* else {
                        int price = Integer.valueOf(distext);
                        int p1 = price * 12;
                        int p2 = p1 / 1000;
                        farevalue = Integer.toString(p2);
                        fare.setText("Approx Fare : Rs." + farevalue + "/-");
                    }*/
                } else {
                    distance.setText("Total Distance is : 0");
                    duration.setText("Total Time Taken : 0");
                    fare.setText("Approx Fare : Rs.0/-");
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Input",
                            Toast.LENGTH_LONG).show();
                }
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
}
