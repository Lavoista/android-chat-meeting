package tom.meets;

import it.meet.service.user.entity.UserDTO;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import tom.meets.MainActivity.PlanetFragment;
import android.R.color;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
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
	EditText campoData;
	public static final String ARG_PLANET_NUMBER = "planet_number";
	private static int RESULT_LOAD_IMAGE = 1;
	private static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private String picturePath = "";

	public RegistrationFragment() {
		// Empty constructor required for fragment subclasses
	}

	public void setRootView(View _rootView) {
		rootView = _rootView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		System.out.println("creataView");
		if (rootView != null) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null)
				parent.removeView(rootView);
		}
		try {
			rootView = inflater.inflate(R.layout.registration_fragment,
					container, false);
			campoData = (EditText) rootView.findViewById(R.id.EditTextData);
			campoData.setFocusableInTouchMode(false);
			campoData.setRawInputType(InputType.TYPE_NULL);

			campoData.setOnClickListener(new EditText.OnClickListener() {

				public void onClick(View v) {
					OnDateSetListener myDateSetListener = new OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker datePicker, int i,
								int j, int k) {
							campoData.setText("" + k + "/" + (j + 1) + "/" + i);
							Calendar c = Calendar.getInstance();
							DateFormat dateFormat = android.text.format.DateFormat
									.getDateFormat(getActivity());
							c.set(i, j, k);
							Date data = new Date(c.getTimeInMillis());
							campoData.setText("" + dateFormat.format(data));

						}
					};

					DatePickerDialog dpdFromDate = new DatePickerDialog(
							getActivity(), myDateSetListener, 1, 1, 1990);
					dpdFromDate.getDatePicker().setBackgroundColor(color.black);
					dpdFromDate.getDatePicker().updateDate(1990, 0, 1);
					int currentapiVersion = android.os.Build.VERSION.SDK_INT;
					System.out.println("versione API:" + currentapiVersion);
					Log.w("TOM", "" + currentapiVersion);
					if (currentapiVersion >= 11) {
						try {
							dpdFromDate.getDatePicker().setCalendarViewShown(
									false);
						} catch (Exception e) {
							e.printStackTrace();
						} // eat exception in our case
					}
					dpdFromDate.show();
					dpdFromDate.setButton(DialogInterface.BUTTON_NEGATIVE,
							"Cancel", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == DialogInterface.BUTTON_NEGATIVE) {
										// et_to_date.setText("");
									}
								}
							});

				}

			});

			Button caricaFoto = (Button) rootView
					.findViewById(R.id.buttonCaricaFoto);
			caricaFoto.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent i = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					startActivityForResult(i, RESULT_LOAD_IMAGE);
				}
			});
			Button scattaFoto = (Button) rootView
					.findViewById(R.id.buttonScattaFoto);
			scattaFoto.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent i = new Intent(
							android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(i,
							CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
				}
			});
			// Gestione pulsanti rotazione foto sinistra e destra
			Button ruotaSinistra = (Button) rootView
					.findViewById(R.id.ruotaFotoSinistra);
			ruotaSinistra.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					MainActivity host = (MainActivity) rootView.getContext();
					Matrix matrix = new Matrix();
					matrix.postRotate(270.0f);
					ImageView imageView = (ImageView) rootView
							.findViewById(R.id.viewFoto);
					imageView.buildDrawingCache();
					Bitmap myImg = host.immagineFoto;
					Bitmap resizedBitmap = Bitmap.createBitmap(myImg, 0, 0,
							myImg.getWidth(), myImg.getHeight(), matrix, true);
					host.immagineFoto = resizedBitmap;
					imageView.setImageBitmap(resizedBitmap);

				}
			});
			Button ruotaDestra = (Button) rootView
					.findViewById(R.id.ruotaFotoDestra);
			ruotaDestra.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					MainActivity host = (MainActivity) rootView.getContext();
					Matrix matrix = new Matrix();
					matrix.postRotate(90.0f);
					ImageView imageView = (ImageView) rootView
							.findViewById(R.id.viewFoto);
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
						.findViewById(R.id.viewFoto);
				imageView.setImageBitmap(host.immagineFoto);
			}
			Button BottoneConfermaRegistrazione = (Button) rootView
					.findViewById(R.id.confermaRegistrazioneButton);
			BottoneConfermaRegistrazione
					.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							RegistrationTask rt = new RegistrationTask((MainActivity)rootView.getContext());
							UserDTO utente = new UserDTO();
							utente.setUsername("Prova Username");
							utente.setPassword("Prova password");
							utente.setEmail("tom78@kk.com");
							utente.setSex("M");
							rt.execute(utente);

						}

					});
			// Fine codice di ripristino immagine nella view

		} catch (InflateException e) {
			/* map is already there, just return view as it is */
		}
		return rootView;
	}

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
			int larghezza = temp.getWidth();
			int altezza = temp.getHeight();
			// se l'immagine non è quadrata la rendiamo quadrata tagliando le
			// parti in eccesso
			int offsetX = 0;
			int offsetY = 0;
			System.out.println("temp.getWidth()=" + temp.getWidth());
			System.out.println("temp.getHeight()=" + temp.getHeight());
			if (larghezza > altezza) {
				offsetX = (larghezza - altezza) / 2;
				temp = Bitmap.createBitmap(temp, offsetX, 0, altezza, altezza,
						matrix, true);
			} else if (altezza > larghezza) {
				offsetY = (altezza - larghezza) / 2;
				temp = Bitmap.createBitmap(temp, 0, offsetY, larghezza,
						larghezza, matrix, true);
			}
			System.out.println("offsetX=" + offsetX);
			System.out.println("temp.getWidth()=" + temp.getWidth());
			System.out.println("temp.getHeight()=" + temp.getHeight());

			temp = temp.createScaledBitmap(temp, 100, 100, true);
			// temp = Bitmap.createBitmap(temp, 1, 1, temp.getWidth(),
			// temp.getHeight(), matrix, true);
			MainActivity host = (MainActivity) rootView.getContext();
			host.immagineFoto = temp;
			cursor.close();
			ImageView imageView = (ImageView) rootView
					.findViewById(R.id.viewFoto);
			imageView.setImageBitmap(temp);

		}
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			System.out.println("cambio immagine 1");
			if (resultCode == Activity.RESULT_OK) {
				System.out.println("cambio immagine 2");
				Matrix matrix = new Matrix();
				Bitmap temp = (Bitmap) data.getExtras().get("data");
				// se l'immagine non è quadrata la rendiamo quadrata tagliando
				// le parti in eccesso
				int larghezza = temp.getWidth();
				int altezza = temp.getHeight();
				int offsetX = 0;
				int offsetY = 0;
				System.out.println("temp.getWidth()=" + temp.getWidth());
				System.out.println("temp.getHeight()=" + temp.getHeight());
				if (larghezza > altezza) {
					offsetX = (larghezza - altezza) / 2;
					temp = Bitmap.createBitmap(temp, offsetX, 0, altezza,
							altezza, matrix, true);
				} else if (altezza > larghezza) {
					offsetY = (altezza - larghezza) / 2;
					temp = Bitmap.createBitmap(temp, 0, offsetY, larghezza,
							larghezza, matrix, true);
				}
				temp = temp.createScaledBitmap(temp, 100, 100, true);
				MainActivity host = (MainActivity) rootView.getContext();
				host.immagineFoto = temp;
				ImageView imageView = (ImageView) rootView
						.findViewById(R.id.viewFoto);
				imageView.setImageBitmap(temp);

			}
		}

	}

}
