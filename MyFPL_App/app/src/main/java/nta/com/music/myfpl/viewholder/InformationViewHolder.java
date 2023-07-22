package nta.com.music.myfpl.viewholder;



import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.model.Information;

public class InformationViewHolder extends RecyclerView.ViewHolder  {

     public TextView tv_name,tv_author,tv_notification,tv_time;
    public void bindData(Information informations) {

        tv_name.setText(informations.getName());

    }
    public InformationViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.tv_name);
        tv_author = itemView.findViewById(R.id.tv_author);
        tv_notification = itemView.findViewById(R.id.tv_notification);
        tv_time = itemView.findViewById(R.id.tv_time);
    }
}
