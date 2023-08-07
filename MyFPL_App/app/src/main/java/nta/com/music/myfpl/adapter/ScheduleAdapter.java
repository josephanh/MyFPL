package nta.com.music.myfpl.adapter;

import static nta.com.music.myfpl.adapter.ViewPagerSchedule.CALENDAR_WEEK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.interfaces.OnClickSchedule;
import nta.com.music.myfpl.model.Schedule;
import nta.com.music.myfpl.viewholder.ScheduleViewHolder;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {
    Context context;
    List<Schedule> list;

    public ScheduleAdapter() {

    }

    public void updateData(List<Schedule> newData) {
        try {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    list = newData;
                    notifyDataSetChanged(); // Thông báo cho RecyclerView biết về sự thay đổi dữ liệu
                }
            });
        } catch (Exception e) {
            Log.d(">>>>TAG", "updateData: Lỗi lấy lịch học: "+e.getMessage());
        }
    }



    OnClickSchedule onClickSchedule;
    int status;
//    0 là lịch tuần: 1 là lịch tháng

    long timeStamp = 0;

    private Date date;
    HashMap<String, Integer> datePosition = new HashMap<>();
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
        holder.tv_teacher.setText(list.get(position).getTeacher_name());
        holder.tv_subject.setText(list.get(position).getCourse_name());
        holder.tv_school.setText("0"+list.get(position).getTime());

        if(datePosition.get(date) == null){
            for (int i = 0; i < list.size(); i++) {
                String dateSchedule = list.get(i).getDay();
                if (datePosition.get(dateSchedule) == null) {

                    datePosition.put(dateSchedule, i);
//                    Log.d("TAG ADAPTER", "onBindViewHolder: "+dateSchedule);
                }
            }
        }

        String date = list.get(position).getDay();
        if(position == 0){
            holder.firstLine.setBackgroundResource(R.drawable.white_5dp);
        }
        if(datePosition.get(date) != null && datePosition.get(date) == position) {
            holder.layout_date.setVisibility(View.VISIBLE);
            holder.dateTime.setText(date);

        } else {
            holder.layout_date.setVisibility(View.GONE);
        }


        switch (list.get(position).getTime()){
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

        holder.item_schedule.setOnClickListener(view -> {
            onClickSchedule.onClick(list.get(position));
        });
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
