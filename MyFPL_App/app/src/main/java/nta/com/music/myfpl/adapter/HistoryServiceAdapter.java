package nta.com.music.myfpl.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.model.HistoryService;
import nta.com.music.myfpl.viewholder.TradeHistoryViewHolder;

public class HistoryServiceAdapter extends RecyclerView.Adapter<TradeHistoryViewHolder> {
    Context context;
    List<HistoryService> list;

    public HistoryServiceAdapter(Context context, List<HistoryService> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TradeHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_trade_history, parent, false);
        return new TradeHistoryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TradeHistoryViewHolder holder, int position) {
        holder.tv_chuthich.setText("Chú thích :" + list.get(position).getChuthich());
        holder.tv_sotien.setText("Số tiền :" + list.get(position).getSotien() + "");
        holder.tv_hocky.setText("Học kỳ :" + list.get(position).getHocky());
        holder.tv_mahoadon.setText("Mã hóa đơn :" + list.get(position).getMahoadon());
        holder.tv_time_trade_history.setText("Thời gian :" + list.get(position).getTime() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
