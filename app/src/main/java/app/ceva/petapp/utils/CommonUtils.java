/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package app.ceva.petapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;

import androidx.appcompat.app.AlertDialog;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.ceva.petapp.R;

/**
 * Created by janisharali on 27/01/17.
 */

public final class CommonUtils {

    private static final String TAG = "CommonUtils";
    private static Typeface typefaceBold = null;
    private static Typeface typefaceRegular = null;
    private static Typeface typefaceSemibold = null;
    private static Typeface typefaceMedium = null;

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }



    public static void showAlrt(Context context,String msg)
    {
        new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                       // Activity activity = (Activity) context;
                       // activity.finish();
                    }
                })
                .show();
    }


    public static void openNewScreen(Class<?> cls, Bundle b,Context context)
    {

        Intent intent=new Intent(context,cls);
        if(b!=null)
            intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
    public static void openNewScreenWithFinish(Class<?> cls, Bundle b,Context context)
    {

        Intent intent=new Intent(context,cls);
        if(b!=null)
            intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public  static boolean isValidPrice(String price){
        Pattern pattern;
        Matcher matcher;
        final String PRICE_PATTERN = "^([0-9]{1,4})(.[0-9]{1,2})?$";
        pattern = Pattern.compile(PRICE_PATTERN);
        matcher = pattern.matcher(price);
        return matcher.matches();
    }
    public  static boolean isValidPassword(String password){
        Pattern pattern;
        Matcher matcher;
        final String PRICE_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
        pattern = Pattern.compile(PRICE_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }




    public static String loadJSONFromAsset(Context context, String jsonFileName)
            throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }

    }



    public static String getLocationText(Location location) {
        return location == null ? "Unknown location" :
                "(" + location.getLatitude() + ", " + location.getLongitude() + ")";
    }

    public static String getLocationTitle(Context context) {
        return "Location updated " +
                DateFormat.getDateTimeInstance().format(new Date());
    }

    public static boolean isGPSEnabled(Context mContext)
    {
        LocationManager lm = (LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    public static Typeface getTypefaceBold(Context context) {
        if (typefaceBold == null) {
            typefaceBold = Typeface.createFromAsset(context.getAssets(), "fonts/quicksand_bold.otf");
        }
        return typefaceBold;
    }

    public static Typeface getTypefaceSemiBold(Context context) {
        if (typefaceSemibold == null) {
            typefaceSemibold = Typeface.createFromAsset(context.getAssets(), "fonts/lato_semibold.ttf");
        }
        return typefaceSemibold;
    }
    public static Typeface getTypefaceRegular(Context context) {
        if (typefaceRegular == null) {
            typefaceRegular = Typeface.createFromAsset(context.getAssets(), "fonts/lato_regular.ttf");
        }
        return typefaceRegular;
    }
    public static Typeface getTypefaceMedium(Context context) {
        if (typefaceRegular == null) {
            typefaceRegular = Typeface.createFromAsset(context.getAssets(), "fonts/lato_medium.ttf");
        }
        return typefaceRegular;
    }
}
