package it.meet.fragments;

import it.meet.R;
import it.meet.chat.OnClickSubmitChatListener;
import it.meet.localdb.MessagesAdministrator;
import it.meet.service.messaging.Message;

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

public class ConversationsFragment extends Fragment {
	private View conversationsView;

	public ConversationsFragment() {
		// Empty constructor required for fragment subclasses
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		conversationsView = inflater.inflate(R.layout.conversations_fragment,
				container, false);
		//MessagesAdministrator chatMessagesAdministrator = new MessagesAdministrator(conversationView);
		//Iterator<Message> chatMessages = chatMessagesAdministrator.getMessagesFromDb(localUsername,remoteUsername);
		
		
		Toast.makeText(getActivity(), "NO CONVERSATIONS FOUND",Toast.LENGTH_LONG);
		return conversationsView;
	}

}

