package nta.com.music.myfpl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.model.Event;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context context;
    private List<Event> listEvents;

    public EventAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Event> list){
        this.listEvents = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, null);

        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = listEvents.get(position);
        if (event == null){
            return;
        }
        holder.imageEvent.setImageResource(event.getResourceId());
        holder.tvName.setText(event.getName());
    }

    @Override
    public int getItemCount() {
        if(listEvents !=null){
            return listEvents.size();
        }
        return 0;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageEvent;
        private TextView tvName;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEvent = itemView.findViewById(R.id.img_event);
            tvName = itemView.findViewById(R.id.tv_name_event);

        }
    }
}
