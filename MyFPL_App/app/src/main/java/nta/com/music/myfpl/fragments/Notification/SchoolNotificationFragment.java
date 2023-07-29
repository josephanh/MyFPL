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
import nta.com.music.myfpl.adapter.NotificationSchoolAdapter;
import nta.com.music.myfpl.fragments.NotificationFragment;
import nta.com.music.myfpl.model.NotificationSchool;


public class SchoolNotificationFragment extends Fragment {

    RecyclerView rc_schoolNotification;
    public SchoolNotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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
        View view = inflater.inflate(R.layout.fragment_school_notification, container, false);
        rc_schoolNotification = view.findViewById(R.id.rc_schoolNotification);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_schoolNotification.setLayoutManager(linearLayoutManager);
        list.add(new NotificationSchool(1,"Thông báo đăng ký thực hiện dự án tốt nghiệp học kỳ Fall 2023", "Content nè", "29/07/2023"));
        list.add(new NotificationSchool(2,"Thông báo đăng ký thực hiện dự án tốt nghiệp học kỳ Fall 2023", "Content nè", "28/07/2023"));
        list.add(new NotificationSchool(3,"Thông báo đăng ký thực hiện dự án tốt nghiệp học kỳ Fall 2023", "Content nè", "29/06/2023"));
        list.add(new NotificationSchool(4,"Thông báo đăng ký thực hiện dự án tốt nghiệp học kỳ Fall 2023", "Content nè", "15/06/2023"));
        NotificationSchoolAdapter adapter = new NotificationSchoolAdapter(getContext(), list);

        rc_schoolNotification.setAdapter(adapter);
        adapter.setOnItemClickListener(new NotificationSchoolAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                NotificationSchool item = list.get(position);
                Toast.makeText(getContext(), "nhan o cho school"+item.getId(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}