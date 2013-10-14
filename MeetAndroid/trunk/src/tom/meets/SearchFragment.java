package tom.meets;

import android.app.Fragment;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchFragment extends Fragment {
	private View rootView;
    public static final String ARG_PLANET_NUMBER = "planet_number";

    public SearchFragment() {
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
        	rootView = inflater.inflate(R.layout.search_fragment, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        return rootView;
    }
}
