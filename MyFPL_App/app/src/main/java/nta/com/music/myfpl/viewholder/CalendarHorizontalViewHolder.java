package nta.com.music.myfpl.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muratozturk.click_shrink_effect.ClickShrinkEffect;

import nta.com.music.myfpl.R;

public class CalendarHorizontalViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_text, tv_number;
    public LinearLayout layout_item_calendar;
    public CalendarHorizontalViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_text = itemView.findViewById(R.id.tv_text);
        tv_number = itemView.findViewById(R.id.tv_number);
        layout_item_calendar = itemView.findViewById(R.id.layout_item_calendar);

        new ClickShrinkEffect(layout_item_calendar, 0.95f, 200);
    }
}
