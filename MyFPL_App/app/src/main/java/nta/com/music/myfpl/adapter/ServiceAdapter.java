package nta.com.music.myfpl.adapter;

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
import nta.com.music.myfpl.viewholder.TradeHistoryViewHolder;

public class ServiceAdapter extends RecyclerView.Adapter<TradeHistoryViewHolder> {
    Context context;
    List<Service> list;

    public ServiceAdapter(Context context, List<Service> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TradeHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_trade_history, parent, false);
        return new TradeHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TradeHistoryViewHolder holder, int position) {
        holder.tv_chuthich.setText("Chú thích :" + list.get(position).getChuthich());
        holder.tv_sotien.setText("Số tiền :" + list.get(position).getSotien() + "");
        holder.tv_hocky.setText("Học kỳ :" + list.get(position).getHocky());
        holder.tv_mahoadon.setText("Mã hóa đơn :" + list.get(position).getMahoadon());
        holder.tv_time_trade_history.setText("Thời gian :" + list.get(position).getTime() + "");

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

            @Override
            public void onBindViewHolder(@NonNull ServiceAdapter.ViewHolder holder, int position) {
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
                    return list.size();
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
        }
    }
}
