package it.meet.fragments;

import it.meet.activities.MainActivity;
import it.meet.chat.OnClickSubmitChatListener;
import it.meet.localdb.MessagesAdministrator;
import it.meet.service.messaging.Message;
import it.meet.service.user.entity.UserDTO;
import it.meet.user.data.UserDataAdministrator;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import it.meet.R;
import android.R.color;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.InflateException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnHoverListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class ChatFragment extends Fragment {
	private View chatView;
	private String remoteUsername;
	private UserDataAdministrator userDataAdministrator;

	public void setUserDataAdministrator(UserDataAdministrator userDataAdministrator) {
		this.userDataAdministrator = userDataAdministrator;
	}

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
		String title = getResources()
				.getStringArray(R.array.menu_array)[3];
		getActivity().setTitle(title);
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
