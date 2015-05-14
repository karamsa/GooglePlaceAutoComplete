package co.geeksters.googleplaceautocomplete.interfaces;

import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by hero3 on 19/12/14.
 */
public interface GooglePlacesInterface {


    @GET("/place/autocomplete/{output_type}")
    void listPlaces( @Path("output_type") String output_type, @Query("input") String input_string, @Query("types") String type, @Query("sensor") String sensor, @Query("key") String key, Callback<JsonElement> cb);
}
