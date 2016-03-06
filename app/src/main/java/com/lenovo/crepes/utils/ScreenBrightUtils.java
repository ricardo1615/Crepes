package com.lenovo.crepes.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by Lenovo on 2015/11/22.
 */
public class ScreenBrightUtils {

    public static boolean isAutoBrightness(ContentResolver resolver) {
        boolean isAtuo = false;
        try {
            Log.i("AAAA", Settings.System.getInt(resolver, Settings.System.SCREEN_BRIGHTNESS_MODE) + "  " + Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
            isAtuo = Settings.System.getInt(resolver, Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return isAtuo;
    }

    public static void stopAutoBrightness(ContentResolver resolver) {
        Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }

    public static void startAutoBrightness(ContentResolver resolver) {
        Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    }

    public static int getBrightness(ContentResolver resolver) {
        int brightness = 0;

        try {
            brightness = Settings.System.getInt(resolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        return brightness;
    }

    public static void setBrightness(Activity activity, int brightness) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.screenBrightness = brightness;
        Settings.System.putInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);
        activity.getWindow().setAttributes(attributes);
    }
}
