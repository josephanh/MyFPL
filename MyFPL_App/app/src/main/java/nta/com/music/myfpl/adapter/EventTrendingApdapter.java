package nta.com.music.myfpl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.model.EventTrending;

public class EventTrendingApdapter extends  RecyclerView.Adapter<EventTrendingApdapter.EventTrendingViewHolder> {

    private Context context;
    private List<EventTrending> eventTrendings;

    public EventTrendingApdapter(Context context) {
        this.context = context;
    }

    public void setDataEvent(List<EventTrending> listEventTrending) {
        this.eventTrendings = listEventTrending;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public EventTrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event_trending, null);

        return new EventTrendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventTrendingViewHolder holder, int position) {

        EventTrending trending = eventTrendings.get(position);

        if(trending == null) {
            return;
        }
        holder.tv_view_title.setText(trending.getTitle());
        holder.image_event.setImageResource(trending.getImageId());
        holder.tv_view_description.setText(trending.getDescription());


    }

    @Override
    public int getItemCount() {
        if(eventTrendings != null) {
            return eventTrendings.size();
        }
        return 0;
    }

    public class EventTrendingViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_view_title;
        private ImageView image_event;
        private TextView tv_view_description;


        public EventTrendingViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_view_title = itemView.findViewById(R.id.tv_title_view);
            image_event = itemView.findViewById(R.id.image_view_event);
            tv_view_description =itemView.findViewById(R.id.tv_description_view);
        }
    }
}
