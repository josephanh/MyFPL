package nta.com.music.myfpl.fragments.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.InformationsAdapter;
import nta.com.music.myfpl.model.Informations;

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
        List<Informations> list = new ArrayList<Informations>();
        list.add(new Informations(1,"Phòng đào tạo","Nguyễn Đức Tuân",
                "Thông báo đăng ký thực hiện dự án tốt nghiệp học kỳ Fall 2023",7));
        list.add(new Informations(2,"Phòng đào tạo","Nguyễn Đức Tuân",
                "Phòng khảo thí thông báo DSSV kiểm tra Tiếng Anh đầu vào khóa 19.3.1_Ngày thi 22/07/2023",7));
        list.add(new Informations(3,"Phòng đào tạo","Nguyễn Đức Tuân",
                "Thông báo nhắc nhở DSSV học Giáo dục quốc phòng - An ning đợt học tháng 12/2022",7));
        list.add(new Informations(4,"Phòng đào tạo","Nguyễn Đức Tuân",
                "Thông báo nhắc nhở DSSV học Giáo dục quốc phòng - An ning đợt học tháng 12/2022",7));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new InformationsAdapter(getContext(),list));

        return view;


    }
}