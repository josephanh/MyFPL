package nta.com.music.myfpl.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import nta.com.music.myfpl.MainActivity;
import nta.com.music.myfpl.R;
import nta.com.music.myfpl.utils.CheckInternet;
import render.animations.Bounce;
import render.animations.Render;

public class Custom_dialog_NotConnection {
    Context context;
    AlertDialog dialog;

    public final FragmentActivity requireActivity() {
        FragmentActivity activity = (FragmentActivity) getContext();
        if (activity == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
        }
        return activity;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Custom_dialog_NotConnection(Context context) {
        this.context = context;
        SetUpDialog();
    }
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    private void SetUpDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View customView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_noconnection, null);
        builder.setView(customView);
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LinearLayout layout_dialog = customView.findViewById(R.id.layout_dialog);
        Button btn_check_internet = customView.findViewById(R.id.btn_check_internet);

        Render render = new Render(context);
        render.setAnimation(Bounce.In(layout_dialog).setDuration(100L));
        render.start();

        btn_check_internet.setOnClickListener(view -> {
            if(CheckInternet.isNetworkAvailable(context)){
                Intent intent = new Intent(getContext(), MainActivity.class);
                requireActivity().startActivity(intent);
                requireActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                requireActivity().finish();

            } else {
                Intent intent = new  Intent(Settings.ACTION_WIFI_SETTINGS);
                getContext().startActivity(intent);
                Hide();
            }
        });
    }
    public void Show(){
        dialog.show();
    }
    public void Hide(){
        dialog.dismiss();
    }
}

