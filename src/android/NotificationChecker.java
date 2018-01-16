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
import android.content.Intent;
import android.provider.Settings;
import android.content.Context;
import android.net.Uri;

public class NotificationChecker extends CordovaPlugin {
    public static String TAG = "NotificationChecker";
    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("checkStatus")) {
            this.callbackContext = callbackContext;
            this.checkStatus();
            return true;
        } else if (action.equals("openSettings")) {
            this.callbackContext = callbackContext;
            Context context=this.cordova.getActivity().getApplicationContext();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            intent.addCategory(Intent.CATEGORY_DEFAULT);

            this.cordova.startActivityForResult((CordovaPlugin) this, intent, 0);
            return true;
        }
        return false;
    }

    private void checkStatus() {
        try {
            boolean notifications = NotificationManagerCompat.from(cordova.getActivity().getApplicationContext()).areNotificationsEnabled();
            JSONObject resultObj = new JSONObject();
            resultObj.put("enabled", notifications);
            this.callbackContext.success(resultObj);
        }
        catch(JSONException ex) {
            this.callbackContext.error("Unexpected error.");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.checkStatus();
    }
}
