package it.meet.friends;

import java.util.List;

import it.meet.R;
import it.meet.entity.Friend;
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
	private Drawable noPhotoMale,noPhotoFemale,noPhotoAvailable,onLineImage,offLineImage;
	private Bitmap bmp;
	private byte[] byteArray;
    public FriendsListAdapter(Context context, int textViewResourceId,
    		List<Friend> objects) {
        super(context, textViewResourceId, objects);
        noPhotoMale = this.getContext().getResources().getDrawable(R.drawable.no_photo_male);
        noPhotoFemale = this.getContext().getResources().getDrawable(R.drawable.no_photo_female);
        onLineImage = this.getContext().getResources().getDrawable(R.drawable.on_line_image);
        offLineImage = this.getContext().getResources().getDrawable(R.drawable.on_line_image);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.friends_row, null);
        
        TextView username = (TextView)convertView.findViewById(R.id.friendsRowText1);
        TextView phrase = (TextView)convertView.findViewById(R.id.friendsRowText2);
        ImageView onLine = (ImageView)convertView.findViewById(R.id.friendsRowOnLine);
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
        //capitalize first letter in every word in username
        String usernameVar = c.getUsername();
        String[] tokens = usernameVar.split(" ");
        String resultString = "";
        for(int h=0;h<tokens.length;h++){
        	tokens[h] = tokens[h].substring(0,1).toUpperCase()+tokens[h].substring(1,tokens[h].length());
        	resultString += tokens[h]+" ";
        }
        onLine.setImageDrawable(onLineImage);
        username.setText(resultString);
        phrase.setText(c.getPhrase());
        return convertView;
    }

}
