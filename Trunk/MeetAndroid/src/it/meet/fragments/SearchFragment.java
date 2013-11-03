package it.meet.fragments;

import it.meet.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchFragment extends Fragment {
	private static View searchView;//conservo la view in memoria per eliminarla dopo, prima di ricrearla,
								   //per evitare di avere chiave duplicata sui suoi elementi
    public SearchFragment() {
        // Empty constructor required for fragment subclasses
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	if (searchView != null) {    		
            ViewGroup parent = (ViewGroup) searchView.getParent();
            if (parent != null)
                parent.removeView(searchView);
        }
    	
        try {
        	searchView = inflater.inflate(R.layout.search_fragment, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        	e.printStackTrace();
        }
        
        return searchView;
    }
}
