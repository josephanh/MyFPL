package nta.com.music.myfpl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.model.Information;
import nta.com.music.myfpl.model.Schedule;
import nta.com.music.myfpl.viewholder.InformationViewHolder;
import nta.com.music.myfpl.viewholder.ScheduleViewHolder;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {
    Context context;
    List<Schedule> list;

    public ScheduleAdapter(Context context, List<Schedule> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_item_schedule, parent,false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        holder.tv_room.setText("Room"+""+list.get(position).getRoom());
        holder.tv_teacher.setText(list.get(position).getTeacher());
        holder.tv_subject.setText(list.get(position).getSubject());
        holder.tv_school.setText(list.get(position).getShift_school()+"");
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
