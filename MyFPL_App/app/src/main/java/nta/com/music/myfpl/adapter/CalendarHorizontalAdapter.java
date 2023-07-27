package nta.com.music.myfpl.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.interfaces.OnClickCalendar;
import nta.com.music.myfpl.viewholder.CalendarHorizontalViewHolder;

public class CalendarHorizontalAdapter extends RecyclerView.Adapter<CalendarHorizontalViewHolder> {
    Context context;
    List<String> list;
    public static int selectedItem = -1;

    OnClickCalendar onClickCalendar;

    public CalendarHorizontalAdapter(Context context, List<String> list, OnClickCalendar onClickCalendar) {
        this.context = context;
        this.list = list;
        this.onClickCalendar = onClickCalendar;
    }

    @NonNull
    @Override
    public CalendarHorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_calendar_horizontal, parent, false);
        return new CalendarHorizontalViewHolder(view);
    }

    @Override
    @SuppressLint({"SimpleDateFormat", "ResourceAsColor"})
    public void onBindViewHolder(@NonNull CalendarHorizontalViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String[] dateOfGregory = list.get(position).split(" ");
        holder.tv_text.setText(String.valueOf(dateOfGregory[0].charAt(0)));
        holder.tv_number.setText(dateOfGregory[2]);
        if(selectedItem < 0) {
            for(int i = 0; i < list.size(); i++) {
                if(new Date().toString().contains(list.get(i))){
                    selectedItem = i;
                    break;
                }
            }
        }

        holder.layout_item_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCalendar.onClick(list.get(position));
            }
        });

        int textColor, numColor;
        if(selectedItem != position) {
            holder.layout_item_calendar.setBackgroundResource(R.drawable.white_5dp);
            textColor = context.getResources().getColor(R.color.grey_light);
            numColor = context.getResources().getColor(R.color.purple);
        } else {
            textColor = context.getResources().getColor(R.color.white);
            numColor = context.getResources().getColor(R.color.white);
            holder.layout_item_calendar.setBackgroundResource(R.drawable.custom_tv_nameinformations );

        }
        holder.tv_text.setTextColor(textColor);
        holder.tv_number.setTextColor(numColor);
        holder.layout_item_calendar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                selectedItem = position;
                onClickCalendar.onClick(list.get(position));
                notifyDataSetChanged(); // Notify the adapter that the dataset has changed to update the UI
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        else return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSelectedItem(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }

    private static String getDayOfWeekString(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "Su";
            case Calendar.MONDAY:
                return "Mo";
            case Calendar.TUESDAY:
                return "Tu";
            case Calendar.WEDNESDAY:
                return "We";
            case Calendar.THURSDAY:
                return "Th";
            case Calendar.FRIDAY:
                return "Fr";
            case Calendar.SATURDAY:
                return "Sa";
            default:
                return "";
        }
    }
}
