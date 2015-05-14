package co.geeksters.googleplaceautocomplete.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hero3 on 19/12/14.
 */
public class GooglePlace {

    String description;
    String id;

    String place_id;
    String reference;

    ArrayList<String> terms;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public ArrayList<String> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<String> terms) {
        this.terms = terms;
    }

    public static GooglePlace findPlaceById(String id, List<GooglePlace> googlePlaces) {
        for (int i = 0; i < googlePlaces.size(); i++) {
            if (googlePlaces.get(i).getId().compareToIgnoreCase(id)==0){
                return googlePlaces.get(i);
            }
        }
        return null;
    }

    public String getCity(){
        return getTerms().get(0).toString();
    }

    public String getCountry(){
        return getTerms().get(getTerms().size()-1).toString();
    }
}
