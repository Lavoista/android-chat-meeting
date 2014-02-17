package it.meet.friends;

import java.util.List;

import it.meet.R;
import it.meet.fragments.ChatFragment;
import it.meet.fragments.FriendProfileFragment;
import it.meet.fragments.MyProfileFragment;
import it.meet.user.data.UserDataAdministrator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import it.meet.activities.MainActivity;
import it.meet.entity.*;
import android.widget.AdapterView.OnItemClickListener;

public class FriendsItemClickListener implements OnItemClickListener {
	private UserDataAdministrator userDataAdministrator;
	private List<Friend> friendsList;
    	
		
		public void setFriendsList(List<Friend> friendsList) {
			this.friendsList = friendsList;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			((MainActivity) arg1.getContext()).getDrawerList().setItemChecked(2, false);
			FriendProfileFragment friendProfileFragment = new FriendProfileFragment();
			((MainActivity) arg1.getContext()).setCurrentFragment(friendProfileFragment);
			friendProfileFragment.setRemoteUsername(this.friendsList.get(arg2).getUsername());
			FragmentManager fragmentManager = ((Activity) arg1.getContext()).getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, friendProfileFragment).commit();
		}

}
