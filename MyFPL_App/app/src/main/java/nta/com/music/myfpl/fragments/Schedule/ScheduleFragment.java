package nta.com.music.myfpl.fragments.Schedule;

import static nta.com.music.myfpl.adapter.ViewPagerSchedule.CALENDAR_WEEK;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ScheduleAdapter;
import nta.com.music.myfpl.interfaces.OnClickSchedule;
import nta.com.music.myfpl.model.Schedule;

public class ScheduleFragment extends Fragment {

    LinearLayout layout_id;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
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

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        layout_id = view.findViewById(R.id.layout_id);



        RecyclerView recyclerSchedule = view.findViewById(R.id.listSchedule);
        List<Schedule> list = new ArrayList<Schedule>();
        list.add(new Schedule(2,"Khởi sự doanh nghiệp","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",1));
        list.add(new Schedule(1,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSchedule.setLayoutManager(linearLayoutManager);

        ScheduleAdapter adapter = new ScheduleAdapter(getContext(), list, CALENDAR_WEEK, new OnClickSchedule() {
            @Override
            public void onClick(Schedule schedule) {

            }
        });
        recyclerSchedule.setAdapter(adapter);

        return view;
    }

}