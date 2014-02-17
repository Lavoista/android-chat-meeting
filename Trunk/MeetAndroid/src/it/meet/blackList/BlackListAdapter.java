package it.meet.blackList;

import java.util.List;

import it.meet.R;
import it.meet.entity.BlackContact;
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

public class BlackListAdapter extends ArrayAdapter<BlackContact>{
	private Drawable noPhotoMale,noPhotoFemale,noPhotoAvailable;
	private Bitmap bmp;
	private byte[] byteArray;
    public BlackListAdapter(Context context, int textViewResourceId,
    		List<BlackContact> objects) {
        super(context, textViewResourceId, objects);
        noPhotoMale = this.getContext().getResources().getDrawable(R.drawable.no_photo_male);
        noPhotoFemale = this.getContext().getResources().getDrawable(R.drawable.no_photo_female);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.black_list_row, null);
        
        TextView username = (TextView)convertView.findViewById(R.id.blackListRowText1);
        TextView lastMessage = (TextView)convertView.findViewById(R.id.blackListRowText2);
        ImageView photoView = (ImageView)convertView.findViewById(R.id.blackListRowPhoto);
        BlackContact c = getItem(position);
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
