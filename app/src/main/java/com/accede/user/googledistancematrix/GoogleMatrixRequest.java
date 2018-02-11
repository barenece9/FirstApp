package com.accede.user.googledistancematrix;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by User on 29-06-2016.
 */
public class GoogleMatrixRequest {
    private static final String API_KEY = "AIzaSyCz4h0e7T_8bgDOm5zQG8dxBZlQTtrONGE";

    OkHttpClient client;
    public void call(){
         client= new OkHttpClient();
       // GoogleMatrixRequest request = new GoogleMatrixRequest();
        String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC%7CSeattle&destinations=San+Francisco%7CVictoria+BC&mode=bicycling&language=fr-FR&key=" + API_KEY;
        try {
            String response = run(url_request);
            System.out.println("YYYYYYYYYYYYYYWWWWWWWWWWWWW"+response);
        }catch (Exception e){
            System.out.println("EECCCCEEEEPPPPTTTTIIIIOOONNNN");
        }


    }

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

   /* public static void main(String[] args) throws IOException {
        GoogleMatrixRequest request = new GoogleMatrixRequest();
        String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC%7CSeattle&destinations=San+Francisco%7CVictoria+BC&mode=bicycling&language=fr-FR&key=" + API_KEY;

        String response = request.run(url_request);
        System.out.println("YYYYYYYYYYYYYYWWWWWWWWWWWWW"+response);
    }*/
}
