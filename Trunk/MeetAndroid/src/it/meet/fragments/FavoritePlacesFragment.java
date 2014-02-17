package it.meet.fragments;

import it.meet.R;
import it.meet.utils.ErrorsAdministrator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FavoritePlacesFragment extends Fragment {
	private View favoritePlacesView;

	

	public FavoritePlacesFragment() {
		// Empty constructor required for fragment subclasses
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		favoritePlacesView = inflater.inflate(R.layout.favorite_places_fragment,
				container, false);
		String title = getResources().getStringArray(R.array.menu_array)[5];
		getActivity().setTitle(title);
		//MessagesAdministrator chatMessagesAdministrator = new MessagesAdministrator(conversationView);
		//Iterator<Message> chatMessages = chatMessagesAdministrator.getMessagesFromDb(localUsername,remoteUsername);
		
		
		Toast.makeText(getActivity(), ErrorsAdministrator.getDescription("NO_FAVORITE_PLACE_FOUND",
				getActivity()),Toast.LENGTH_LONG).show();
		return favoritePlacesView;
	}



}

