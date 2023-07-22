package nta.com.music.myfpl.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.model.Information;

public class ScheduleViewHolder extends RecyclerView.ViewHolder  {

    public TextView tv_teacher,tv_room,tv_subject,tv_time_start,tv_time_end,tv_school;
    public void bindData(Information informations) {

        tv_teacher.setText(informations.getName());

    }
    public ScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
      tv_teacher = itemView.findViewById(R.id.tv_teacher);
      tv_room = itemView.findViewById(R.id.tv_room);
      tv_subject = itemView.findViewById(R.id.tv_subject);
      tv_time_start = itemView.findViewById(R.id.tv_time_start);
      tv_time_end = itemView.findViewById(R.id.tv_time_end);
      tv_school = itemView.findViewById(R.id.tv_school);
    }
}
