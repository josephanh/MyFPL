package nta.com.music.myfpl.adapter;

import static nta.com.music.myfpl.adapter.ViewPagerSchedule.CALENDAR_WEEK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    long timeStamp = 0;

    private Date date;
    HashMap<String, Integer> datePosition = new HashMap<>();

    public ScheduleAdapter(Context context, List<Schedule> list) {
        this.context = context;
        this.list = list;

    }

    public ScheduleAdapter(Context context, List<Schedule> list, int status,  OnClickSchedule onClickSchedule) {
        this.context = context;
        this.list = list;
        this.onClickSchedule = onClickSchedule;
        this.status = status;

        for (int i = 0; i < list.size(); i++) {
            String dateSchedule = list.get(i).getDate();

            if (datePosition.get(dateSchedule) == null) {

                datePosition.put(dateSchedule, i);
            }
        }

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
        String date = list.get(position).getDate();
        if(position == 0){
            holder.firstLine.setBackgroundResource(R.drawable.white_5dp);
        }
        if(datePosition.get(date) != null && datePosition.get(date) == position) {
            holder.layout_date.setVisibility(View.VISIBLE);
            holder.dateTime.setText(date);
        } else {
            holder.layout_date.setVisibility(View.GONE);
        }


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

        if(status == CALENDAR_WEEK) {
            holder.img_lines.setVisibility(View.GONE);
            holder.layout_date.setVisibility(View.GONE);
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
    @SuppressLint("SimpleDateFormat")
    public long setCurrentTimeStamp(String dateString){
        int year = Integer.parseInt(dateString.split("-")[0]);
        int month = Integer.parseInt(dateString.split("-")[1]);
        int day = Integer.parseInt(dateString.split("-")[2]);
        String formatPattern = "yyyy-M-d";
        long timestamp;
        try {
            // Tạo đối tượng SimpleDateFormat với định dạng cho chuỗi ngày tháng
            SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);

            // Chuyển chuỗi ngày tháng thành đối tượng Date
            Date date = sdf.parse(dateString);

            // Lấy giá trị timestamp từ đối tượng Date (số mili-giây kể từ mốc thời gian Unix)
            if (date != null) timestamp = date.getTime();
            else timestamp = 0;

        } catch (ParseException e) {
            e.printStackTrace();
            timestamp = 0;
        }

        return timestamp;
    }
}
