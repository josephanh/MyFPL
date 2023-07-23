package nta.com.music.myfpl.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import nta.com.music.myfpl.R;

public class TextBold extends androidx.appcompat.widget.AppCompatTextView {
    public TextBold(@NonNull Context context) {
        super(context);
        init();
    }
    public TextBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("ResourceAsColor")
    private void init() {
        Typeface customFont = ResourcesCompat.getFont(getContext(), R.font.lexend);
        setTypeface(customFont);

//        int color = ContextCompat.getColor(getContext(), R.color.black);
//        setTextColor(color);
        setTextSize(16);
    }
}
