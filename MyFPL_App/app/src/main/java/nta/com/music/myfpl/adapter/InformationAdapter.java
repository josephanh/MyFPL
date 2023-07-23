package nta.com.music.myfpl.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.interfaces.OnClickInformation;
import nta.com.music.myfpl.model.Information;
import nta.com.music.myfpl.viewholder.InformationViewHolder;

public class InformationAdapter extends RecyclerView.Adapter<InformationViewHolder> {
    Context context;
    List<Information> list;
    OnClickInformation information;

    public InformationAdapter(Context context, List<Information> list) {
        this.context = context;
        this.list = list;
    }

    public InformationAdapter(Context context, List<Information> list, OnClickInformation information) {
        this.context = context;
        this.list = list;
        this.information = information;
    }

    @NonNull
    @Override
    public InformationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_item_informations, parent,false);
        return new InformationViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InformationViewHolder holder, int position) {
        holder.tv_name.setText(list.get(position).getName());
        setIconRoom(list.get(position).getName(),holder.img_name);
        holder.tv_time.setText(list.get(position).getTime()+":00 AM");
        holder.tv_author.setText(list.get(position).getAuthor());
        holder.tv_notification.setText(list.get(position).getNotification());

        holder.layout_item_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                information.onClick(list.get(position));
            }
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

    private void setIconRoom(String name, ImageView image) {
        if(name.equals("Công tác sinh viên") || name.contains("công tác")) {
            image.setImageResource(R.drawable.ic_ctsv);
        }
        if(name.equals("Phòng đào tạo") || name.contains("đào tạo")) {
            image.setImageResource(R.drawable.ic_pdt);
        }
        if(name.equals("Phòng quan hệ doanh nghiệp") || name.contains("quan hệ")){
            image.setImageResource(R.drawable.ic_qhdn);
        }

    }
}
