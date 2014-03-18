package it.meet.registration;

import java.net.URL;


import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

import it.meet.activities.LoginActivity;
import it.meet.service.common.entity.ResponseDTO;
import it.meet.service.user.UserService;
import it.meet.service.user.entity.UserDTO;
import it.meet.utils.ErrorsAdministrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class RegistrationTask extends AsyncTask<UserDTO,Boolean,Void>{
	private Activity context;
	private ProgressDialog progressBar;
	
	public RegistrationTask(Activity context) {
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	
	ResponseDTO response;
	@Override
	protected Void doInBackground(UserDTO... arg0) {
		response = null;
		JsonRpcHttpClient client;
		try {
			client = new JsonRpcHttpClient(new URL("http://192.168.1.19:8080/JSONServlet"));
			UserService userService = ProxyUtil.createClientProxy(getClass().getClassLoader(), UserService.class, client);
			UserDTO userToCreate = arg0[0];
			response = userService.createUser(userToCreate);
			return null;
		} 
		catch (Exception e) {
			response = new ResponseDTO();
			response.setErrorCode("MEET0055");
			return null;
		}
	}
	
	@Override
	protected void onPreExecute(){
		progressBar = new ProgressDialog(context);
		progressBar.setCancelable(true);
		progressBar.setMessage("Attendere ...");
		progressBar.setProgressStyle(AlertDialog.THEME_HOLO_DARK);
		progressBar.setIndeterminate(true);
		progressBar.setCancelable(false);
		progressBar.show();
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if(progressBar!=null)progressBar.dismiss();
		if(response != null){
			if(response.getErrorCode().toString().equals("MEET0000")){
				Toast.makeText(context, ErrorsAdministrator.getDescription("SUCCESSFULLY_REGISTRATION",context), Toast.LENGTH_LONG).show();	     			
				context.startActivity(new Intent(context,
						LoginActivity.class));
				context.finish();
			}
			else{
				Toast.makeText(context, ErrorsAdministrator.getDescription(response.getErrorCode(),context), Toast.LENGTH_LONG).show();
			}
		}
	}

}
