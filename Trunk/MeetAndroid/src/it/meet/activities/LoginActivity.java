package it.meet.activities;

import it.meet.R;
import it.meet.activities.MainActivity.PlanetFragment;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		try {
			EditText campoUtente = (EditText) findViewById(R.id.editText1);
			campoUtente.setImeOptions(EditorInfo.IME_ACTION_NEXT);

			Button buttonOne = (Button) findViewById(R.id.Button01);

			buttonOne.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					startActivity(new Intent(LoginActivity.this,
							RegistrationActivity.class));

				}
			});
		} catch (InflateException e) {
			/* map is already there, just return view as it is */

		}
	}
}