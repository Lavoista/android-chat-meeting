package it.meet.blackList;

import java.util.List;

import it.meet.user.data.UserDataAdministrator;
import android.view.View;
import android.widget.AdapterView;
import it.meet.entity.*;
import android.widget.AdapterView.OnItemClickListener;

public class BlackListItemClickListener implements OnItemClickListener {
	private UserDataAdministrator userDataAdministrator;
	private List<BlackContact> blackList;
    	
		public void setUserDataAdministrator(UserDataAdministrator userDataAdministrator) {
			this.userDataAdministrator = userDataAdministrator;
		}
		public void setBlackList(List<BlackContact> blackList) {
			this.blackList = blackList;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			/*
			// TODO Auto-generated method stub
			arg1.getContext();
			((MainActivity) arg1.getContext()).getDrawerList().setItemChecked(1, false);
			ChatFragment chatFragment = new ChatFragment();
			((MainActivity) arg1.getContext()).setCurrentFragment(chatFragment);
			chatFragment.setUserDataAdministrator(userDataAdministrator);
			chatFragment.setRemoteUsername(this.conversationList.get(arg2).getRemoteUser());
			FragmentManager fragmentManager = ((Activity) arg1.getContext()).getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, chatFragment).commit();
					*/
		}

}
