package nta.com.music.myfpl.fragments.Schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.InformationAdapter;
import nta.com.music.myfpl.adapter.ScheduleAdapter;
import nta.com.music.myfpl.model.Information;
import nta.com.music.myfpl.model.Schedule;

public class ScheduleMonthFragment extends Fragment {
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_month,container,false);
        recyclerView = view.findViewById(R.id.rcv_schedule);
        List<Schedule> list = new ArrayList<Schedule>();
        list.add(new Schedule(2,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2));
        list.add(new Schedule(1,"Android Networking","T308","Chấn Nguyễn","Phần mềm Quang Trung","MOB403","MD17306",2));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new ScheduleAdapter(getContext(),list));
        return view;
    }
}
