package nta.com.music.myfpl.fragments.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.MainActivity;
import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.InformationAdapter;
import nta.com.music.myfpl.interfaces.OnClickInformation;
import nta.com.music.myfpl.model.Information;

public class InformationFragment extends Fragment {
    RecyclerView recyclerView;

    public InformationFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        recyclerView = view.findViewById(R.id.rcv_information);
        List<Information> list = new ArrayList<Information>();
        list.add(new Information(1,"Phòng đào tạo","Nguyễn Đức Tuân",
                "Thông báo đăng ký thực hiện dự án tốt nghiệp học kỳ Fall 2023",7));
        list.add(new Information(2,"Phòng công tác sinh viên","Nguyễn Đức Tuân",
                "Phòng khảo thí thông báo DSSV kiểm tra Tiếng Anh đầu vào khóa 19.3.1_Ngày thi 22/07/2023",7));
        list.add(new Information(3,"Phòng quan hệ doanh nghiệp","Nguyễn Đức Tuân",
                "Thông báo nhắc nhở DSSV học Giáo dục quốc phòng - An ning đợt học tháng 12/2022",7));
        list.add(new Information(4,"Phòng đào tạo","Nguyễn Đức Tuân",
                "Thông báo nhắc nhở DSSV học Giáo dục quốc phòng - An ning đợt học tháng 12/2022",7));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new InformationAdapter(getContext(), list, new OnClickInformation() {
            @Override
            public void onClick(Information information) {
                ((MainActivity)requireContext()).goToDetailInformation(information);
            }
        }));
        return view;


    }
}