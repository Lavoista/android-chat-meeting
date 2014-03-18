package it.meet.activities;

import it.meet.R;
import it.meet.registration.OnClickDateFieldListener;
import it.meet.registration.OnClickSubmitRegistrationListener;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.text.InputType;
import android.view.InflateException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class RegistrationActivity extends Activity{
	EditText dateField;
	public static final String ARG_PLANET_NUMBER = "planet_number";
	private static int RESULT_LOAD_IMAGE = 1;
	private static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private String picturePath = "";
	private Bitmap photoImage;

	public Bitmap getPhotoImage() {
		return photoImage;
	}

	public void setPhotoImage(Bitmap image) {
		photoImage = image;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null){
			photoImage = savedInstanceState.getParcelable("photoImage");
        }
		setTitle("Registrazione Utente");
		try {
			setContentView(R.layout.registration_activity);
			dateField = (EditText) findViewById(R.id.birthDate);
			dateField.setFocusableInTouchMode(false);
			dateField.setRawInputType(InputType.TYPE_NULL);
			dateField.setOnClickListener(new OnClickDateFieldListener(
					this));

			Button loadPhoto = (Button)findViewById(R.id.loadPhotoButton);
			loadPhoto.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent i = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					startActivityForResult(i, RESULT_LOAD_IMAGE);
				}
			});
			Button makePhoto = (Button)findViewById(R.id.makePhotoButton);
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
			Button rotateLeft = (Button)findViewById(R.id.rotateLeftButton);
			rotateLeft.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if(photoImage==null){
						return;
					}
					Matrix matrix = new Matrix();
					matrix.postRotate(270.0f);
					ImageView imageView = (ImageView)findViewById(R.id.photoView);
					imageView.buildDrawingCache();
					Bitmap resizedBitmap = Bitmap.createBitmap(photoImage, 0,
							0, photoImage.getWidth(), photoImage.getHeight(),
							matrix, true);
					photoImage = resizedBitmap;
					imageView.setImageBitmap(resizedBitmap);

				}
			});
			Button rotateRight = (Button)findViewById(R.id.rotateRightButton);
			rotateRight.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if(photoImage==null){
						return;
					}
					Matrix matrix = new Matrix();
					matrix.postRotate(90.0f);
					ImageView imageView = (ImageView) findViewById(R.id.photoView);
					imageView.buildDrawingCache();
					Bitmap resizedBitmap = Bitmap.createBitmap(photoImage, 0,
							0, photoImage.getWidth(), photoImage.getHeight(),
							matrix, true);
					photoImage = resizedBitmap;
					imageView.setImageBitmap(resizedBitmap);

				}
			});
			// Fine gestione pulsanti rotazione foto

			// Nell'aggiornamento della view l'immagine caricata come foto
			// potrebbe essere rimossa
			// Controlliamo se host.immagineFoto ha un'immagine salvata e la
			// ricarichiamo nella view
			if (photoImage != null ) {
				System.out.println("immagine trovata");
				ImageView imageView = (ImageView) findViewById(R.id.photoView);
				imageView.setImageBitmap(photoImage);
			}
			Button submitRegistrationButton = (Button) findViewById(R.id.submitRegistrationButton);
			submitRegistrationButton
					.setOnClickListener(new OnClickSubmitRegistrationListener(
							this));
			// Fine codice di ripristino immagine nella view

		} catch (InflateException e) {
			/* map is already there, just return view as it is */
		}
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
			String[] filePathColumn = { MediaColumns.DATA };
			Cursor cursor = getContentResolver()
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

			temp = Bitmap.createScaledBitmap(temp, 100, 100, true);
			// temp = Bitmap.createBitmap(temp, 1, 1, temp.getWidth(),
			// temp.getHeight(), matrix, true);
			photoImage = temp;
			cursor.close();
			ImageView imageView = (ImageView)findViewById(R.id.photoView);
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
				temp = Bitmap.createScaledBitmap(temp, 100, 100, true);
				photoImage = temp;
				ImageView imageView = (ImageView)findViewById(R.id.photoView);
				imageView.setImageBitmap(temp);

			}
		}

	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
	    super.onSaveInstanceState(outState);
	    outState.putParcelable("photoImage", this.photoImage);
	}
	
}
