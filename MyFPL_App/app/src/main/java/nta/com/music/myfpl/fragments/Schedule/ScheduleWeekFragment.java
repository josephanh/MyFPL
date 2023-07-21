package nta.com.music.myfpl.fragments.Schedule;

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
import nta.com.music.myfpl.adapter.InformationsAdapter;
import nta.com.music.myfpl.model.Informations;
import render.animations.Bounce;
import render.animations.Render;

public class ScheduleWeekFragment extends Fragment {

    LinearLayout layout_id;
    public ScheduleWeekFragment() {
        // Required empty public constructor
    }


    public static ScheduleWeekFragment newInstance() {
        ScheduleWeekFragment fragment = new ScheduleWeekFragment();
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
        View view = inflater.inflate(R.layout.fragment_schedule_week, container, false);

        layout_id = view.findViewById(R.id.layout_id);



        RecyclerView recyclerSchedule = view.findViewById(R.id.listSchedule);

        List<Informations> list = new ArrayList<Informations>();
        list.add(new Informations(1,"Phòng đào tạo","Nguyễn Đức Tuân",
                "Thông báo đăng ký thực hiện dự án tốt nghiệp học kỳ Fall 2023",7));
        list.add(new Informations(2,"Phòng đào tạo","Nguyễn Đức Tuân",
                "Phòng khảo thí thông báo DSSV kiểm tra Tiếng Anh đầu vào khóa 19.3.1_Ngày thi 22/07/2023",7));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerSchedule.setLayoutManager(linearLayoutManager);
        recyclerSchedule.setAdapter(new InformationsAdapter(getContext(),list));

        return view;
    }

}