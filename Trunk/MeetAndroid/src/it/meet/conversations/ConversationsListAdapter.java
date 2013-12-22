package it.meet.conversations;

import java.util.List;

import it.meet.R;
import it.meet.entity.Conversation;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ConversationsListAdapter extends ArrayAdapter<Conversation>{
	private Drawable noPhotoMale,noPhotoFemale,noPhotoAvailable;
	private Bitmap bmp;
	private byte[] byteArray;
    public ConversationsListAdapter(Context context, int textViewResourceId,
    		List<Conversation> objects) {
        super(context, textViewResourceId, objects);
        noPhotoMale = this.getContext().getResources().getDrawable(R.drawable.no_photo_male);
        noPhotoFemale = this.getContext().getResources().getDrawable(R.drawable.no_photo_female);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.conversations_row, null);
        
        TextView username = (TextView)convertView.findViewById(R.id.textView1List);
        TextView lastMessage = (TextView)convertView.findViewById(R.id.textView2List);
        ImageView photoView = (ImageView)convertView.findViewById(R.id.conversationPhoto);
        Conversation c = getItem(position);
        String sex = c.getRemoteUserSex();
        if(sex.equals("M")){
        	noPhotoAvailable = noPhotoMale;
        }
        else{
        	noPhotoAvailable = noPhotoFemale;
        }
        byteArray = c.getRemoteUserPhoto();
        if(byteArray != null && byteArray.length > 0){
        	bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        	photoView.setImageBitmap(bmp);
        }
        else{
        	photoView.setImageDrawable(noPhotoAvailable);
        }
        username.setText(c.getRemoteUser());
        lastMessage.setText(c.getLastMessageChat().getMessage());
        return convertView;
    }

}
