package com.androtush.newsapp.Utils;

import android.app.ProgressDialog;
import android.content.Context;

public class IoUtils {

    public static ProgressDialog getProgressDialog(Context context){
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        return dialog;
    }

}
