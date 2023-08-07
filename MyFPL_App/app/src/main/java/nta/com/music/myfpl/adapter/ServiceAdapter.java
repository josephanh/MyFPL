package nta.com.music.myfpl.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.model.Service;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    Context c;
    List<Service> list;

    public ServiceAdapter(Context c, List<Service> list) {
        this.c = c;
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private ServiceAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(ServiceAdapter.OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service, parent, false);
        return new ServiceAdapter.ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Service item = list.get(position);
        if (item.getId() < 10) {
            holder.txt_id_service.setText("0" + item.getId());
        } else {
            holder.txt_id_service.setText(item.getId() + "");
        }

        holder.txt_title_service.setText(item.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position);
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
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_id_service, txt_title_service;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id_service = itemView.findViewById(R.id.txt_id_service);
            txt_title_service = itemView.findViewById(R.id.txt_title_service);
        }
    }
}
