package it.meet.fragments;

import it.meet.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SettingsFragment extends Fragment {
	private View settingsView;
	public SettingsFragment() {
		// Empty constructor required for fragment subclasses
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		settingsView = inflater.inflate(R.layout.settings_fragment,
				container, false);
		String title = getResources().getStringArray(R.array.menu_array)[7];
		getActivity().setTitle(title);
		//MessagesAdministrator chatMessagesAdministrator = new MessagesAdministrator(conversationView);
		//Iterator<Message> chatMessages = chatMessagesAdministrator.getMessagesFromDb(localUsername,remoteUsername);
		
		
		Toast.makeText(getActivity(), "NO CONVERSATIONS FOUND",Toast.LENGTH_LONG).show();
		return settingsView;
	}


}

