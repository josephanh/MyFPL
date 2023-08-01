package nta.com.music.myfpl.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import nta.com.music.myfpl.R;
import render.animations.Render;

public class Custom_dialog_NotConnection {
    Context context;
    AlertDialog dialog;


    public Custom_dialog_NotConnection(Context context) {
        this.context = context;
        SetUpDialog();
    }
    private void SetUpDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View customView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_noconnection, null);
        builder.setView(customView);
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Render render = new Render(context);
//        render.setAnimation(Bounce.In(layout_dialog).setDuration(500));
//        render.start();
    }
    public void Show(){
        dialog.show();
    }
    public void Hide(){
        dialog.dismiss();
    }
}

