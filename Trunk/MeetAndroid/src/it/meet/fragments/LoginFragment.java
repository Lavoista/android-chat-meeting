package it.meet.fragments;

import it.meet.activities.MainActivity.PlanetFragment;
import it.meet.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {
	private View rootView;
    public static final String ARG_PLANET_NUMBER = "planet_number";

    public LoginFragment() {
        // Empty constructor required for fragment subclasses
    }
    public void setRootView(View _rootView) {
    	rootView = _rootView;
        // Empty constructor required for fragment subclasses
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
        	rootView = inflater.inflate(R.layout.login_fragment, container, false);
        	EditText campoUtente = (EditText) rootView.findViewById(R.id.editText1);
        	campoUtente.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        	Button buttonOne = (Button) rootView.findViewById(R.id.Button01);
        	
        	
        	buttonOne.setOnClickListener(new Button.OnClickListener() {
        	    public void onClick(View v) {
        	    	Log.w("Position","Non faccio nulla");
            		RegistrationFragment fragmentRegistrati = new RegistrationFragment();
            		fragmentRegistrati.setRootView(rootView);
            		Bundle args = new Bundle();
        	        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, 1);
        	        fragmentRegistrati.setArguments(args);
            		FragmentManager fragmentManager = getFragmentManager();
        	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentRegistrati).commit();
        	
        	    }
        	});
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        return rootView;
    }
   
}
