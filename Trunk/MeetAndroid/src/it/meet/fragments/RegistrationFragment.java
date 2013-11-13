package it.meet.fragments;

import it.meet.activities.MainActivity;
import it.meet.activities.MainActivity.PlanetFragment;
import it.meet.registration.OnClickDateFieldListener;
import it.meet.registration.OnClickSubmitRegistrationListener;
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

public class RegistrationFragment extends Fragment {
	private View rootView;
	EditText dateField;
	public static final String ARG_PLANET_NUMBER = "planet_number";
	private static int RESULT_LOAD_IMAGE = 1;
	private static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private String picturePath = "";
	private ProgressDialog progressBar;

	public RegistrationFragment() {
		// Empty constructor required for fragment subclasses
	}

	public void setRootView(View _rootView) {
		rootView = _rootView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (rootView != null) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null)
				parent.removeView(rootView);
		}
		try {
			rootView = inflater.inflate(R.layout.registration_fragment,
					container, false);
			dateField = (EditText) rootView.findViewById(R.id.birthDate);
			dateField.setFocusableInTouchMode(false);
			dateField.setRawInputType(InputType.TYPE_NULL);
			dateField.setOnClickListener(new OnClickDateFieldListener(rootView,this));

			Button loadPhoto = (Button) rootView
					.findViewById(R.id.loadPhotoButton);
			loadPhoto.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent i = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					startActivityForResult(i, RESULT_LOAD_IMAGE);
				}
			});
			Button makePhoto = (Button) rootView
					.findViewById(R.id.makePhotoButton);
			makePhoto.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent i = new Intent(
							android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(i,
							CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
				}
			});
			// Gestione pulsanti rotazione foto sinistra e destra
			Button rotateLeft = (Button) rootView
					.findViewById(R.id.rotateLeftButton);
			rotateLeft.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					MainActivity host = (MainActivity) rootView.getContext();
					Matrix matrix = new Matrix();
					matrix.postRotate(270.0f);
					ImageView imageView = (ImageView) rootView
							.findViewById(R.id.photoView);
					imageView.buildDrawingCache();
					Bitmap myImg = host.immagineFoto;
					Bitmap resizedBitmap = Bitmap.createBitmap(myImg, 0, 0,
							myImg.getWidth(), myImg.getHeight(), matrix, true);
					host.immagineFoto = resizedBitmap;
					imageView.setImageBitmap(resizedBitmap);

				}
			});
			Button rotateRight = (Button) rootView
					.findViewById(R.id.rotateRightButton);
			rotateRight.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					MainActivity host = (MainActivity) rootView.getContext();
					Matrix matrix = new Matrix();
					matrix.postRotate(90.0f);
					ImageView imageView = (ImageView) rootView
							.findViewById(R.id.photoView);
					imageView.buildDrawingCache();
					Bitmap myImg = host.immagineFoto;
					Bitmap resizedBitmap = Bitmap.createBitmap(myImg, 0, 0,
							myImg.getWidth(), myImg.getHeight(), matrix, true);
					host.immagineFoto = resizedBitmap;
					imageView.setImageBitmap(resizedBitmap);

				}
			});
			// Fine gestione pulsanti rotazione foto

			// Nell'aggiornamento della view l'immagine caricata come foto
			// potrebbe essere rimossa
			// Controlliamo se host.immagineFoto ha un'immagine salvata e la
			// ricarichiamo nella view
			MainActivity host = (MainActivity) rootView.getContext();
			if (host.immagineFoto != null) {
				System.out.println("immagine trovata");
				ImageView imageView = (ImageView) rootView
						.findViewById(R.id.photoView);
				imageView.setImageBitmap(host.immagineFoto);
			}
			Button submitRegistrationButton = (Button) rootView
					.findViewById(R.id.submitRegistrationButton);
			submitRegistrationButton
					.setOnClickListener(new OnClickSubmitRegistrationListener(rootView));
			// Fine codice di ripristino immagine nella view

		} catch (InflateException e) {
			/* map is already there, just return view as it is */
		}
		return rootView;
	}

	
	/*
	 * method used when load or make photo
	 * system open new activity, when control returns to main activity this method is called
	 * @see android.app.Fragment#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE
				&& resultCode == Activity.RESULT_OK && data != null) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = rootView.getContext().getContentResolver()
					.query(selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			picturePath = cursor.getString(columnIndex);
			Bitmap temp = BitmapFactory.decodeFile(picturePath);
			Matrix matrix = new Matrix();

			// se l'immagine non è quadrata la rendiamo quadrata tagliando le
			// parti in eccesso
			int width = temp.getWidth();
			int height = temp.getHeight();
			// se l'immagine non è quadrata la rendiamo quadrata tagliando le
			// parti in eccesso
			int offsetX = 0;
			int offsetY = 0;
			if (width > height) {
				offsetX = (width - height) / 2;
				temp = Bitmap.createBitmap(temp, offsetX, 0, height, height,
						matrix, true);
			} else if (height > width) {
				offsetY = (height - width) / 2;
				temp = Bitmap.createBitmap(temp, 0, offsetY, width,
						width, matrix, true);
			}

			temp = temp.createScaledBitmap(temp, 100, 100, true);
			// temp = Bitmap.createBitmap(temp, 1, 1, temp.getWidth(),
			// temp.getHeight(), matrix, true);
			MainActivity host = (MainActivity) rootView.getContext();
			host.immagineFoto = temp;
			cursor.close();
			ImageView imageView = (ImageView) rootView
					.findViewById(R.id.photoView);
			imageView.setImageBitmap(temp);

		}
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				Matrix matrix = new Matrix();
				Bitmap temp = (Bitmap) data.getExtras().get("data");
				// se l'immagine non è quadrata la rendiamo quadrata tagliando
				// le parti in eccesso
				int width = temp.getWidth();
				int height = temp.getHeight();
				int offsetX = 0;
				int offsetY = 0;
				if (width > height) {
					offsetX = (width - height) / 2;
					temp = Bitmap.createBitmap(temp, offsetX, 0, height,
							height, matrix, true);
				} else if (height > width) {
					offsetY = (height - width) / 2;
					temp = Bitmap.createBitmap(temp, 0, offsetY, width,
							width, matrix, true);
				}
				temp = temp.createScaledBitmap(temp, 100, 100, true);
				MainActivity host = (MainActivity) rootView.getContext();
				host.immagineFoto = temp;
				ImageView imageView = (ImageView) rootView
						.findViewById(R.id.photoView);
				imageView.setImageBitmap(temp);

			}
		}

	}

}
