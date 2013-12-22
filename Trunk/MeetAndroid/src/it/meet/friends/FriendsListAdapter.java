package it.meet.friends;

import java.util.List;

import it.meet.R;
import it.meet.entity.Conversation;
import it.meet.entity.Friend;
import it.meet.entity.User;
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

public class FriendsListAdapter extends ArrayAdapter<Friend>{
	private Drawable noPhotoMale,noPhotoFemale,noPhotoAvailable;
	private Bitmap bmp;
	private byte[] byteArray;
    public FriendsListAdapter(Context context, int textViewResourceId,
    		List<Friend> objects) {
        super(context, textViewResourceId, objects);
        noPhotoMale = this.getContext().getResources().getDrawable(R.drawable.no_photo_male);
        noPhotoFemale = this.getContext().getResources().getDrawable(R.drawable.no_photo_female);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.friends_row, null);
        
        TextView username = (TextView)convertView.findViewById(R.id.friendsRowText1);
        TextView lastMessage = (TextView)convertView.findViewById(R.id.friendsRowText2);
        ImageView photoView = (ImageView)convertView.findViewById(R.id.friendsRowPhoto);
        Friend c = getItem(position);
        String sex = c.getSex();
        if(sex.equals("M")){
        	noPhotoAvailable = noPhotoMale;
        }
        else{
        	noPhotoAvailable = noPhotoFemale;
        }
        byteArray = c.getPhoto();
        if(byteArray != null && byteArray.length > 0){
        	bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        	photoView.setImageBitmap(bmp);
        }
        else{
        	photoView.setImageDrawable(noPhotoAvailable);
        }
        username.setText(c.getUsername());
        return convertView;
    }

}
