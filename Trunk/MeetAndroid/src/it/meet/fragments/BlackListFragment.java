package it.meet.fragments;

import it.meet.R;
import it.meet.chat.OnClickSubmitChatListener;
import it.meet.localdb.MessagesAdministrator;
import it.meet.service.messaging.Message;
import it.meet.utils.ErrorsAdministrator;

import java.util.Iterator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BlackListFragment extends Fragment {
	private View blackListView;

	public BlackListFragment() {
		// Empty constructor required for fragment subclasses
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		blackListView = inflater.inflate(R.layout.black_list_fragment,
				container, false);
		String title = getResources().getStringArray(R.array.menu_array)[4];
		getActivity().setTitle(title);
		//MessagesAdministrator chatMessagesAdministrator = new MessagesAdministrator(conversationView);
		//Iterator<Message> chatMessages = chatMessagesAdministrator.getMessagesFromDb(localUsername,remoteUsername);
		
		Toast.makeText(getActivity(), ErrorsAdministrator.getDescription("NO_BLACKLIST_FOUND",
				getActivity()),Toast.LENGTH_LONG).show();
		return blackListView;
	}

}

