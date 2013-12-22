package it.meet.fragments;

import it.meet.R;
import it.meet.conversations.ConversationsItemClickListener;
import it.meet.conversations.ConversationsListAdapter;
import it.meet.entity.Conversation;
import it.meet.user.data.UserDataAdministrator;
import it.meet.utils.ErrorsAdministrator;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ConversationsFragment extends Fragment {
	private View conversationsView;
	private UserDataAdministrator userDataAdministrator;

	public void setUserDataAdministrator(
			UserDataAdministrator userDataAdministrator) {
		this.userDataAdministrator = userDataAdministrator;
	}

	public ConversationsFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		conversationsView = inflater.inflate(R.layout.conversations_fragment,
				container, false);
		String title = getResources().getStringArray(R.array.menu_array)[1];
		getActivity().setTitle(title);
		ArrayDeque<Conversation> conversationsDeque = userDataAdministrator
				.getConversationsDeque();
		if (conversationsDeque.isEmpty()) {
			Toast.makeText(
					getActivity(),
					ErrorsAdministrator.getDescription(
							"NO_CONVERSATIONS_FOUND", getActivity()),
					Toast.LENGTH_LONG).show();
		}
		// there is any conversation
		else {
	        ListView listView = (ListView)conversationsView.findViewById(R.id.listViewConversation);
	        List<Conversation> list = new LinkedList<Conversation>();
	        Iterator<Conversation> conversationsIterator = conversationsDeque.descendingIterator();
	        while (conversationsIterator.hasNext()){
	        	list.add(conversationsIterator.next());
	        }
	        ConversationsListAdapter adapter = new ConversationsListAdapter(this.getActivity(), R.layout.conversations_row, list);
	        listView.setAdapter(adapter);
	        ConversationsItemClickListener listener = new ConversationsItemClickListener();
	        listener.setUserDataAdministrator(userDataAdministrator);
	        listener.setConversationList(list);
	        listView.setOnItemClickListener(listener);	        
		}

		return conversationsView;
	}

}
