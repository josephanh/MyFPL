package nta.com.music.myfpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.adapter.InformationAdapter;
import nta.com.music.myfpl.adapter.ServiceAdapter;
import nta.com.music.myfpl.interfaces.OnClickInformation;
import nta.com.music.myfpl.model.Information;
import nta.com.music.myfpl.model.Service;

public class ServiceActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        recyclerView = findViewById(R.id.rcv_trade_history);
        List<Service> list = new ArrayList<Service>();
        list.add(new Service(7,200000,"3","MK 20000","Spring 2023",""));
        list.add(new Service(7,200000,"3","MK 20000","Spring 2023",""));
        list.add(new Service(7,200000,"3","MK 20000","Spring 2023",""));
        list.add(new Service(7,200000,"3","MK 20000","Spring 2023",""));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ServiceActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new ServiceAdapter(ServiceActivity.this,list));
    }
}