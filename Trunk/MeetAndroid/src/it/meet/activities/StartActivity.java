package it.meet.activities;

import it.meet.R;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

public class StartActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);


        int secondsDelayed = 1;
        
        //faccio un controllo sui servizi presenti sul dispositivo
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	    	Log.w("Servizio",service.service.getClassName());
	    }
		
	    //avvio il MainActivity dopo 2 secondi
        new Handler().postDelayed(new Runnable() {
                public void run() {
                        startActivity(new Intent(StartActivity.this, MainActivity.class));
                        finish();
                }
        }, secondsDelayed * 2000);
    }
}