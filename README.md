# Android GooglePlaceAutoComplete 

GooglePlaceAutoComplete is a module that you can integrate in your android app to make google places autocomplete easly.

### Installation:

1) Go to:
  
File -> Import Module -> choose the folder "GooglePlaceAutoComplete".

2) add this to your gradle file :

	compile project(':googleplaceautocomplete')

### Usages:

In your xml import an extra namespace on the root of your layout, like this:

	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:whatever="http://schemas.android.com/apk/res-auto"
	    android:layout_width="match_parent"
	    android:layout_height="wrap_content" >
	    ....
	    <!-- Your actual layout -->
	    ....
	</LinearLayout>
	
Whenever you need to use the GooglePlaceAutoComplete just do the following in your xml.

	<co.geeksters.googleplaceautocomplete.lib.CustomAutoCompleteTextView
	   android:id="@+id/atv_places"
	   android:layout_width="match_parent"
	   android:layout_height="wrap_content"

	   whatever:key="Your application's API key"
	   whatever:type="(cities)"
	   whatever:sensor="true"
	   whatever:delay="700" />

In your activity, you can get result like this: 

	CustomAutoCompleteTextView customAutoCompleteTextView = (CustomAutoCompleteTextView)findViewById(R.id.atv_places)

	customAutoCompleteTextView.googlePlace.getCountry(); //Return the country name
	customAutoCompleteTextView.googlePlace.getCity(); //Return the city
	customAutoCompleteTextView.googlePlace.getDescription(); //Return the description (city + region + country)
	...

Attributes definition:

key: Your application's API key. 
type: Restricts the results to places matching of the specified types.
delay: Time (millisecondes) before start searching.



