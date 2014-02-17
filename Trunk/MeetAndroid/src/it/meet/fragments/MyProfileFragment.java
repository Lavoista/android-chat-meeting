package it.meet.fragments;


import it.meet.R;
import it.meet.user.data.UserDataAdministrator;
import android.app.Fragment;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MyProfileFragment extends Fragment {
	private View profileView;
	EditText campoData;
	private UserDataAdministrator userDataAdministrator;

    
    public MyProfileFragment() {
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
