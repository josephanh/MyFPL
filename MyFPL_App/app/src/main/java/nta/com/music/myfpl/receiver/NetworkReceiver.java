package nta.com.music.myfpl.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import nta.com.music.myfpl.dialog.Custom_dialog_NotConnection;
import nta.com.music.myfpl.utils.CheckInternet;

public class NetworkReceiver extends BroadcastReceiver {

    @SuppressLint("StaticFieldLeak")
    private static Custom_dialog_NotConnection dialogVocab;


    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        dialogVocab = new Custom_dialog_NotConnection(context);
        if (CheckInternet.isNetworkAvailable(context)) {
            dialogVocab.Hide();
//            Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show();
        } else {

            dialogVocab.Show();

        }
    }

    public boolean isConnected(Context context) {

        try {
            ConnectivityManager cm = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());

        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }

    }


}
