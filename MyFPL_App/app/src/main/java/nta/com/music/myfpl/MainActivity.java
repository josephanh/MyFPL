package nta.com.music.myfpl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChipNavigationBar menu = findViewById(R.id.menu);
        menu.setItemSelected(R.id.bt_home, true);
    }
}