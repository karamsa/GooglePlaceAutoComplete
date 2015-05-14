package co.geeksters.googleplaceautocomplete.lib;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.SimpleAdapter;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import co.geeksters.googleplaceautocomplete.bus.BaseApplication;
import co.geeksters.googleplaceautocomplete.bus.events.GooglePlaceEvent;
import co.geeksters.googleplaceautocomplete.services.GooglePlacesService;

/**
 * Created by hero3 on 22/12/14.
 */
public class CustomTextWatcher implements TextWatcher {

    private GooglePlacesService googlePlacesService = new GooglePlacesService();
    Timer timer = new Timer();
    long DELAY; // in ms

    CustomAutoCompleteTextView atv;

    public CustomTextWatcher(CustomAutoCompleteTextView atv){

        BaseApplication.getInstance().unregisterAll();
        BaseApplication.getInstance().register(this);

        this.atv = atv;
        DELAY = this.atv.delay;

        List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();

        makeList(result);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count){
        final String inputText = s.toString();
        timer.cancel();

        if(s.length() >= 3) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    googlePlacesService.getGooglePlaces(inputText.toString(), atv.type, atv.sonsor, atv.apiKey);
                }
            }, DELAY);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub
    }

    @Subscribe
    public void onResult(GooglePlaceEvent googlePlaceEvent) {
        atv.googlePlaces = googlePlaceEvent.googlePlaces;

        List<HashMap<String, String>> result = GooglePlacesJsonParser.getInstance().getPlaces(googlePlaceEvent.googlePlaces);
        makeList(result);

        Log.d("Fill The autoCompleteList","_");
    }

    public void makeList(List<HashMap<String, String>> result){
        String[] from = new String[] { "description"};
        int[] to = new int[] { android.R.id.text1 };

        // Creating a SimpleAdapter for the AutoCompleteTextView
        SimpleAdapter adapter = new SimpleAdapter(atv.context, result, android.R.layout.simple_dropdown_item_1line, from, to);

        // Setting the adapter
        atv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
