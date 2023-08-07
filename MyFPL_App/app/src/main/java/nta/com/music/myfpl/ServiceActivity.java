package nta.com.music.myfpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.adapter.NotificationSchoolAdapter;
import nta.com.music.myfpl.adapter.ServiceAdapter;
import nta.com.music.myfpl.model.NotificationSchool;
import nta.com.music.myfpl.model.Service;

public class ServiceActivity extends AppCompatActivity {

    ImageButton btn_backService;
    RecyclerView rc_service;
    List<Service> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        btn_backService = findViewById(R.id.btn_backService);
        rc_service = findViewById(R.id.rc_service);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rc_service.setLayoutManager(linearLayoutManager);
        list.add(new Service(01, "Đăng ký cấp bảng điểm"));
        list.add(new Service(02, "Đăng ký cấp lại thẻ"));
        list.add(new Service(03, "Đăng ký học lại miễn phí v2"));
        list.add(new Service(04, "Đăng ký khôi phục điểm danh"));
        list.add(new Service(05, "Đăng ký tốt nghiệp sớm"));
        list.add(new Service(06, "Đăng ký miễn giảm học phần"));

        ServiceAdapter adapter = new ServiceAdapter(this, list);
        rc_service.setAdapter(adapter);
        adapter.setOnItemClickListener(new ServiceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Service item = list.get(position);
                Toasttext("Nhan " + item.getTitle());
            }
        });
    }

    public void Toasttext(String text){
        Toast.makeText(ServiceActivity.this, text, Toast.LENGTH_LONG).show();
    }
}