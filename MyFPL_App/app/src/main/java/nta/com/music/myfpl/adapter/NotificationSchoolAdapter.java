package nta.com.music.myfpl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import nta.com.music.myfpl.DTO.ListInformationResponseDTO;
import nta.com.music.myfpl.R;
import nta.com.music.myfpl.model.NotificationSchool;
import render.animations.Attention;
import render.animations.Render;

public class NotificationSchoolAdapter extends RecyclerView.Adapter<NotificationSchoolAdapter.ViewHolder> {
    Context c;
//    List<NotificationSchool> list;
    List<ListInformationResponseDTO.InformationResponseDTO> list;

    public NotificationSchoolAdapter(Context c,List<ListInformationResponseDTO.InformationResponseDTO> list){
        this.c = c;
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListInformationResponseDTO.InformationResponseDTO item =list.get(position);
        holder.tvNotification.setText(item.getDepartment());
        holder.tvNotificationTitle.setText(item.getTitle());
        holder.tvNotificationDate.setText(item.getCreated_at());


        //check if date is today
//        String today = (String) android.text.format.DateFormat.format(
//                "dd/MM/yyyy", new java.util.Date());
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Date date1;
//        Date date2;
//        try {
//            date1 = sdf.parse(item.getCreated_at());
//            date2 = sdf.parse(today);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        if(date1.before(date2)){
//            holder.tvNotificationDate.setText(item.getDate());
//        }
//        else{
//            holder.tvNotificationDate.setText("Now");
//        }

        //holder.imgNotification.setImageDrawable(c.getResources().getDrawable(R.drawable.ic_schoolnotification));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(position);
                    Render render = new Render(c);
                    render.setAnimation(Attention.Pulse(holder.itemView).setDuration(10));
                    render.start();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgNotification;
        TextView tvNotification,tvNotificationTitle, tvNotificationDate, tvNotificationView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNotification =itemView.findViewById(R.id.imgNotification);
            tvNotification =itemView.findViewById(R.id.tvNotification);
            tvNotificationTitle =itemView.findViewById(R.id.tvNotificationTitle);
            tvNotificationDate =itemView.findViewById(R.id.tvNotificationDate);
            tvNotificationView =itemView.findViewById(R.id.tvNotificationView);
        }
    }
}
