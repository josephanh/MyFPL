package nta.com.music.myfpl.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.interfaces.OnClickSchedule;
import nta.com.music.myfpl.model.Information;
import nta.com.music.myfpl.model.Schedule;
import nta.com.music.myfpl.viewholder.InformationViewHolder;
import nta.com.music.myfpl.viewholder.ScheduleViewHolder;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {
    Context context;
    List<Schedule> list;

    OnClickSchedule onClickSchedule;
    int status;
//    0 là lịch tuần: 1 là lịch tháng

    public ScheduleAdapter(Context context, List<Schedule> list) {
        this.context = context;
        this.list = list;
    }

    public ScheduleAdapter(Context context, List<Schedule> list, int status,  OnClickSchedule onClickSchedule) {
        this.context = context;
        this.list = list;
        this.onClickSchedule = onClickSchedule;
        this.status = status;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_item_schedule, parent,false);
        return new ScheduleViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        holder.tv_room.setText("Room "+list.get(position).getRoom());
        holder.tv_teacher.setText(list.get(position).getTeacher());
        holder.tv_subject.setText(list.get(position).getSubject());
        holder.tv_school.setText("0"+list.get(position).getShift_school());

        switch (list.get(position).getShift_school()){
            case 1: {
                holder.tv_time_start.setText("7:30");
                holder.tv_time_end.setText("9:30");
                break;
            }
            case 2: {
                holder.tv_time_start.setText("9:45");
                holder.tv_time_end.setText("11:45");
                break;
            }
            case 3: {
                holder.tv_time_start.setText("13:00");
                holder.tv_time_end.setText("15:00");
                break;
            }
            case 4: {
                holder.tv_time_start.setText("15:15");
                holder.tv_time_end.setText("17:15");
                break;
            }
            case 5: {
                holder.tv_time_start.setText("17:30");
                holder.tv_time_end.setText("19:30");
                break;
            }
            case 6: {
                holder.tv_time_start.setText("19:30");
                holder.tv_time_end.setText("20:30");
                break;
            }
        }

        if(status == 0) {
            holder.img_lines.setVisibility(View.GONE);
        } else {
            holder.img_lines.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }
}
