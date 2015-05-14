package co.geeksters.googleplaceautocomplete.bus.events;

import android.util.Log;

import java.util.ArrayList;

import co.geeksters.googleplaceautocomplete.lib.GooglePlace;

/**
 * Created by hero3 on 19/12/14.
 */
public class GooglePlaceEvent {

    public ArrayList<GooglePlace> googlePlaces;

    //GooglePlace

    public GooglePlaceEvent(ArrayList<GooglePlace> googlePlaces){
        Log.d("init google place event", "Event class");
        this.googlePlaces = googlePlaces;
    }
}
