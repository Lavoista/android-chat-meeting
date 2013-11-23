package it.meet.fragments;


import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import it.meet.R;
import it.meet.user.data.UserDataAdministrator;
import android.R.color;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class ProfileFragment extends Fragment {
	private View profileView;
	EditText campoData;
	private UserDataAdministrator userDataAdministrator;

	public void setUserDataAdministrator(UserDataAdministrator userDataAdministrator) {
		this.userDataAdministrator = userDataAdministrator;
	}
    
    public ProfileFragment() {
        // Empty constructor required for fragment subclasses
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		String title = getResources().getStringArray(R.array.menu_array)[6];
		getActivity().setTitle(title);
    	if (profileView != null) {
            ViewGroup parent = (ViewGroup) profileView.getParent();
            if (parent != null)
                parent.removeView(profileView);
        }
        try {
        	
        	

          
        	
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        return profileView;
    }

   
}
