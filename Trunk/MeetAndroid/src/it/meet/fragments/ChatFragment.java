package it.meet.fragments;

import it.meet.chat.OnClickSubmitChatListener;
import it.meet.service.messaging.Message;
import it.meet.user.data.UserDataAdministrator;

import java.util.ArrayList;
import java.util.Iterator;
import it.meet.R;
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

public class ChatFragment extends Fragment {
	private View chatView;
	private String remoteUsername;
	private UserDataAdministrator userDataAdministrator;


	public ChatFragment() {
		// Empty constructor required for fragment subclasses
	}
	
	
	public void setRemoteUsername(String remoteUsername){
		this.remoteUsername = remoteUsername;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		chatView = inflater.inflate(R.layout.chat_fragment,
				container, false);
		String title = "Chat";
		getActivity().setTitle(title);
		userDataAdministrator = UserDataAdministrator.getInstance(this.getActivity());
		ArrayList<Message> chatMessages = userDataAdministrator.getLastChatMessages(remoteUsername);
		Iterator<Message> chatMessagesIterator = chatMessages.iterator();
		
		
		//cerco tutti i messaggi inviati e ricevuti dall'utente username
		while(chatMessagesIterator.hasNext()){
			Message temp = chatMessagesIterator.next();
			LinearLayout linearLayout = (LinearLayout) chatView.findViewById(R.id.messages);
			LinearLayout linearTemp = new LinearLayout(chatView.getContext());
			TextView child = new TextView(chatView.getContext());
			if(temp.getSender().equals(remoteUsername)){
				linearTemp.setGravity(Gravity.LEFT);
				child.setBackgroundResource(R.drawable.comic_dialog_left);
			}
			else{
				linearTemp.setGravity(Gravity.RIGHT);
				child.setBackgroundResource(R.drawable.comic_dialog_right);
			}
			child.setText(temp.getMessage());
			child.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		            LayoutParams.WRAP_CONTENT));
			linearTemp.setPadding(15,10,15,0);
			linearTemp.addView(child);
			linearLayout.addView(linearTemp);
			//ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.scrollViewChat);
		}
		Button submitMessageButton = (Button) chatView.findViewById(R.id.submitMessageButton);
		submitMessageButton.setOnClickListener(new OnClickSubmitChatListener(chatView, this)); 
		
		
		return chatView;
	}

}
