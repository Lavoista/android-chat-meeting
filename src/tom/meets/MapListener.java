package tom.meets;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapListener implements OnCameraChangeListener{
	MarkerOptions markerOptions;
	Context context;
	/*costruttore
	public MapListener(){
		System.out.println("Mappa costruita");
	}
	*/
	public MapListener(Context _context){
		context = _context;
	}
	

	@Override
	public void onCameraChange(CameraPosition position) {
		// TODO Auto-generated method stub
		Log.w("Classe1","Mappa cambiata "+position.target);	
		markerOptions = new MarkerOptions();
		markerOptions.position(position.target);
		new ReverseGeocodingTask(context).execute(position.target);
		
	}
}

	
