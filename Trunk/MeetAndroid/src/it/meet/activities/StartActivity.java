package it.meet.activities;

import it.meet.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class StartActivity extends Activity {
	String PREFS_NAME = "MeetPreferFile";
	static SharedPreferences storedInfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		storedInfo = getSharedPreferences(PREFS_NAME, 0);
		
		int secondsDelayed = 2;// start new Activity after secondsDelayed
								// seconds
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (storedInfo.getString("loggedUser", "").isEmpty()) {
					startActivity(new Intent(StartActivity.this,
							LoginActivity.class));
				} else {
					startActivity(new Intent(StartActivity.this,
							MainActivity.class));
				}
				finish();
			}
		}, secondsDelayed * 1000);
	}

}