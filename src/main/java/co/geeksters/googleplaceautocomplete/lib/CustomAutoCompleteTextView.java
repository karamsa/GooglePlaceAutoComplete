package co.geeksters.googleplaceautocomplete.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import java.util.HashMap;
import java.util.List;

import co.geeksters.googleplaceautocomplete.R;

/**
 * Created by hero3 on 19/12/14.
 */
public class CustomAutoCompleteTextView extends AutoCompleteTextView {
    Context context;

    String type = "geocode";
    String sonsor = "false";
    String apiKey = "";
    int delay = 600;

    public List<GooglePlace> googlePlaces;

    public GooglePlace googlePlace;

    public CustomAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.googleplaces, 0, 0);

        try {
            type = ta.getString(R.styleable.googleplaces_type);
            sonsor = ta.getString(R.styleable.googleplaces_sensor);
            apiKey = ta.getString(R.styleable.googleplaces_key);
            delay = ta.getInteger(R.styleable.googleplaces_delay, delay);
        } finally {
            ta.recycle();
        }

        this.context = context;

        this.setThreshold(1);

        this.addTextChangedListener(new CustomTextWatcher(this));
    }

    /** Returns the place description corresponding to the selected item */
    @Override
    protected CharSequence convertSelectionToString(Object selectedItem) {
        /** Each item in the autocompetetextview suggestion list is a hashmap object */
        HashMap<String, String> hm = (HashMap<String, String>) selectedItem;
        googlePlace =  GooglePlace.findPlaceById(hm.get("id"), this.googlePlaces);
        return hm.get("description");
    }


}