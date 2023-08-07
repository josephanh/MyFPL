package nta.com.music.myfpl.fragments.Schedule;


import static nta.com.music.myfpl.LoginActivity.student;
import static nta.com.music.myfpl.adapter.CalendarHorizontalAdapter.selectedItem;
import static nta.com.music.myfpl.fragments.Schedule.ScheduleWeekFragment.convertDateFormat;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nta.com.music.myfpl.DTO.ScheduleResponseDTO;
import nta.com.music.myfpl.MainActivity;
import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.CalendarHorizontalAdapter;
import nta.com.music.myfpl.adapter.ViewPagerSchedule;
import nta.com.music.myfpl.helper.IRetrofit;
import nta.com.music.myfpl.helper.RetrofitHelper;
import nta.com.music.myfpl.interfaces.OnChangeSchedule;
import nta.com.music.myfpl.interfaces.OnClickCalendar;
import nta.com.music.myfpl.interfaces.OnRecyclerScrollListener;
import nta.com.music.myfpl.model.Schedule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScheduleMonthFragment extends Fragment implements OnRecyclerScrollListener, OnChangeSchedule {

    private int userSetTimes;
    public static final int THIS_WEEK = 0;
    public static final int NEXT_WEEK = 1;
    public static final int LAST_WEEK = 2;

    public static final int THIS_MONTH = 3;
    public static final int NEXT_MONTH = 4;
    public static final int LAST_MONTH = 5;

    List<String> listCalendar = new ArrayList<>();
    CalendarHorizontalAdapter adapter;
    LinearLayoutManager layoutManager;
    TextView tv_month;


    public static ScheduleMonthFragment newInstance(int userSetTimes) {
        Bundle args = new Bundle();
        args.putInt("userSetTimes", userSetTimes);
        ScheduleMonthFragment fragment = new ScheduleMonthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userSetTimes = getArguments().getInt("userSetTimes");
        }
    }

    ViewPager2 viewPager;
    TabLayout tabSchedule;
    RecyclerView calendarHorizontal;
    private ScheduleClassTabMonthFragment classTab;
    private ScheduleExamTabMonthFragment examTab;


    List<Schedule> scheduleList = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    ImageView btn_filter;
    IRetrofit retrofit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_month, container, false);

        Utils(view);
        classTab = new ScheduleClassTabMonthFragment();
        examTab = new ScheduleExamTabMonthFragment();
        fragmentList.add(classTab);
        fragmentList.add(examTab);

        userSetTimes = THIS_MONTH;
        setCalendar();
        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireContext()).showNavigationChoiceSchedule();
            }
        });

        retrofit = RetrofitHelper.createService(IRetrofit.class);


        return view;
    }

    private void Utils(View view) {
        viewPager = view.findViewById(R.id.viewpager);
        tabSchedule = view.findViewById(R.id.tab_schedule);
        calendarHorizontal = view.findViewById(R.id.calendar_horizontal);
        tv_month = view.findViewById(R.id.tv_month);
        btn_filter = view.findViewById(R.id.btn_filter);


        ViewPagerSchedule adapterSchedule = new ViewPagerSchedule(requireActivity(), fragmentList);
        viewPager.setAdapter(adapterSchedule);
        setTabSchedule();
        adapter = new CalendarHorizontalAdapter(requireContext(), listCalendar, new OnClickCalendar() {
            @Override
            public void onClick(String date) {
//              sự kiện khi click vào calendar
                onScroll(date);
            }
        });

    }

    private void setTabSchedule() {
        new TabLayoutMediator(tabSchedule, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Class schedule");
                        break;
                    case 1:
                        tab.setText("Exam schedule");
                        break;
                }
            }
        }).attach();

    }

    @SuppressLint("NotifyDataSetChanged")
    private void setCalendar() {
        listCalendar.clear();
//      cài đặt lịch dựa theo tùy chọn người dùng
        Date currentDate = new Date();
        boolean flag = false;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
//      0 là tuần hiện tại, -7 là tuần trước, 7 là tuần sau

        switch (userSetTimes) {
            case THIS_WEEK: {
                calendar.add(Calendar.DAY_OF_MONTH, 0);
                setWeek(calendar);
                flag = true;
                break;
            }
            case NEXT_WEEK: {
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                setWeek(calendar);
                flag = true;
                break;
            }
            case LAST_WEEK: {
                calendar.add(Calendar.DAY_OF_MONTH, -7);
                setWeek(calendar);
                flag = true;
                break;
            }
        }
//      nếu tuần đã được chọn thì tháng sẽ không xử lý nữa
        if (!flag) {
            switch (userSetTimes) {
                case THIS_MONTH: {
                    calendar = Calendar.getInstance();
                    calendar.add(Calendar.MONTH, 0);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    int currentMonth = calendar.get(Calendar.MONTH);
                    listCalendar.clear();
                    while (calendar.get(Calendar.MONTH) == currentMonth) {
                        Date date = calendar.getTime();
                        String[] arr = date.toString().split(" ");
                        listCalendar.add(arr[0] + " " + arr[1] + " " + arr[2]+ " "+ arr[5]);
                        adapter.notifyDataSetChanged();
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    break;
                }
                case LAST_MONTH: {
                    calendar = Calendar.getInstance();
                    calendar.add(Calendar.MONTH, -1);
                    int previousMonth = calendar.get(Calendar.MONTH);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    listCalendar.clear();
                    while (calendar.get(Calendar.MONTH) == previousMonth) {
                        Date date = calendar.getTime();
                        String[] arr = date.toString().split(" ");
                        listCalendar.add(arr[0] + " " + arr[1] + " " + arr[2]+ " "+ arr[5]);
                        adapter.notifyDataSetChanged();
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    break;
                }
                case NEXT_MONTH: {
                    calendar = Calendar.getInstance();
                    calendar.add(Calendar.MONTH, 1);
                    int nextMonth = calendar.get(Calendar.MONTH);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    listCalendar.clear();
                    while (calendar.get(Calendar.MONTH) == nextMonth) {
                        Date date = calendar.getTime();
                        String[] arr = date.toString().split(" ");
                        listCalendar.add(arr[0] + " " + arr[1] + " " + arr[2]+ " "+ arr[5]);
                        adapter.notifyDataSetChanged();
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    break;
                }
            }
        }
    }

//  hàm cài đặt lịch cho tuần
    @SuppressLint("NotifyDataSetChanged")
    private void setWeek(Calendar calendar) {
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
            adapter.notifyDataSetChanged();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

//  hàm cài đặt lịch và bắt sự kiện onScroll;
    private void setCalendarHorizontal() {
        layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        calendarHorizontal.setLayoutManager(layoutManager);
        calendarHorizontal.setAdapter(adapter);

        // Sử dụng SnapHelper để giữ cho mỗi lần lướt RecyclerView cuộn đến mục gần nhất

//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(calendarHorizontal);

        calendarHorizontal.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                handleScroll();
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });
    }


    private void handleScroll() {
        View middleView = findMiddleView();
        if (middleView != null) {
            int pos = calendarHorizontal.getChildAdapterPosition(middleView);
            if(pos >= 0) {
                selectItem(pos);
                onScroll(listCalendar.get(pos));
            }
        }
    }

    private View findMiddleView() {
        LinearLayoutManager manager = (LinearLayoutManager) calendarHorizontal.getLayoutManager();
        assert manager != null;
        int firstVisibleItem = manager.findFirstVisibleItemPosition();
        int lastVisibleItem = manager.findLastVisibleItemPosition();
        int middleItemPosition = (firstVisibleItem + lastVisibleItem) / 2;
        return calendarHorizontal.getChildAt(middleItemPosition - firstVisibleItem);
    }

    private void selectItem(int position) {
        adapter.setSelectedItem(position);

    }
    @Override
    public void onScroll(String date) {
       try {
           classTab.onScroll(date);
           examTab.onScroll(date);
       } catch (Exception e) {
//           Log.d(">>>>TAG", "onScroll: "+e.getMessage());
       }
    }

    @Override
    public void onResume() {
        super.onResume();
        try{
            setDefaultCalendar();
        }catch (Exception e){
//            Log.d(">>>>TAG", "onResume Month: "+e.getMessage());
        }
    }

    @Override
    public void onChange(int state, String subject, int time) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                switch (time){
                    case THIS_WEEK:{
                        userSetTimes = THIS_WEEK;
                        break;
                    }
                    case NEXT_WEEK:{
                        userSetTimes = NEXT_WEEK;
                        break;
                    }
                    case LAST_WEEK:{
                        userSetTimes = LAST_WEEK;
                        break;
                    }
                    case THIS_MONTH:{
                        userSetTimes = THIS_MONTH;
                        break;
                    }
                    case LAST_MONTH:{
                        userSetTimes = LAST_MONTH;
                        break;
                    }
                    case NEXT_MONTH:{
                        userSetTimes = NEXT_MONTH;
                        break;
                    }
                    default:{
                        userSetTimes = THIS_WEEK;
                    }
                }
                setCalendar();
                setCalendarHorizontal();
                setDefaultCalendar();
                if(student != null && student.getId() >= 0){
                    Log.d(">>>>TAG", "onCreateView: "+convertDateFormat(listCalendar.get(0)) + "date_end: "+convertDateFormat(listCalendar.get(listCalendar.size()-1)));
                    retrofit.getScheduleByTime(student.getId(), convertDateFormat(listCalendar.get(0)), convertDateFormat(listCalendar.get(listCalendar.size()-1))).enqueue(getScheduleWeek);
                }
            }
        });
    }

    public void setDefaultCalendar(){
        Calendar calendar = Calendar.getInstance();
        Toast.makeText(requireContext(), "Hello", Toast.LENGTH_SHORT).show();
        String[] timeNow = calendar.getTime().toString().split(" ");
        int indexOf = listCalendar.indexOf(timeNow[0] + " " + timeNow[1] + " "+timeNow[2]+ " "+ timeNow[5]);
        if(indexOf > 0) selectedItem = indexOf;

        if(indexOf < 4) {
            layoutManager.scrollToPositionWithOffset(indexOf, 0);
        } else if(indexOf > 4) {
            layoutManager.scrollToPositionWithOffset(indexOf-2, 0);
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
                    List<Schedule> classList = new ArrayList<>();
                    List<Schedule> examList = new ArrayList<>();
                    for(int i = 0; i < scheduleList.size(); i++){
                        if(Integer.parseInt(scheduleList.get(i).getType()) == 0){
                            classList.add(scheduleList.get(i));
                        }else{
                            examList.add(scheduleList.get(i));
                        }
                    }
                    ((ScheduleClassTabMonthFragment) fragmentList.get(0)).onChangeSchedule(classList);
                    ((ScheduleExamTabMonthFragment) fragmentList.get(1)).onChangeSchedule(examList);
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
