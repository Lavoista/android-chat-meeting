package it.meet.fragments;

import it.meet.activities.MainActivity;
import it.meet.activities.MainActivity.PlanetFragment;
import it.meet.service.user.entity.UserDTO;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import it.meet.R;
import android.R.color;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.InflateException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnHoverListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class FriendsFragment extends Fragment {
	private View rootView;
	EditText dateField;
	public static final String ARG_PLANET_NUMBER = "planet_number";
	private static int RESULT_LOAD_IMAGE = 1;
	private static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private String picturePath = "";
	private ProgressDialog progressBar;

	public FriendsFragment() {
		// Empty constructor required for fragment subclasses
	}

	public void setRootView(View _rootView) {
		rootView = _rootView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		rootView = inflater.inflate(R.layout.chat_fragment,
					container, false);
		return rootView;
	}

	
	

}
