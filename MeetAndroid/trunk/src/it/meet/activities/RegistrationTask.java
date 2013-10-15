package it.meet.activities;

import java.net.MalformedURLException;
import java.net.URL;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

import it.meet.service.common.entity.ResponseDTO;
import it.meet.service.user.UserService;
import it.meet.service.user.entity.UserDTO;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import android.widget.Toast;

public class RegistrationTask extends AsyncTask<UserDTO,Boolean,Void>{
	private Activity context;
	
	public RegistrationTask(Activity context) {
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	
	ResponseDTO response;
	@Override
	protected Void doInBackground(UserDTO... arg0) {

			JsonRpcHttpClient client;
			try {
				client = new JsonRpcHttpClient(new URL("http://192.168.1.9:8080/JSONServlet"));
				UserService userService = ProxyUtil.createClientProxy(getClass().getClassLoader(), UserService.class, client);
				UserDTO userToCreate = arg0[0];
				response = userService.createUser(userToCreate);	
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Toast.makeText(context, response.getErrorDescription(), Toast.LENGTH_LONG).show();	     
	}

}
