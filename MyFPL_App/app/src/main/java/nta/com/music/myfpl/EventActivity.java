package nta.com.music.myfpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.adapter.EventAdapter;
import nta.com.music.myfpl.model.Event;

public class EventActivity extends AppCompatActivity {

    private RecyclerView rcvEvent;
    private EventAdapter eventAdapter;

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
}