package it.meet.fragments;

import it.meet.entity.Friend;
import it.meet.friends.FriendsListAdapter;
import it.meet.friends.FriendsItemClickListener;
import it.meet.user.data.UserDataAdministrator;
import it.meet.utils.ErrorsAdministrator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.meet.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FriendsListFragment extends Fragment {
	private View friendsView;
	EditText dateField;
	private UserDataAdministrator userDataAdministrator;


	public FriendsListFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		friendsView = inflater.inflate(R.layout.friends_fragment,
					container, false);
		String title = getResources()
				.getStringArray(R.array.menu_array)[2];
		getActivity().setTitle(title);
		userDataAdministrator = UserDataAdministrator.getInstance(this.getActivity());
		ArrayList<Friend> friendList = userDataAdministrator.getFriendsList();
		if (friendList.isEmpty()) {
			Toast.makeText(getActivity(), ErrorsAdministrator.getDescription("NO_FRIENDS_FOUND",
				getActivity()),Toast.LENGTH_LONG).show();
		}
		else {
	        ListView listView = (ListView)friendsView.findViewById(R.id.friendsListView);
	        List<Friend> list = new LinkedList<Friend>();
	        Iterator<Friend> friendsIterator = friendList.iterator();
	        while (friendsIterator.hasNext()){
	        	list.add(friendsIterator.next());
	        }
	        FriendsListAdapter adapter = new FriendsListAdapter(this.getActivity(), R.layout.friends_row, list);
	        listView.setAdapter(adapter);
	        FriendsItemClickListener listener = new FriendsItemClickListener();
	        listener.setFriendsList(list);
	        listView.setOnItemClickListener(listener);	        
		}
		return friendsView;
	}


	
	

}
