package it.meet.registration;

import it.meet.R;
import it.meet.activities.RegistrationActivity;
import it.meet.service.user.entity.UserDTO;
import it.meet.utils.ErrorsAdministrator;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class OnClickSubmitRegistrationListener implements OnClickListener {
	
	private String userName = "";
	private String password = "";
	private String confirmPassword = "";
	private String name = "";
	private String surname = "";
	private String mailAddress = "";
	private Date birthDate = null;
	private String dateField = "";
	private String sex = "";
	private String phoneNumber = "";
	private byte[] photo;
	private ArrayList<String> emptyFields;
	private RegistrationActivity registrationActivity;

	public OnClickSubmitRegistrationListener(
			RegistrationActivity registrationActivity) {
		this.registrationActivity = registrationActivity;
	}

	@Override
	public void onClick(View v) {
		if (checkRegistrationFields()) {
			RegistrationTask rt = new RegistrationTask(registrationActivity);
			UserDTO utente = new UserDTO();
			utente.setUsername(userName);
			utente.setPassword(password);
			utente.setName(name);
			utente.setSurname(surname);
			utente.setTelephoneNumber(phoneNumber);
			utente.setDateOfBirth(birthDate);
			utente.setEmail(mailAddress);
			utente.setSex(sex);
			utente.setPhoto(photo);
			rt.execute(utente);
		} else {
			// send error to user
		}
	}

	private boolean checkRegistrationFields() {
		emptyFields = new ArrayList<String>();
		userName = (((EditText) registrationActivity.findViewById(R.id.username)).getText()
				.toString());
		if (userName.isEmpty()) {
			emptyFields.add("UserName");
		}
		password = (((EditText) registrationActivity.findViewById(R.id.password)).getText()
				.toString());
		if (password.isEmpty()) {
			emptyFields.add("Password");
		}
		confirmPassword = (((EditText) registrationActivity
				.findViewById(R.id.confirmPassword)).getText().toString());
		if (confirmPassword.isEmpty()) {
			emptyFields.add("ConfirmPassword");
		}
		name = (((EditText) registrationActivity.findViewById(R.id.name)).getText()
				.toString());
		if (name.isEmpty()) {
			//emptyFields.add("Name"); if is empty do nothing
		}
		surname = (((EditText) registrationActivity.findViewById(R.id.surname)).getText()
				.toString());
		if (surname.isEmpty()) {
			//emptyFields.add("Surname"); if is empty do nothing
		}
		mailAddress = (((EditText) registrationActivity.findViewById(R.id.mailAddress))
				.getText().toString());
		if (mailAddress.isEmpty()) {
			//emptyFields.add("EMail"); if is empty do nothing
		}
		dateField = (((EditText) registrationActivity.findViewById(R.id.birthDate))
				.getText().toString());
		if (dateField.isEmpty()) {
			emptyFields.add("BirthDate");
		} else {
			try {
				DateFormat dateFormat = android.text.format.DateFormat
						.getDateFormat(registrationActivity);
				birthDate = dateFormat.parse(dateField);
			} catch (Exception e) {
				Toast.makeText(
						registrationActivity,
						ErrorsAdministrator.getDescription(
								"Error to parse a Date",
								registrationActivity),
						Toast.LENGTH_LONG).show();
				return false;
			}
		}
		phoneNumber = (((EditText) registrationActivity.findViewById(R.id.phoneNumber))
				.getText().toString());

		Bitmap bitmapPicture = registrationActivity.getPhotoImage();
		if (bitmapPicture != null) {
			ByteArrayOutputStream stream;
			byte[] byteArray;
			stream = new ByteArrayOutputStream();
			bitmapPicture.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byteArray = stream.toByteArray();

			photo = byteArray;
		}
		boolean sexM = (((RadioButton) registrationActivity.findViewById(R.id.sexM))
				.isChecked());
		boolean sexF = (((RadioButton) registrationActivity.findViewById(R.id.sexM))
				.isChecked());
		if (sexM) {
			sex = "M";
		}
		if (sexF) {
			sex = "F";
		}
		if (!sexM && !sexF) {
			emptyFields.add("Sex");
		}

		// required fields control
		if (!emptyFields.isEmpty()) {
			String message = ErrorsAdministrator.getDescription("MEET0057",
					registrationActivity);
			Iterator<String> iterator = emptyFields.iterator();
			while (iterator.hasNext()) {
				String temp = iterator.next();
				String temp2 = ErrorsAdministrator.getDescription(temp,
						registrationActivity);
				message += "\n- " + temp2;
			}
			Toast.makeText(registrationActivity, message, Toast.LENGTH_LONG)
					.show();
			return false;
		}

		if (!mailAddress.isEmpty() && !mailAddress.contains("@")) {
			Toast.makeText(
					registrationActivity,
					ErrorsAdministrator.getDescription("MEET0006",
							registrationActivity),
					Toast.LENGTH_LONG).show();
			return false;
		}
		if (!password.equals(confirmPassword)) {
			Toast.makeText(
					registrationActivity,
					ErrorsAdministrator.getDescription("MEET0056",
							registrationActivity),
					Toast.LENGTH_LONG).show();
			return false;
		}

		return true;
	}

}
