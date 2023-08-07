package nta.com.music.myfpl;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.adapter.HistoryServiceAdapter;
import nta.com.music.myfpl.model.HistoryService;

public class WalletActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        recyclerView = findViewById(R.id.rcv_trade_history);
        btn_back = findViewById(R.id.btn_back);

        List<HistoryService> list = new ArrayList<HistoryService>();
        list.add(new HistoryService(7,200000,"3","MK 20000","Spring 2023",""));
        list.add(new HistoryService(7,200000,"3","MK 20000","Spring 2023",""));
        list.add(new HistoryService(7,200000,"3","MK 20000","Spring 2023",""));
        list.add(new HistoryService(7,200000,"3","MK 20000","Spring 2023",""));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WalletActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new HistoryServiceAdapter(WalletActivity.this,list));

        btn_back.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}