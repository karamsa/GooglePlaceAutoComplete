package co.geeksters.googleplaceautocomplete.services;

import android.util.Log;

import com.google.gson.JsonElement;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.geeksters.googleplaceautocomplete.bus.BaseApplication;
import co.geeksters.googleplaceautocomplete.bus.events.GooglePlaceEvent;
import co.geeksters.googleplaceautocomplete.interfaces.GooglePlacesInterface;
import co.geeksters.googleplaceautocomplete.lib.GooglePlace;
import co.geeksters.googleplaceautocomplete.lib.GooglePlacesJsonParser;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.MainThreadExecutor;
import retrofit.client.Response;

/**
 * Created by hero3 on 19/12/14.
 */
public class GooglePlacesService {

    private GooglePlacesInterface api;
    private ExecutorService mExecutorService;
    RestAdapter restAdapter;
    public GooglePlacesService(){
        startService();
    }

    public void getGooglePlaces(String inputString, String type,  String sensor,  String key) {
        stopAll();
        String input = "";
        try {
            input =  URLEncoder.encode(inputString.trim(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.api.listPlaces("json", input, type, sensor, key, new Callback<JsonElement>() {

            @Override
            public void success(JsonElement jsonObject, Response response) {

                Log.d("success", jsonObject.toString());
                ArrayList<GooglePlace> googlePlaces = GooglePlacesJsonParser.getInstance().placesFromJson(jsonObject.getAsJsonObject());
                BaseApplication.getInstance().post(new GooglePlaceEvent(googlePlaces));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("on fail google place", "");
            }
        });
    }

    public void stopAll(){
        mExecutorService.shutdownNow();
        //mExecutorService.shutdown();
        // probably await for termination.
        startService();
    }

    public void startService(){
        mExecutorService = Executors.newCachedThreadPool();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://maps.googleapis.com/maps/api")
                .setExecutors(mExecutorService, new MainThreadExecutor())
                .build();
        this.api = restAdapter.create(GooglePlacesInterface.class);
    }



}
