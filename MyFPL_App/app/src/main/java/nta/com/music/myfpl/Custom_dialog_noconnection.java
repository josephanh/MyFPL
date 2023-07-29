package nta.com.music.myfpl;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.adapter.ChooseCampusItemAdapter;
import nta.com.music.myfpl.model.Campus;
import render.animations.Bounce;
import render.animations.Render;

public class Custom_dialog_noconnection {
    Context context;
    AlertDialog dialog;


    public Custom_dialog_noconnection(Context context) {
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

