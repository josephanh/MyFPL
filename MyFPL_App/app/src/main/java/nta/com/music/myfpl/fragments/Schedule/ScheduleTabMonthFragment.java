package nta.com.music.myfpl.fragments.Schedule;

import static nta.com.music.myfpl.adapter.ViewPagerSchedule.CALENDAR_MONTH;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ScheduleAdapter;
import nta.com.music.myfpl.interfaces.OnClickSchedule;
import nta.com.music.myfpl.model.Schedule;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduleTabMonthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleTabMonthFragment extends Fragment {

    RecyclerView recycleView_Schedule;
    public ScheduleTabMonthFragment() {
        // Required empty public constructor
    }


    public static ScheduleTabMonthFragment newInstance() {
        ScheduleTabMonthFragment fragment = new ScheduleTabMonthFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_tab_month, container, false);
        Utils(view);

        List<Schedule> list = new ArrayList<Schedule>();
        list.add(new Schedule(1,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2, "2023-7-20"));
        list.add(new Schedule(2,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",3, "2023-7-20"));
        list.add(new Schedule(3,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2, "2023-7-21"));
        list.add(new Schedule(4,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",3, "2023-7-21"));
        list.add(new Schedule(5,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2, "2023-7-22"));
        list.add(new Schedule(6,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",3, "2023-7-22"));
        list.add(new Schedule(6,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2, "2023-7-23"));
        list.add(new Schedule(6,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",3, "2023-7-23"));
        list.add(new Schedule(6,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2, "2023-7-25"));
        list.add(new Schedule(6,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",3, "2023-7-25"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        ScheduleAdapter adapter = new ScheduleAdapter(requireContext(), list, CALENDAR_MONTH, new OnClickSchedule() {
            @Override
            public void onClick(Schedule schedule) {

            }
        });
        recycleView_Schedule.setLayoutManager(linearLayoutManager);
        recycleView_Schedule.setAdapter(adapter);
        return view;
    }

    private void Utils(View view) {
        recycleView_Schedule = view.findViewById(R.id.recycleView_Schedule);
    }
}