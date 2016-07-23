package com.example.darius.sharelocation.services;

import android.util.Log;

import com.example.darius.sharelocation.Constants;
import com.example.darius.sharelocation.models.Route;
import com.example.darius.sharelocation.models.Step;
import com.example.darius.sharelocation.ui.MainActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class GoogleDirectionsService{
    public static final String TAG = MainActivity.class.getSimpleName();
    public static void findTrip(String origin, String destination, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GOOGLE_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.ORIGINP, origin)
                .addQueryParameter(Constants.ALTERNATIVESP, String.valueOf(true))
                .addQueryParameter(Constants.DESTINATIONP, destination)
                .addQueryParameter(Constants.KEYP, Constants.KEY);
        String url = urlBuilder.build().toString();
//        Log.d(TAG, "findTrip: "+ url);
        Request request= new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public ArrayList<Route> processResults(Response response) {
        ArrayList<Route> routes = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject initialJSON = new JSONObject(jsonData);

                JSONArray routesJSON = initialJSON.getJSONArray("routes");
                for(int i = 0; i < routesJSON.length(); i++) {


                    String startAddress = routesJSON.getJSONObject(i).getJSONArray("legs").getJSONObject(0).getString("start_address");
                    String endAddress = routesJSON.getJSONObject(i).getJSONArray("legs").getJSONObject(0).getString("end_address");

                    String startCoordinates = routesJSON.getJSONObject(i).getJSONArray("legs").getJSONObject(0).getJSONObject("start_location").getString("lat") + ","+ routesJSON.getJSONObject(i).getJSONArray("legs").getJSONObject(0).getJSONObject("start_location").getString("lng");
                    String endCoordinates = routesJSON.getJSONObject(i).getJSONArray("legs").getJSONObject(0).getJSONObject("end_location").getString("lat") + ","+ routesJSON.getJSONObject(i).getJSONArray("legs").getJSONObject(0).getJSONObject("end_location").getString("lng");


                    String summary = routesJSON.getJSONObject(i).getString("summary");

                    String distance = routesJSON.getJSONObject(i).getJSONArray("legs").getJSONObject(0).getJSONObject("distance").getString("text");
                    String duration = routesJSON.getJSONObject(i).getJSONArray("legs").getJSONObject(0).getJSONObject("duration").getString("text");

    //                Log.d(TAG, "processResults:"+ initialJSON.getJSONArray("routes").length());
                    Route route = new Route(startAddress, endAddress, summary, distance, duration, startCoordinates, endCoordinates);

                    JSONArray stepsJSON = routesJSON.getJSONObject(i).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
                    for (int j = 0; j < stepsJSON.length(); j++) {
                        JSONObject directionJSON = stepsJSON.getJSONObject(j);
                        String stepDistance = directionJSON.getJSONObject("distance").getString("text");
                        String stepDuration = directionJSON.getJSONObject("duration").getString("text");
                        String htmlInstruction = directionJSON.getString("html_instructions");
                        Step step = new Step(stepDistance, stepDuration, htmlInstruction);
                        step.setParentRoute(route);
                        route.getStepArray().add(step);
                    }

                    routes.add(route);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return routes;
    }
}
