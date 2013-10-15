package it.meet.fragments;

import it.meet.activities.MainActivity.PlanetFragment;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import tom.meets.R;
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
        	rootView = inflater.inflate(R.layout.registration_fragment, container, false);
        	campoData = (EditText) rootView.findViewById(R.id.EditTextData);
        	campoData.setFocusableInTouchMode(false);
        	campoData.setRawInputType(InputType.TYPE_NULL);
	
        	campoData.setOnClickListener(new EditText.OnClickListener() {
        		
        	    public void onClick(View v) {
        	    	OnDateSetListener myDateSetListener = new OnDateSetListener() {
        	    	    @Override
        	    	    public void onDateSet(DatePicker datePicker, int i, int j, int k) {
        	    	        campoData.setText(""+k+"/"+(j+1)+"/"+i);
        	    	        Calendar c = Calendar.getInstance();
        	    	        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getActivity());
        	    	        c.set(i, j, k);
        	    	        Date data= new Date(c.getTimeInMillis());
        	    	        campoData.setText(""+dateFormat.format(data));
        	    	        
        	    	    }
        	    	};
        	    	
        	    	DatePickerDialog dpdFromDate = new DatePickerDialog(getActivity(),myDateSetListener,1,1,1990);
        	    	dpdFromDate.getDatePicker().setBackgroundColor(color.black);
        	    	dpdFromDate.getDatePicker().updateDate(1990, 0, 1);
        	    	int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        	    	System.out.println("versione API:"+currentapiVersion);
        	    	Log.w("TOM",""+currentapiVersion);
        	    	if (currentapiVersion >= 11) {
        	    	  try {
        	    		  dpdFromDate.getDatePicker().setCalendarViewShown(false);
        	    	  }
        	    	  catch (Exception e) {
        	    		  e.printStackTrace();
        	    	  } // eat exception in our case
        	    	}
        	    	dpdFromDate.show();
        	    	dpdFromDate.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
        	              public void onClick(DialogInterface dialog, int which) {
        	                 if (which == DialogInterface.BUTTON_NEGATIVE) {
        	                      //et_to_date.setText("");
        	                 }
        	              }
        	          });
        	     
        	    
        	    }
        	
        	
        	
        	});
        	

          
        	
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        return rootView;
    }
   
}
