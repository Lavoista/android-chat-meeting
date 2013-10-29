package it.meet.fragments;

import it.meet.activities.MainActivity.PlanetFragment;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import it.meet.R;
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
	private View rootView;
	EditText campoData;
    public static final String ARG_PLANET_NUMBER = "planet_number";
    
    public ProfileFragment() {
        // Empty constructor required for fragment subclasses
    }
    
    public void setRootView(View _rootView){
    	rootView = _rootView;    	
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
        	
        	

          
        	
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        return rootView;
    }
   
}
