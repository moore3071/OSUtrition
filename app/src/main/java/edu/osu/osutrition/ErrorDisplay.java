package edu.osu.osutrition;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ErrorDisplay {
    private static void displayError(Context caller, String title, String message) {
        new AlertDialog.Builder(caller)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private static void killApplication(Activity activity) {
        activity.finish();
    }

    public static void makeError(Context caller, String title, String message, boolean isFatal) {
        displayError(caller, title, message);
    }

    public static void makeError(Context caller, String message, boolean isFatal) {
        displayError(caller, "OSUtrition", message);
    }

    public static boolean isNetworkUp(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
