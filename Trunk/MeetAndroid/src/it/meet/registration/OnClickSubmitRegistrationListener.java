package it.meet.registration;
import it.meet.R;
import it.meet.activities.MainActivity;
import it.meet.fragments.RegistrationFragment;
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

public class OnClickSubmitRegistrationListener implements OnClickListener{
	private View rootView;
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
	private RegistrationFragment registrationFragment;
	
	public OnClickSubmitRegistrationListener(View rootView,RegistrationFragment registrationFragment){
		this.rootView = rootView;
		this.registrationFragment = registrationFragment;
	}
	@Override
	public void onClick(View v) {
		if(checkRegistrationFields()){
			RegistrationTask rt = new RegistrationTask((MainActivity)rootView.getContext());
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
		}
		else{
			//send error to user 
		}
	}
	
	private boolean checkRegistrationFields(){
		emptyFields = new ArrayList<String>();
		userName = (((EditText) rootView
				.findViewById(R.id.username)).getText().toString());
		if(userName.isEmpty()){
			emptyFields.add("UserName");
		}
		password = (((EditText) rootView
				.findViewById(R.id.password)).getText().toString());
		if(password.isEmpty()){
			emptyFields.add("Password");
		}
		confirmPassword = (((EditText) rootView
				.findViewById(R.id.confirmPassword)).getText().toString());
		if(confirmPassword.isEmpty()){
			emptyFields.add("ConfirmPassword");
		}
		name = (((EditText) rootView
				.findViewById(R.id.name)).getText().toString());
		if(name.isEmpty()){
			emptyFields.add("Name");
		}
		surname = (((EditText) rootView
				.findViewById(R.id.surname)).getText().toString());
		if(surname.isEmpty()){
			emptyFields.add("Surname");
		}
		mailAddress = (((EditText) rootView
				.findViewById(R.id.mailAddress)).getText().toString());
		if(mailAddress.isEmpty()){
			emptyFields.add("EMail");
		}
		dateField = (((EditText) rootView
				.findViewById(R.id.birthDate)).getText().toString());
		if(dateField.isEmpty()){
			emptyFields.add("BirthDate");
		}
		else{
			try{
				DateFormat dateFormat = android.text.format.DateFormat
						.getDateFormat((MainActivity)rootView.getContext());			
				birthDate = dateFormat.parse(dateField);
			}
			catch(Exception e){
				Toast.makeText(rootView.getContext(),ErrorsAdministrator.getDescription("Error to parse a Date",(MainActivity)rootView.getContext()), Toast.LENGTH_LONG).show();
				return false;
			}
		}
		phoneNumber = (((EditText) rootView
				.findViewById(R.id.phoneNumber)).getText().toString());
		
		Bitmap bitmapPicture = registrationFragment.getPhotoImage();	
		if(bitmapPicture != null){
			ByteArrayOutputStream stream;
			byte[] byteArray;
			stream = new ByteArrayOutputStream();
			bitmapPicture.compress(Bitmap.CompressFormat.PNG, 100,
			                    stream);
			byteArray = stream.toByteArray();

			photo = byteArray; 
		}
		boolean sexM = (((RadioButton) rootView
				.findViewById(R.id.sexM)).isChecked());
		boolean sexF = (((RadioButton) rootView
				.findViewById(R.id.sexM)).isChecked());
		if(sexM){
			sex = "M";
		}
		if(sexF){
			sex = "F";
		}
		if(!sexM && !sexF){
			emptyFields.add("Sex");
		}
		
		//required fields control		
		if(!emptyFields.isEmpty()){
			String message = ErrorsAdministrator.getDescription("MEET0057",(MainActivity)rootView.getContext());
			Iterator<String> iterator = emptyFields.iterator();
			while(iterator.hasNext()){
				String temp = iterator.next();
				String temp2 = ErrorsAdministrator.getDescription(temp,(MainActivity)rootView.getContext());
				message += "\n- "+temp2;
			}
			Toast.makeText(rootView.getContext(),message, Toast.LENGTH_LONG).show();
			return false;
		}
		
		if(!mailAddress.contains("@")){
			Toast.makeText(rootView.getContext(),ErrorsAdministrator.getDescription("MEET0006",(MainActivity)rootView.getContext()), Toast.LENGTH_LONG).show();
			return false;
		}
		if(!password.equals(confirmPassword)){
			Toast.makeText(rootView.getContext(),ErrorsAdministrator.getDescription("MEET0056",(MainActivity)rootView.getContext()), Toast.LENGTH_LONG).show();
			return false;
		}
		
		return true;
	}

}
