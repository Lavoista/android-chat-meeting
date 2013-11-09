package it.meet.registration.classes;

import it.meet.R;
import it.meet.fragments.RegistrationFragment;

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
	private View rootView;
	private EditText dateField;
	private RegistrationFragment regitrationFragment;
	
	public OnClickDateFieldListener(View _rootView,RegistrationFragment regitrationFragment){
		this.rootView = _rootView;
		dateField = (EditText) rootView.findViewById(R.id.birthDate);
		this.regitrationFragment = regitrationFragment;
		
	}
	
	public void onClick(View v) {
		OnDateSetListener myDateSetListener = new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker datePicker, int i,
					int j, int k) {
				dateField.setText("" + k + "/" + (j + 1) + "/" + i);
				Calendar c = Calendar.getInstance();
				DateFormat dateFormat = android.text.format.DateFormat
						.getDateFormat(regitrationFragment.getActivity());
				c.set(i, j, k);
				Date data = new Date(c.getTimeInMillis());
				dateField.setText("" + dateFormat.format(data));

			}
		};

		DatePickerDialog dpdFromDate = new DatePickerDialog(
				regitrationFragment.getActivity(), myDateSetListener, 1, 1, 1990);
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
					public void onClick(DialogInterface dialog,
							int which) {
						if (which == DialogInterface.BUTTON_NEGATIVE) {
							// et_to_date.setText("");
						}
					}
				});

	}

}
