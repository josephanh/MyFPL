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
import nta.com.music.myfpl.viewholder.InformationViewHolder;

public class InformationAdapter extends RecyclerView.Adapter<InformationViewHolder> {
    Context context;
    List<Information> list;

    public InformationAdapter(Context context, List<Information> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InformationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_item_informations, parent,false);
        return new InformationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InformationViewHolder holder, int position) {
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_time.setText(list.get(position).getTime()+":00 AM");
        holder.tv_author.setText(list.get(position).getAuthor());
        holder.tv_notification.setText(list.get(position).getNotification());

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
