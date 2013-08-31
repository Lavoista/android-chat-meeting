package tom.meets;

import java.io.IOException;
import java.util.List;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.maps.model.LatLng;

public class ReverseGeocodingTask extends AsyncTask<LatLng, Void, String>{
	    Context mContext;
	    public ReverseGeocodingTask(Context context){
        super();
        mContext = context;
        }
	    // Finding address using reverse geocoding
	    @Override
	    protected String doInBackground(LatLng... params) {
	        Geocoder geocoder = new Geocoder(mContext);
	        double latitude = params[0].latitude;
	        double longitude = params[0].longitude;
	        List<Address> addresses = null;
	        String addressText="";
	        try {
	            addresses = geocoder.getFromLocation(latitude, longitude,1);
	            Thread.sleep(500);


	        if(addresses != null && addresses.size() > 0 ){
	            Address address = addresses.get(0);
	            addressText = String.format("%s, %s, %s",
	                    address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
	                            address.getLocality(),
	                            address.getCountryName());
	            }
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        //selectedLocAddress = addressText;
	        Log.w("Citta",addressText);
	        return addressText;
	    }

	    /*
	    @Override
	    protected void onPostExecute(String addressText) {
	        // Setting the title for the marker.
	        // This will be displayed on taping the marker
	        markerOptions.title(addressText);
	        // Placing a marker on the touched position
	        myMap.addMarker(markerOptions);
	    }*/

}
