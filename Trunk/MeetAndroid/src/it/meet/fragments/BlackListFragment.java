package it.meet.fragments;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.meet.R;
import it.meet.blackList.BlackListAdapter;
import it.meet.blackList.BlackListItemClickListener;
import it.meet.entity.BlackContact;
import it.meet.user.data.UserDataAdministrator;
import it.meet.utils.ErrorsAdministrator;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class BlackListFragment extends Fragment {
	private View blackListView;
	private UserDataAdministrator userDataAdministrator;


	public BlackListFragment() {
		// Empty constructor required for fragment subclasses
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		blackListView = inflater.inflate(R.layout.black_list_fragment,
				container, false);
		String title = getResources().getStringArray(R.array.menu_array)[4];
		getActivity().setTitle(title);	
		userDataAdministrator = UserDataAdministrator.getInstance(this.getActivity());
		ArrayList<BlackContact> blackList = userDataAdministrator.getBlackList();
		if (blackList.isEmpty()) {
			Toast.makeText(getActivity(), ErrorsAdministrator.getDescription("NO_BLACKLIST_FOUND",
					getActivity()),Toast.LENGTH_LONG).show();
		}
		else {
	        ListView listView = (ListView)blackListView.findViewById(R.id.blackListView);
	        List<BlackContact> list = new LinkedList<BlackContact>();
	        Iterator<BlackContact> blackListIterator = blackList.iterator();
	        while (blackListIterator.hasNext()){
	        	list.add(blackListIterator.next());
	        }
	        BlackListAdapter adapter = new BlackListAdapter(this.getActivity(), R.layout.black_list_row, list);
	        listView.setAdapter(adapter);
	        BlackListItemClickListener listener = new BlackListItemClickListener();
	        listener.setBlackList(list);
	        listView.setOnItemClickListener(listener);	        
		}
		return blackListView;
	}
	



}

