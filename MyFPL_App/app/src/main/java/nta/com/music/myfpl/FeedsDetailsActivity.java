package nta.com.music.myfpl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class FeedsDetailsActivity extends AppCompatActivity {

    LinearLayout viewToHideShow;
    ScrollView scrollView;
    boolean isViewToHideShowVisible = false;
    int thresholdScroll = 100;

    ImageView btn_back_feed_detail,img_feed_detail;
    TextView txt_time_feed_detail, txt_title_feed_detail, txt_content_feed_detail, txt_title2_feed_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds_details);

        viewToHideShow = findViewById(R.id.viewToHideShow);
        img_feed_detail = findViewById(R.id.img_feed_detail);
        scrollView = findViewById(R.id.scrollView);

        btn_back_feed_detail = findViewById(R.id.btn_back_feed_detail);
        txt_time_feed_detail = findViewById(R.id.txt_time_feed_detail);
        txt_title_feed_detail = findViewById(R.id.txt_title_feed_detail);
        txt_content_feed_detail = findViewById(R.id.txt_content_feed_detail);
        txt_title2_feed_detail = findViewById(R.id.txt_title2_feed_detail);


        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                // Get the bottom position of the ImageView relative to the scrollView
                int imageViewBottom = img_feed_detail.getBottom() - scrollView.getScrollY();

                // Check if the ImageView is no longer visible on the screen
                if (imageViewBottom <= thresholdScroll && !isViewToHideShowVisible) {
                    viewToHideShow.setVisibility(View.VISIBLE);
                    isViewToHideShowVisible = true;
                } else if (imageViewBottom > thresholdScroll && isViewToHideShowVisible) {
                    viewToHideShow.setVisibility(View.GONE);
                    isViewToHideShowVisible = false;
                }
            }
        });
    }
}