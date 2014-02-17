package it.meet.fragments;

import it.meet.entity.Friend;
import it.meet.friendProfile.OnClickStartChatListener;
import it.meet.user.data.UserDataAdministrator;
import it.meet.utils.DateUtil;

import it.meet.R;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendProfileFragment extends Fragment {
	private View friendProfileView;
	private String remoteUsername;
	private UserDataAdministrator userDataAdministrator;
	private Drawable noPhotoAvailable;
	private Bitmap bmp;
	private byte[] byteArray;
	


	public FriendProfileFragment() {
		// Empty constructor required for fragment subclasses
	}
	
	
	public void setRemoteUsername(String remoteUsername){
		this.remoteUsername = remoteUsername;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		friendProfileView = inflater.inflate(R.layout.friend_profile_fragment,
				container, false);
		getActivity().setTitle("Profilo utente");
		userDataAdministrator = UserDataAdministrator.getInstance(this.getActivity());
		Friend friend = userDataAdministrator.getFriend(remoteUsername);
		if(friend != null){
			BitmapDrawable bitmap = (BitmapDrawable) getResources().getDrawable(R.drawable.gradiente_background_photo_friend);
			 bitmap.setTileModeX(TileMode.REPEAT);
			ImageView photoView = (ImageView) friendProfileView.findViewById(R.id.friend_profile_photo);
			TextView nameView = (TextView) friendProfileView.findViewById(R.id.friend_profile_name);
			TextView surnameView = (TextView) friendProfileView.findViewById(R.id.friend_profile_surname);
			TextView usernameView = (TextView) friendProfileView.findViewById(R.id.friend_profile_username);
			TextView sexView = (TextView) friendProfileView.findViewById(R.id.friend_profile_sex);
			TextView birthdateView = (TextView) friendProfileView.findViewById(R.id.friend_profile_birthdate);
			TextView phraseView = (TextView) friendProfileView.findViewById(R.id.friend_profile_phrase);
			TextView cityView = (TextView) friendProfileView.findViewById(R.id.friend_profile_city);
			TextView provinceView = (TextView) friendProfileView.findViewById(R.id.friend_profile_province);
			TextView nationView = (TextView) friendProfileView.findViewById(R.id.friend_profile_nation);
			TextView phoneView = (TextView) friendProfileView.findViewById(R.id.friend_profile_phone);
			TextView favouriteSexView = (TextView) friendProfileView.findViewById(R.id.friend_profile_favouriteSex);
			Button openChat = (Button) friendProfileView.findViewById(R.id.friend_profile_openChat);
			openChat.setOnClickListener(new OnClickStartChatListener(friendProfileView,remoteUsername));
	        String sex = friend.getSex();
	        if(sex.equals("M")){
	        	noPhotoAvailable = friendProfileView.getContext().getResources().getDrawable(R.drawable.no_photo_male);
	        	sexView.setText("Uomo");
	        }
	        else{
	        	noPhotoAvailable = friendProfileView.getContext().getResources().getDrawable(R.drawable.no_photo_female);
	        	sexView.setText("Donna");
	        }
	        byteArray = friend.getPhoto();
	        if(byteArray != null && byteArray.length > 0){
	        	bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
	        	photoView.setImageBitmap(bmp);
	        }
	        else{
	        	photoView.setImageDrawable(noPhotoAvailable);
	        }
	        String usernameVar = friend.getUsername();
	        String[] tokens = usernameVar.split(" ");
	        String resultString = "";
	        for(int h=0;h<tokens.length;h++){
	        	tokens[h] = tokens[h].substring(0,1).toUpperCase()+tokens[h].substring(1,tokens[h].length());
	        	resultString += tokens[h]+" ";
	        }
	        if(friend.getName().isEmpty()){
	        	nameView.setText("Non Specificato");
	        }
	        else{
	        	nameView.setText(friend.getName());
	        }
	        if(friend.getSurname().isEmpty()){
	        	surnameView.setText("Non Specificato");
	        }
	        else{
	        	surnameView.setText(friend.getSurname());
	        }
	        usernameView.setText(resultString);
	        birthdateView.setText(DateUtil.formatDateToLocale(friend.getBirthdate(),getActivity()));
	        phraseView.setText(Html.fromHtml("<i>&#8221"+friend.getPhrase()+"&#8220</i>"));
	        if(friend.getCity().isEmpty()){
	        	cityView.setText("Non Specificato");
	        }
	        else{
	        	cityView.setText(friend.getCity());
	        }
	        if(friend.getProvince().isEmpty()){
	        	provinceView.setText("Non Specificato");
	        }
	        else{
	        	provinceView.setText(friend.getProvince());
	        }
	        if(friend.getNation().isEmpty()){
	        	nationView.setText("Non Specificato");
	        }
	        else{
	        	nationView.setText(friend.getNation());
	        }
	        if(friend.getPhone().isEmpty()){
	        	phoneView.setText("Non Specificato");
	        }
	        else{
	        	phoneView.setText(friend.getPhone());
	        }
	        if(friend.getFavouriteSex().equals("M")){
	        	favouriteSexView.setText("Uomini");
	        }
	        else if(friend.getFavouriteSex().equals("F")){
	        	favouriteSexView.setText("Donne");
	        }
	        else if(friend.getFavouriteSex().equals("E")){
	        	favouriteSexView.setText("Uomini e Donne");
	        }
	        
		}
        
		return friendProfileView;
	}

}
