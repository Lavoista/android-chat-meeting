package it.meet.conversations;

import java.util.List;

import it.meet.R;
import it.meet.fragments.ChatFragment;
import it.meet.user.data.UserDataAdministrator;
import android.app.Activity;
import android.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import it.meet.activities.MainActivity;
import it.meet.entity.*;
import android.widget.AdapterView.OnItemClickListener;

public class ConversationsItemClickListener implements OnItemClickListener {
	private UserDataAdministrator userDataAdministrator;
	private List<Conversation> conversationList;
    	
		
		public void setConversationList(List<Conversation> conversationList) {
			this.conversationList = conversationList;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			((MainActivity) arg1.getContext()).getDrawerList().setItemChecked(1, false);
			ChatFragment chatFragment = new ChatFragment();
			((MainActivity) arg1.getContext()).setCurrentFragment(chatFragment);
			chatFragment.setRemoteUsername(this.conversationList.get(arg2).getRemoteUser());
			FragmentManager fragmentManager = ((Activity) arg1.getContext()).getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, chatFragment).commit();
		}

}
