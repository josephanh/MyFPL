package nta.com.music.myfpl;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.adapter.ServiceAdapter;
import nta.com.music.myfpl.model.Service;

public class ServiceActivity extends AppCompatActivity {

    ImageButton btn_backService;
    RecyclerView rc_service;
    List<Service> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        btn_backService = findViewById(R.id.btn_backService);
        rc_service = findViewById(R.id.rc_service);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rc_service.setLayoutManager(linearLayoutManager);
        list.add(new Service(1, "Đăng ký cấp bảng điểm"));
        list.add(new Service(2, "Đăng ký cấp lại thẻ"));
        list.add(new Service(3, "Đăng ký học lại miễn phí v2"));
        list.add(new Service(4, "Đăng ký khôi phục điểm danh"));
        list.add(new Service(5, "Đăng ký tốt nghiệp sớm"));
        list.add(new Service(6, "Đăng ký miễn giảm học phần"));

        ServiceAdapter adapter = new ServiceAdapter(this, list);
        rc_service.setAdapter(adapter);
        adapter.setOnItemClickListener(new ServiceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Service item = list.get(position);
                ToastText("Nhan " + item.getTitle());
            }
        });
        btn_backService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void ToastText(String text){
        Toast.makeText(ServiceActivity.this, text, Toast.LENGTH_LONG).show();
    }
}