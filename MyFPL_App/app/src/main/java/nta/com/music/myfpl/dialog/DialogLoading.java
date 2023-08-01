package nta.com.music.myfpl.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import nta.com.music.myfpl.R;
import render.animations.Render;

public class DialogLoading {

    Context context;
    Dialog dialog;

    public DialogLoading(Context context) {
        this.context = context;
        onCreate();
    }

    private void onCreate(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View customView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);

        dialog = new Dialog(context);
        dialog.setContentView(customView);

//        Glide.with(context)
//                        .load(R.raw.loading).into(imageView);
//        builder.setView(customView);
//        dialog = builder.create();
        dialog.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Render render = new Render(context);
    }

    public void show() {
        dialog.show();
    }
    public void hide() {
        dialog.dismiss();
    }


}
