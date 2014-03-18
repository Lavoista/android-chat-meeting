package it.meet.friendProfile;

import it.meet.R;
import it.meet.activities.MainActivity;
import it.meet.fragments.ChatFragment;
import it.meet.user.data.UserDataAdministrator;
import android.app.Activity;
import android.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClickStartChatListener implements OnClickListener{
	private View rootView;
	private UserDataAdministrator userDataAdministrator;
	private String remoteUsername;
	
	public OnClickStartChatListener(View _rootView,String remoteUsername){
		this.rootView = _rootView;
		this.remoteUsername = remoteUsername;
		
	}
	
	@Override
	public void onClick(View v) {
		ChatFragment chatFragment = new ChatFragment();
		((MainActivity) v.getContext()).setCurrentFragment(chatFragment);

		chatFragment.setRemoteUsername(remoteUsername);
		FragmentManager fragmentManager = ((Activity) rootView.getContext()).getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, chatFragment).commit();
	}

}