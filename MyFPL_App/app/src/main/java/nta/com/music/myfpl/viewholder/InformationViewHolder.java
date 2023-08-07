package nta.com.music.myfpl.viewholder;



import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muratozturk.click_shrink_effect.ClickShrinkEffect;

import nta.com.music.myfpl.R;
public class InformationViewHolder extends RecyclerView.ViewHolder  {

     public TextView tv_name,tv_author,tv_notification,tv_time;
     public ImageView img_name;
     public LinearLayout layout_item_information;

    public InformationViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.tv_name);
        tv_author = itemView.findViewById(R.id.tv_author);
        tv_notification = itemView.findViewById(R.id.tv_notification);
        tv_time = itemView.findViewById(R.id.tv_time);
        img_name = itemView.findViewById(R.id.img_name);
        layout_item_information = itemView.findViewById(R.id.layout_item_information);
        new ClickShrinkEffect(layout_item_information, 0.98f, 50L);
    }
}
