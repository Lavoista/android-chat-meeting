package it.meet.conversations;

import java.util.List;

import it.meet.R;
import it.meet.entity.Conversation;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ConversationsAdapter extends ArrayAdapter<Conversation>{

    public ConversationsAdapter(Context context, int textViewResourceId,
    		List<Conversation> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.conversations_row, null);
        Drawable imageDrawable = this.getContext().getResources().getDrawable(R.drawable.no_photo_male);
        TextView username = (TextView)convertView.findViewById(R.id.textView1List);
        TextView lastMessage = (TextView)convertView.findViewById(R.id.textView2List);
        ImageView photoView = (ImageView)convertView.findViewById(R.id.conversationPhoto);
        Conversation c = getItem(position);
        photoView.setImageDrawable(imageDrawable);
        username.setText(c.getRemoteUser());
        lastMessage.setText(c.getLastMessageChat().getMessage());
        return convertView;
    }

}
