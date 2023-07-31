package nta.com.music.myfpl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import nta.com.music.myfpl.adapter.EventAdapter;
import nta.com.music.myfpl.adapter.EventTrendingApdapter;
import nta.com.music.myfpl.adapter.SlideImage_Adapter;
import nta.com.music.myfpl.model.Event;
import nta.com.music.myfpl.model.EventTrending;

public class EventActivity extends AppCompatActivity {
    ThreadPoolExecutor executor;

    private RecyclerView rcvEvent , rcvEventTrending;
    private EventAdapter eventAdapter;
    private EventTrendingApdapter eventTrendingApdapter;

    private ViewPager2 viewPager2;

    // ảnh slider auto;
    public Handler sildeHandler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

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


        viewPager2 = findViewById(R.id.viewPager_Slide);

        List<SlideItem> slideItems = new ArrayList<>();

        slideItems.add(new SlideItem(R.drawable.bg_event_action));
        slideItems.add(new SlideItem(R.drawable.bg_event_action));
        slideItems.add(new SlideItem(R.drawable.bg_event_action));
        slideItems.add(new SlideItem(R.drawable.bg_event_action));

        viewPager2.setAdapter(new SlideImage_Adapter(slideItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();

        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 -Math.abs((position));
                page.setScaleY(0.85f + r*0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sildeHandler.removeCallbacks(sliderRunnable);
                sildeHandler.postDelayed(sliderRunnable, 3000);

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        sildeHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sildeHandler.postDelayed(sliderRunnable, 3000);
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() +1);
        }
    };



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