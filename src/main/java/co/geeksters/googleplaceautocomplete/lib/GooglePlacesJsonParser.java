package co.geeksters.googleplaceautocomplete.lib;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hero3 on 22/12/14.
 */
public class GooglePlacesJsonParser {

    private static GooglePlacesJsonParser INSTANCE = null;

    public static GooglePlacesJsonParser getInstance(){
        if (INSTANCE == null){
            INSTANCE = new GooglePlacesJsonParser();
        }
        return INSTANCE;
    }

    public ArrayList<GooglePlace> placesFromJson(JsonObject jsonObject) {
        ArrayList<GooglePlace> googlePlaces = new ArrayList<GooglePlace>();
        JsonArray jsonArray = null;

        jsonArray = jsonObject.getAsJsonArray("predictions");

        for (int i = 0; i < jsonArray.size(); i++) {
            googlePlaces.add(placeFromJson(jsonArray.get(i).getAsJsonObject()));
        }

        return googlePlaces;
    }

    public GooglePlace placeFromJson(JsonObject jsonObject) {
        GooglePlace googlePlaces = new GooglePlace();

        googlePlaces.setDescription(jsonObject.get("description").getAsString());
        googlePlaces.setId(jsonObject.get("id").getAsString());
        googlePlaces.setPlace_id(jsonObject.get("place_id").getAsString());
        googlePlaces.setReference(jsonObject.get("reference").getAsString());

        JsonArray jsonArray = jsonObject.get("terms").getAsJsonArray();

        ArrayList<String> termsArray = new ArrayList<String>();
        for (int i = 0; i < jsonArray.size(); i++) {
            termsArray.add(jsonArray.get(i).getAsJsonObject().get("value").getAsString());
        }
        googlePlaces.setTerms(termsArray);

        return googlePlaces;
    }


    public List<HashMap<String, String>> getPlaces(ArrayList<GooglePlace> googlePlaces){

        List<HashMap<String, String>> placesList = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> place = null;

        /** Taking each place, parses and adds to list object */
        for(int i=0; i<googlePlaces.size();i++){
            /** Call getPlace with place JSON object to parse the place */
            place = getPlace(googlePlaces.get(i));
            placesList.add(place);
        }

        return placesList;
    }

    /** Parsing the Place JSON object */
    public HashMap<String, String> getPlace(GooglePlace googlePlace){

        HashMap<String, String> place = new HashMap<String, String>();

        String id="";
        String reference="";
        String description="";

        description = googlePlace.getDescription();
        id = googlePlace.getId();
        reference = googlePlace.getReference();

        place.put("description", description);
        place.put("id",id);
        place.put("reference",reference);

        return place;
    }

}
