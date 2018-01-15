package com.cga;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import android.support.v4.app.NotificationManagerCompat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import org.json.JSONObject;
import org.json.JSONException;

public class NotificationChecker extends CordovaPlugin {
    public static String TAG = "NotificationChecker";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("checkStatus")) {
            this.checkStatus(callbackContext);
            return true;
        }
        return false;
    }

    private void checkStatus(CallbackContext callbackContext) {
        try {
            boolean notifications = NotificationManagerCompat.from(cordova.getActivity().getApplicationContext()).areNotificationsEnabled();
            JSONObject resultObj = new JSONObject();
            resultObj.put("enabled", notifications);
            callbackContext.success(resultObj);
        }
        catch(JSONException ex) {
            callbackContext.error("Unexpected error.");
        }
    }
}
