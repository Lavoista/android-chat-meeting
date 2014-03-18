package it.meet.registration;

import it.meet.R;
import it.meet.activities.RegistrationActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.R.color;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;

public class OnClickDateFieldListener implements OnClickListener{
	private EditText dateField;
	private RegistrationActivity registrationActivity;
	
	public OnClickDateFieldListener(RegistrationActivity registrationActivity){
		dateField = (EditText) registrationActivity.findViewById(R.id.birthDate);
		this.registrationActivity = registrationActivity;
		
	}
	
	@Override
	public void onClick(View v) {
		OnDateSetListener myDateSetListener = new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker datePicker, int i,
					int j, int k) {
				dateField.setText("" + k + "/" + (j + 1) + "/" + i);
				Calendar c = Calendar.getInstance();
				DateFormat dateFormat = android.text.format.DateFormat
						.getDateFormat(registrationActivity);
				c.set(i, j, k);
				Date data = new Date(c.getTimeInMillis());
				dateField.setText("" + dateFormat.format(data));

			}
		};

		DatePickerDialog dpdFromDate = new DatePickerDialog(
				registrationActivity, myDateSetListener, 1, 1, 1990);
		dpdFromDate.getDatePicker().setBackgroundColor(color.black);
		dpdFromDate.getDatePicker().updateDate(1990, 0, 1);
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= 11) {
			try {
				dpdFromDate.getDatePicker().setCalendarViewShown(
						false);
			} catch (Exception e) {
				e.printStackTrace();
			} // eat exception in our case
		}
		dpdFromDate.show();
		dpdFromDate.setButton(DialogInterface.BUTTON_NEGATIVE,
				"Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						if (which == DialogInterface.BUTTON_NEGATIVE) {
							// et_to_date.setText("");
						}
					}
				});

	}

}
