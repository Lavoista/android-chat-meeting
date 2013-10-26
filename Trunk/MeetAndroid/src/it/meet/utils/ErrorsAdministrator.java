package it.meet.utils;

import android.app.Activity;
import android.content.res.Resources;
import it.meet.service.common.entity.ResponseDTO;
import it.meet.service.common.util.StringUtils;


/**
 * @author tommy
 *
 */
public class ErrorsAdministrator {
	/** this method search for description into resource string.xml
	 * if operation fails return errorcode not mapped into string.xml
	 * @param response 
	 * @param activity 
	 * @return message description
	 */
	public static String getDescription(String errorCode,Activity activity) {
		try{
			String packageName = activity.getPackageName();
			int resId = activity.getResources().getIdentifier(errorCode, "string",
					packageName);
			return activity.getResources().getString(resId);
		}
		catch(Resources.NotFoundException ex){
			return "Error Code:"+errorCode ;
		}
	}

}
