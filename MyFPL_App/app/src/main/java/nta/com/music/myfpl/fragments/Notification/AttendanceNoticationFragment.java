package nta.com.music.myfpl.fragments.Notification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.NotificationAttendanceAdapter;
import nta.com.music.myfpl.model.NotificationSchool;

public class AttendanceNoticationFragment extends Fragment {

    RecyclerView rc_attendanceNotification;
    public AttendanceNoticationFragment() {
        // Required empty public constructor
    }

    public static AttendanceNoticationFragment newInstance(String param1, String param2) {
        AttendanceNoticationFragment fragment = new AttendanceNoticationFragment();
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

    List<NotificationSchool> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendance_notication, container, false);
        rc_attendanceNotification = view.findViewById(R.id.rc_attendanceNotification);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_attendanceNotification.setLayoutManager(linearLayoutManager);
        list.add(new NotificationSchool(1,"Bạn đã điểm danh thành công ngày 22/07", "Content nè", "29/07/2023"));
        list.add(new NotificationSchool(2,"Bạn đã điểm danh thành công ngày 22/07", "Content nè", "28/07/2023"));
        list.add(new NotificationSchool(3,"Bạn đã điểm danh thành công ngày 22/07", "Content nè", "29/06/2023"));
        list.add(new NotificationSchool(4,"Bạn đã điểm danh thành công ngày 22/07", "Content nè", "15/06/2023"));
        NotificationAttendanceAdapter adapter = new NotificationAttendanceAdapter(getContext(), list);

        rc_attendanceNotification.setAdapter(adapter);
        adapter.setOnItemClickListener(new NotificationAttendanceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                NotificationSchool item = list.get(position);
                Toast.makeText(getContext(), "nhan o cho attendance"+item.getId(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}