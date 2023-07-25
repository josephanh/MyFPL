package nta.com.music.myfpl.fragments.Schedule;

import static nta.com.music.myfpl.adapter.ViewPagerSchedule.CALENDAR_MONTH;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ScheduleAdapter;
import nta.com.music.myfpl.interfaces.OnClickSchedule;
import nta.com.music.myfpl.interfaces.OnRecyclerScrollListener;
import nta.com.music.myfpl.model.Schedule;

public class ScheduleClassTabMonthFragment extends Fragment implements OnRecyclerScrollListener {

    RecyclerView recycleView_Schedule;
    HashMap<String, Integer> datePosition = new HashMap<>();
    protected static LinearLayoutManager linearLayoutMonthSchedule;
    protected static List<Schedule> listScheduleMonth = new ArrayList<Schedule>();

    boolean checkDatePos = false;

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    public ScheduleClassTabMonthFragment() {
        // Required empty public constructor
    }

    final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String[] temp = msg.obj.toString().split(" ");
            int monthTemp = convertMonth(temp[1]);
            String dateTemp = temp[3] + "-" + monthTemp + "-" + temp[2];

            if (!checkDatePos) {
                for (int i = 0; i < listScheduleMonth.size(); i++) {
                    String dateSchedule = listScheduleMonth.get(i).getDate();

                    datePosition.putIfAbsent(dateSchedule, i);
                }
                checkDatePos = true;
            }
            String pos = String.valueOf(datePosition.get(dateTemp));
            if (isNumber(pos)) {
                linearLayoutMonthSchedule.scrollToPositionWithOffset(Integer.parseInt(pos), 0);
            }
        }
    };


    public static ScheduleClassTabMonthFragment newInstance() {
        ScheduleClassTabMonthFragment fragment = new ScheduleClassTabMonthFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listScheduleMonth.add(new Schedule(1,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2, "2023-7-20"));
            listScheduleMonth.add(new Schedule(2,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",3, "2023-7-20"));
            listScheduleMonth.add(new Schedule(3,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2, "2023-7-21"));
            listScheduleMonth.add(new Schedule(4,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",3, "2023-7-21"));
            listScheduleMonth.add(new Schedule(5,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2, "2023-7-22"));
            listScheduleMonth.add(new Schedule(6,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",3, "2023-7-22"));
            listScheduleMonth.add(new Schedule(6,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2, "2023-7-23"));
            listScheduleMonth.add(new Schedule(6,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",3, "2023-7-23"));
            listScheduleMonth.add(new Schedule(6,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2, "2023-7-25"));
            listScheduleMonth.add(new Schedule(6,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",3, "2023-7-25"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_tab_month, container, false);
        Utils(view);
        linearLayoutMonthSchedule = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        ScheduleAdapter adapter = new ScheduleAdapter(requireContext(), listScheduleMonth, CALENDAR_MONTH, new OnClickSchedule() {
            @Override
            public void onClick(Schedule schedule) {

            }
        });
        recycleView_Schedule.setLayoutManager(linearLayoutMonthSchedule);
        recycleView_Schedule.setAdapter(adapter);


        return view;
    }

    private void Utils(View view) {
        recycleView_Schedule = view.findViewById(R.id.recycleView_Schedule);
    }

    private int convertMonth(String month){
        switch (month){
            case "Jan": return 1;
            case "Feb": return 2;
            case "Mar": return 3;
            case "Apr": return 4;
            case "May": return 5;
            case "Jun": return 6;
            case "Jul": return 7;
            case "Aug": return 8;
            case "Sep": return 9;
            case "Oct": return 10;
            case "Nov": return 11;
            case "Dec": return 12;
            default: return -1;
        }
    }

    public void scrollToPosition(int position) {
        // Cuộn RecyclerView trong Fragment con tới vị trí được chỉ định
        recycleView_Schedule.smoothScrollToPosition(position);
    }

    @Override
    public void onScroll(String date) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj = date;
                handler.sendMessage(message);
            }
        });
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
//                scrollToPosition(4);
            }
        });
    }
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}