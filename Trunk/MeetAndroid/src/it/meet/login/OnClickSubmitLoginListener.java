package it.meet.login;

import it.meet.R;
import it.meet.activities.LoginActivity;
import it.meet.activities.MainActivity;
import it.meet.activities.RegistrationActivity;
import it.meet.service.user.entity.UserDTO;
import it.meet.utils.ErrorsAdministrator;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class OnClickSubmitLoginListener implements OnClickListener {
	
	private String username = "";
	private String password = "";
	private ArrayList<String> emptyFields;
	private LoginActivity loginActivity;

	public OnClickSubmitLoginListener(
			LoginActivity loginActivity) {
		this.loginActivity = loginActivity;
	}

	@Override
	public void onClick(View v) {
		if (checkRegistrationFields()) {
			LoginTask rt = new LoginTask(loginActivity);
			rt.execute(username,password);
		} else {
			// send error to user
		}
	}

	private boolean checkRegistrationFields() {
		emptyFields = new ArrayList<String>();
		username = (((EditText) loginActivity.findViewById(R.id.LoginUsername)).getText()
				.toString());
		if (username.isEmpty()) {
			emptyFields.add("UserName");
		}
		password = (((EditText) loginActivity.findViewById(R.id.LoginPassword)).getText()
				.toString());
		if (password.isEmpty()) {
			emptyFields.add("Password");
		}
		
		// required fields control
		if (!emptyFields.isEmpty()) {
			String message = ErrorsAdministrator.getDescription("MEET0057",
					loginActivity);
			Iterator<String> iterator = emptyFields.iterator();
			while (iterator.hasNext()) {
				String temp = iterator.next();
				String temp2 = ErrorsAdministrator.getDescription(temp,
						loginActivity);
				message += "\n- " + temp2;
			}
			Toast.makeText(loginActivity, message, Toast.LENGTH_LONG)
					.show();
			return false;
		}


		return true;
	}

}
