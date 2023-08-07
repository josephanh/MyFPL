package nta.com.music.myfpl.fragments.Schedule;

import static nta.com.music.myfpl.LoginActivity.student;
import static nta.com.music.myfpl.fragments.Schedule.ScheduleClassTabFragment.viewPager_schedule;
import static nta.com.music.myfpl.fragments.Schedule.ScheduleExamTabFragment.viewPager_schedule_exam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.muratozturk.click_shrink_effect.ClickShrinkEffect;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import nta.com.music.myfpl.DTO.ScheduleResponseDTO;
import nta.com.music.myfpl.MainActivity;
import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ViewPagerSchedule;
import nta.com.music.myfpl.component.TextRegular;
import nta.com.music.myfpl.helper.IRetrofit;
import nta.com.music.myfpl.helper.RetrofitHelper;
import nta.com.music.myfpl.model.Schedule;
import q.rorbin.verticaltablayout.TabAdapter;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleWeekFragment extends Fragment {

    ViewPager2 viewPager;
    View tabViewChild;
    public static VerticalTabLayout tabsWeek;
    public static int currentItem = 0;
    TabLayout tabSchedule;
    TextView day, today_text;
    TextRegular notification_schedule;
    ImageView btn_filter, btn_refest;

    ThreadPoolExecutor executor;
    List<String> listCalendar = new ArrayList<>();

    List<Schedule> scheduleList = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();

    IRetrofit retrofit;


    //    private int currentIndex = 0;
    Handler handler = new Handler(Looper.getMainLooper());

    public ScheduleWeekFragment() {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

    public static ScheduleWeekFragment newInstance() {
        ScheduleWeekFragment fragment = new ScheduleWeekFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    List<String> titleTab = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule_week, container, false);

        Utils(view);
        setListCalendarDefault();
//        Log.d(">>>>TAG", "setListCalendarDefault: "+convertDateFormat(listCalendar.get(0)));
//        Log.d(">>>>TAG", "setListCalendarDefault: "+convertDateFormat(listCalendar.get(listCalendar.size()-1)));
        retrofit = RetrofitHelper.createService(IRetrofit.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        tabsWeek = view.findViewById(R.id.tab_layout);
                        setTabsWeek();
                        setBackgroundItemNavigation(0);

                    }
                });
            }
        }).start();


        setTabSchedule();
        setItemTabsWeekSelected();


        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireContext()).showNavigationChoiceSchedule();
            }
        });

        btn_refest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }


    @SuppressLint("SetTextI18n")
    private void Utils(View view) {
        viewPager = view.findViewById(R.id.viewpager);
        tabsWeek = view.findViewById(R.id.tab_layout);
        tabSchedule = view.findViewById(R.id.tab_schedule);
        btn_filter = view.findViewById(R.id.btn_filter);
        btn_refest = view.findViewById(R.id.btn_refest);
        today_text = view.findViewById(R.id.today_text);
        notification_schedule = view.findViewById(R.id.notification_schedule);


        Calendar calendar = Calendar.getInstance();
        String[] dateToday = calendar.getTime().toString().split(" ");

        today_text.setText(dateToday[0]+ ", "+ dateToday[2] + " " + dateToday[1] + " "+dateToday[5]);


        new ClickShrinkEffect(btn_refest, 0.7f, 100L);

        fragmentList.add(new ScheduleClassTabFragment());
        fragmentList.add(new ScheduleExamTabFragment());
        ViewPagerSchedule adapter = new ViewPagerSchedule(requireActivity(), fragmentList);
        viewPager.setAdapter(adapter);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setBackgroundItemNavigation(int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ViewGroup vg = (ViewGroup) tabsWeek.getChildAt(0);
                int tabsCount = vg.getChildCount();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (int j = 0; j < tabsCount; j++) {
                            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
                            int tabChildsCount = vgTab.getChildCount();
                            for (int i = 0; i < tabChildsCount; i++) {
                                tabViewChild = vgTab.getChildAt(i);
                                // Get TextView Element
                                if (tabViewChild instanceof TextView) {
                                    // change color
                                    if (j == position) {
                                        ((TextView) tabViewChild).setTextColor(getResources().getColor(R.color.white));
                                        ((TextView) tabViewChild).setBackground(getResources().getDrawable(R.drawable.item_day_vertical_slt));
                                    } else {
                                        ((TextView) tabViewChild).setTextColor(getResources().getColor(R.color.black));
                                        ((TextView) tabViewChild).setBackground(getResources().getDrawable(R.drawable.item_day_vertical));
                                    }
                                }
                            }
                        }
                    }
                });

            }
        }).start();

    }

    private void setTabsWeek() {

        tabsWeek.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return 7;
            }

            @SuppressLint("InflateParams")
            @Override
            public View getTabItemView(int position) {
                View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_tab_vertical, null);
                day = itemView.findViewById(R.id.day_name);
                switch (position) {
                    case 0: {
                        day.setText("Mo");
                        break;
                    }
                    case 1: {
                        day.setText("Tu");
                        break;
                    }
                    case 2: {
                        day.setText("We");
                        break;
                    }
                    case 3: {
                        day.setText("Th");
                        break;
                    }
                    case 4: {
                        day.setText("Fr");
                        break;
                    }
                    case 5: {
                        day.setText("Sa");
                        break;
                    }
                    case 6: {
                        day.setText("Su");
                        break;
                    }
                }

                return itemView;
            }
        });
    }

    private void setItemTabsWeekSelected() {
        tabsWeek.setOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
            @Override
            public void onTabSelected(View tab, int position) {
                currentItem = viewPager.getCurrentItem();
                if (currentItem == 0) {
                    viewPager_schedule.setCurrentItem(position);
                    if (viewPager_schedule_exam != null) {
                        viewPager_schedule_exam.setCurrentItem(position, false);
                    } else {
                        currentItem = position;
                    }

                } else {
                    viewPager_schedule_exam.setCurrentItem(position);
                    viewPager_schedule.setCurrentItem(position, false);
                }
                setBackgroundItemNavigation(position);

            }

            @Override
            public void onTabReselected(View tab, int position) {

            }
        });
    }

    private void setTabSchedule() {
        new TabLayoutMediator(tabSchedule, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText(R.string.class_schedule);
                        break;
                    case 1:
                        tab.setText(R.string.exam_schedule);
                        break;
                }
            }
        }).attach();

    }

    @Override
    public void onResume() {
        super.onResume();
        setListCalendarDefault();
        if(student != null && student.getId() >= 0){
            retrofit.getScheduleByTime(student.getId(), convertDateFormat(listCalendar.get(0)), convertDateFormat(listCalendar.get(listCalendar.size()-1))).enqueue(getScheduleWeek);
        }
    }

    private void setListCalendarDefault(){
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 0);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int diff = calendar.getFirstDayOfWeek() - (dayOfWeek-1);
        if (diff > 0) {
            diff -= 7;
        }

        calendar.add(Calendar.DAY_OF_MONTH, diff);
        listCalendar.clear();
        for (int i = 0; i < 7; i++) {
            Date date = calendar.getTime();
            String[] arr = date.toString().split(" ");
            listCalendar.add(arr[0] + " " + arr[1] + " " + arr[2] + " "+ arr[5]);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertDateFormat(String inputDate) {
        String outputFormat = "yyyy-MM-dd";
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
            SimpleDateFormat outputFormatter = new SimpleDateFormat(outputFormat);

            // Chuyển chuỗi thành đối tượng Date
            Date date = inputFormatter.parse(inputDate);

            // Chuyển đổi tháng từ dạng chữ (Jul) sang số (7)
            SimpleDateFormat monthFormatter = new SimpleDateFormat("MM");
            assert date != null;
            String monthNumber = monthFormatter.format(date);

            String outputDate = outputFormatter.format(date);

            return outputDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    Callback<ScheduleResponseDTO> getScheduleWeek = new Callback<ScheduleResponseDTO>() {
        @Override
        public void onResponse(@NonNull Call<ScheduleResponseDTO> call, Response<ScheduleResponseDTO> response) {
            if (response.isSuccessful()) {
                ScheduleResponseDTO loginResponse = response.body();
                assert loginResponse != null;
                if (loginResponse.isStatus()) {
                    scheduleList = loginResponse.getSchedule();
                    ((ScheduleClassTabFragment) fragmentList.get(0)).onChangeSchedule(scheduleList);
                    ((ScheduleExamTabFragment) fragmentList.get(1)).onChangeSchedule(scheduleList);
                }
            }
            
        }

        @Override
        public void onFailure(@NonNull Call<ScheduleResponseDTO> call, Throwable t) {
            Log.d(">>> CALL API", "onFailure: " + t.getMessage());
            Log.d(">>> CALL API", "onFailure: " + student.getId());
            Log.d(">>> CALL API", "onFailure: " + convertDateFormat(listCalendar.get(0)));
            Log.d(">>> CALL API", "onFailure: " + convertDateFormat(listCalendar.get(listCalendar.size()-1)));
        }
    };



}