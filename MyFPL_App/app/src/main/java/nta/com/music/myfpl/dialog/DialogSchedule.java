package nta.com.music.myfpl.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.model.Schedule;

public class DialogSchedule {
    Context context;
    AlertDialog dialog;
    Schedule schedule;
    public DialogSchedule(Context context) {
        this.context = context;
        setUpDialog();
    }

    public DialogSchedule(Context context, Schedule schedule) {
        this.context = context;
        this.schedule = schedule;
        setUpDialog();
    }
    TextView tv_room, tv_time, tv_subject, tv_teacher, tv_address, day_name;
    @SuppressLint({"MissingInflatedId", "LocalSuppress", "SetTextI18n"})
    public void setUpDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View customView = LayoutInflater.from(context).inflate(R.layout.dialog_schedule, null);
        builder.setView(customView);
        dialog = builder.create();
        dialog.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tv_room = customView.findViewById(R.id.tv_room);
        tv_time = customView.findViewById(R.id.tv_time);
        tv_subject = customView.findViewById(R.id.tv_subject);
        tv_teacher = customView.findViewById(R.id.tv_teacher);
        tv_address = customView.findViewById(R.id.tv_address);
        day_name = customView.findViewById(R.id.day_name);

        if(schedule != null){
            tv_room.setText(schedule.getRoom());
            tv_time.setText("Ca " +schedule.getTime());
            tv_subject.setText(schedule.getCourse_name());
            tv_teacher.setText(schedule.getTeacher_name());
            tv_address.setText(schedule.getAddress());
            day_name.setText(schedule.getDay());
            if(schedule.getAddress() == null) {
                tv_address.setText("Công viên phần mềm Quang Trung");
            }
        }


    }

    public void Show(){
        dialog.show();
    }
    public void Hide(){
        dialog.dismiss();
    }
}
