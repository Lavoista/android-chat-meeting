package it.meet.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import it.meet.GCMUpper.GCMRegistrarCompat;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import tom.meets.BuildConfig;
import tom.meets.R;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class SplashConInvio extends Activity {
	public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    
    /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
    static final String SENDER_ID="130308147225";


    /**
     * Tag used on log messages.
     */
    static final String TAG = "GCM Demo";

    TextView mDisplay;
    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    Context context;

    String regid;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        
        context = getApplicationContext();
        
        GCMRegistrarCompat.checkDevice(this);
      
	    Log.w("TOM","tra 2 secondi INVIO MESSAGGIO AL SERVER");
	    try{
	    	this.wait(4000);
	    }
	    catch(Exception k){
	    	
	    }
        if (BuildConfig.DEBUG) {
          GCMRegistrarCompat.checkManifest(this);
        }
        final String regId=GCMRegistrarCompat.getRegistrationId(this);
        System.out.println("regId = "+regId);
        if (regId.length() == 0) {
            new RegisterTask(this).execute(SENDER_ID);
          }
          else {
            Log.d(getClass().getSimpleName(), "Existing registration: "
                + regId);
            Toast.makeText(this, regId, Toast.LENGTH_LONG).show();
          }
        
        //faccio un controllo sui servizi presenti sul dispositivo
        
        /*ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	    	Log.w("Servizio",service.service.getClassName());
	    }
		*/
	    /*lascio l'activity corrente splash
	    //avvio il MainActivity dopo 2 secondi
        new Handler().postDelayed(new Runnable() {
                public void run() {
                        startActivity(new Intent(SplashConInvio.this, MainActivity.class));
                        finish();
                }
        }, secondsDelayed * 2000);
        */
	    
	    
	    /*
	    new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    Bundle data = new Bundle();
                    data.putString("my_message", "Hello World");
                    data.putString("my_action", "tom.meets.ECHO_NOW");
                    String id = Integer.toString(msgId.incrementAndGet());
                    gcm.send(SENDER_ID + "@gcm.googleapis.com", id, data);
                    msg = "invioMessaggio";
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                //PROVA mDisplay.append(msg + "\n");
                Log.w("Completata operazione invio messaggio",msg);
            }
        }.execute(null, null, null);
        */
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private static class RegisterTask extends
    GCMRegistrarCompat.BaseRegisterTask {

  RegisterTask(Context context) {
    super(context);
  }

  @Override
  public void onPostExecute(String regid) {
    Log.d(getClass().getSimpleName(), "registered as: " + regid);
    Toast.makeText(context, regid, Toast.LENGTH_LONG).show();
  }
}
    
}