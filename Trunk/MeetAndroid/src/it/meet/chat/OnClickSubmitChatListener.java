package it.meet.chat;

import it.meet.R;
import it.meet.fragments.ChatFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class OnClickSubmitChatListener implements OnClickListener{
	private View rootView;
	private EditText messageField;
	private ChatFragment chatFragment;
	
	public OnClickSubmitChatListener(View _rootView,ChatFragment chatFragment){
		this.rootView = _rootView;
		messageField = (EditText) rootView.findViewById(R.id.insertMessage);
		this.chatFragment = chatFragment;
		
	}
	
	@Override
	public void onClick(View v) {
		messageField.setText("");
	}

}
