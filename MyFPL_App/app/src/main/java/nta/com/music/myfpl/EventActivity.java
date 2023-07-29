package nta.com.music.myfpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import nta.com.music.myfpl.adapter.EventAdapter;
import nta.com.music.myfpl.adapter.EventTrendingApdapter;
import nta.com.music.myfpl.model.Event;
import nta.com.music.myfpl.model.EventTrending;

public class EventActivity extends AppCompatActivity {
    ThreadPoolExecutor executor;

    private RecyclerView rcvEvent , rcvEventTrending;
    private EventAdapter eventAdapter;
    private EventTrendingApdapter eventTrendingApdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        rcvEvent = findViewById(R.id.recyclerView_event);
        eventAdapter = new EventAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL, false);
        rcvEvent.setLayoutManager(linearLayoutManager);
        eventAdapter.setData(getListUser());
        rcvEvent.setAdapter(eventAdapter);

        rcvEventTrending = findViewById(R.id.recyclerView_event_trending);
        eventTrendingApdapter = new EventTrendingApdapter(this);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        rcvEventTrending.setLayoutManager(linearLayoutManager1);

        eventTrendingApdapter.setDataEvent(getEventTrending());
        rcvEventTrending.setAdapter(eventTrendingApdapter);

    }



    private  List<Event> getListUser() {
        List<Event> list = new ArrayList<>();
        list.add(new Event(R.drawable.image_event, "Event 1"));
        list.add(new Event(R.drawable.image_event_1, "Event 2"));
        list.add(new Event(R.drawable.image_event_2, "Event 3"));

        list.add(new Event(R.drawable.image_event, "Event 1"));
        list.add(new Event(R.drawable.image_event_1, "Event 2"));
        list.add(new Event(R.drawable.image_event_2, "Event 3"));
        return list;
    }


        private List<EventTrending> getEventTrending() {
        List<EventTrending> trendings =new ArrayList<>();
        trendings.add(new EventTrending("Title 1", R.drawable.image_action_event, "Đây là lễ hội truyền thống 1"));
        trendings.add(new EventTrending("Title 2", R.drawable.image_action_event, "Đây là lễ hội truyền thống 2"));
        trendings.add(new EventTrending("Title 3", R.drawable.image_action_event, "Đây là lễ hội truyền thống 3"));
        trendings.add(new EventTrending("Title 4", R.drawable.image_action_event, "Đây là lễ hội truyền thống 4"));
        trendings.add(new EventTrending("Title 5", R.drawable.image_action_event, "Đây là lễ hội truyền thống 5"));
        trendings.add(new EventTrending("Title 6", R.drawable.image_action_event, "Đây là lễ hội truyền thống 6"));

        return  trendings;
    }

}