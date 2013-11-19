package it.meet.login;

import java.net.URL;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

import it.meet.activities.MainActivity;
import it.meet.activities.StartActivity;
import it.meet.service.common.entity.ResponseDTO;
import it.meet.service.user.UserService;
import it.meet.service.user.entity.UserDTO;
import it.meet.utils.ErrorsAdministrator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.widget.Toast;

public class LoginTask extends AsyncTask<String, Boolean, Void> {
	private Activity context;
	private ProgressDialog progressBar;
	String PREFS_NAME = "MeetPreferFile";
	static SharedPreferences storedInfo;
	Editor preferencesEditor;
	String userLogin;
	String userPassword;

	public LoginTask(Activity context) {
		this.context = context;
		storedInfo = context.getSharedPreferences(PREFS_NAME, 0);
		preferencesEditor = storedInfo.edit();
		// TODO Auto-generated constructor stub
	}

	ResponseDTO response;

	@Override
	protected Void doInBackground(String... arg0) {
		response = null;
		JsonRpcHttpClient client;
		try {
			client = new JsonRpcHttpClient(new URL(
					"http://192.168.1.19:8080/JSONServlet"));
			UserService userService = ProxyUtil.createClientProxy(getClass()
					.getClassLoader(), UserService.class, client);
			userLogin = arg0[0];
			userPassword = arg0[1];
			response = userService.login(userLogin, userPassword);
			return null;
		} catch (Exception e) {
			response = new ResponseDTO();
			response.setErrorCode("MEET0055");
			return null;
		}
	}

	@Override
	protected void onPreExecute() {
		progressBar = new ProgressDialog(context);
		progressBar.setCancelable(true);
		progressBar.setMessage("Attendere ...");
		progressBar.setProgressStyle(ProgressDialog.THEME_HOLO_DARK);
		progressBar.setIndeterminate(true);
		progressBar.setCancelable(false);
		progressBar.show();
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if (progressBar != null)
			progressBar.dismiss();
		if (response != null) {
			if (response.getErrorCode().toString().equals("MEET0000")) {
				Toast.makeText(
						context,
						ErrorsAdministrator.getDescription("successfullyLogin",
								context), Toast.LENGTH_SHORT).show();
				context.startActivity(new Intent(context, MainActivity.class));
				context.finish();
				preferencesEditor.putString("loggedUser", userLogin);
				preferencesEditor.commit();
			} else {
				Toast.makeText(
						context,
						ErrorsAdministrator.getDescription(
								response.getErrorCode(), context),
						Toast.LENGTH_LONG).show();
			}
		}
	}

}
