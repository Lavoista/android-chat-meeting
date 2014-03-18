package it.meet.fragments;

import it.meet.chat.OnClickOpenSmileWindow;
import it.meet.chat.OnClickSubmitChatListener;
import it.meet.chat.OnInsertMessageClickListener;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.EditText;

public class ChatFragment extends Fragment {
	private View chatView;
	private String remoteUsername;
	private PopupWindow popupWindow;
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
		getActivity().setTitle("Chat");
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
		ImageButton openSmileWindow = (ImageButton) chatView.findViewById(R.id.ImageButton01);
		openSmileWindow.setOnClickListener(new OnClickOpenSmileWindow(chatView, this)); 
		EditText editTextChat = (EditText) chatView.findViewById(R.id.insertMessage);
		editTextChat.setOnClickListener(new OnInsertMessageClickListener(chatView, this));	
		//enablePopUpView();
		return chatView;
	}

	/*
	@Override
	public void onDestroyView(){
		if(this.popupWindow.isShowing()){
			this.popupWindow.dismiss();
		}
		else{
			super.onDestroyView();
		}
		//3332297876
	}
	*/
	
	/*
	private void enablePopUpView() {

		ViewPager pager = (ViewPager) popUpView.findViewById(R.id.emoticons_pager);
		pager.setOffscreenPageLimit(3);
		
		ArrayList<String> paths = new ArrayList<String>();

		for (short i = 1; i <= NO_OF_EMOTICONS; i++) {			
			paths.add(i + ".png");
		}
		EmoticonsPagerAdapter adapter = new EmoticonsPagerAdapter(this.getActivity(), paths, this);
		pager.setAdapter(adapter);
	}
/*
		

		// Creating a pop window for emoticons keyboard
		popupWindow = new PopupWindow(popUpView, LayoutParams.MATCH_PARENT,
				(int) keyboardHeight, false);
		
		TextView backSpace = (TextView) popUpView.findViewById(R.id.back);
		backSpace.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
				content.dispatchKeyEvent(event);	
			}
		});

		popupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				emoticonsCover.setVisibility(LinearLayout.GONE);
			}
		});
	}
	*/
	

	public PopupWindow getPopupWindow() {
		return popupWindow;
	}


	public void setPopupWindow(PopupWindow popupWindow) {
		this.popupWindow = popupWindow;
	}



}
