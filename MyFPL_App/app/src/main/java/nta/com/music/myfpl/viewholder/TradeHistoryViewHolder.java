package nta.com.music.myfpl.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import nta.com.music.myfpl.R;

public class TradeHistoryViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_time_trade_history,tv_sotien,tv_loai,tv_mahoadon,tv_hocky,tv_chuthich;
    public TradeHistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_time_trade_history = itemView.findViewById(R.id.tv_time_trade_history);
        tv_sotien = itemView.findViewById(R.id.tv_sotien);
        tv_loai = itemView.findViewById(R.id.tv_loai);
        tv_mahoadon = itemView.findViewById(R.id.tv_mahoadon);
        tv_hocky = itemView.findViewById(R.id.tv_hocky);
        tv_chuthich = itemView.findViewById(R.id.tv_chuthich);
    }
}
