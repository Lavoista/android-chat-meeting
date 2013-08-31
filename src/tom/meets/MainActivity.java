package tom.meets;

import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity {
	private GoogleMap mMap;
	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		MapListener mapListener = new MapListener(this.getBaseContext());
		System.out.println("mMap="+mMap);
		mMap.setOnCameraChangeListener(mapListener);
		System.out.println("mMap2="+mMap);
		

		/*
		mContext = getBaseContext().execute(latLng);
		Geocoder geoCoder = new Geocoder(mContext);
		List<Address> addresses = geoCoder.getFromLocation(-33.796923, 150.922433,1);
		 */
	}


}
