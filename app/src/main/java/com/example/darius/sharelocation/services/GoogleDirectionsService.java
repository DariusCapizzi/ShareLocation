package com.example.darius.sharelocation.services;

import com.example.darius.sharelocation.Constants;
import com.example.darius.sharelocation.models.Direction;
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
                .addQueryParameter(Constants.DESTINATIONP, destination)
                .addQueryParameter(Constants.KEYP, Constants.KEY);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Direction> processResults(Response response) {
        ArrayList<Direction> directions = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {

                JSONObject yelpJSON = new JSONObject(jsonData);
                JSONArray placesJSON = yelpJSON.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
//                Log.d(TAG, "Results: "+ placesJSON);

                for (int i = 0; i < placesJSON.length(); i++) {

                    JSONObject directionJSON = placesJSON.getJSONObject(i);

                    String distance = directionJSON.getJSONObject("distance").getString("text");
                    String duration = directionJSON.getJSONObject("duration").getString("text");
                    String htmlInstruction = directionJSON.getString("html_instructions");

                    Direction direction = new Direction(distance, duration, htmlInstruction);
                    directions.add(direction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return directions;
    }
}
