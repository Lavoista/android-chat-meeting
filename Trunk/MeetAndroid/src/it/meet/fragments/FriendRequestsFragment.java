package it.meet.fragments;

import it.meet.R;
import it.meet.user.data.UserDataAdministrator;
import it.meet.utils.ErrorsAdministrator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FriendRequestsFragment extends Fragment {
	private View friendRequestsView;
	public FriendRequestsFragment() {
		// Empty constructor required for fragment subclasses
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		friendRequestsView = inflater.inflate(R.layout.friend_requests_fragment,
				container, false);
		String title = getResources().getStringArray(R.array.menu_array)[3];
		getActivity().setTitle(title);
		//MessagesAdministrator chatMessagesAdministrator = new MessagesAdministrator(conversationView);
		//Iterator<Message> chatMessages = chatMessagesAdministrator.getMessagesFromDb(localUsername,remoteUsername);
		
		
		Toast.makeText(getActivity(), ErrorsAdministrator.getDescription("NO_FRIEND_REQUESTS_FOUND",
				getActivity()),Toast.LENGTH_LONG).show();
		return friendRequestsView;
	}


}

