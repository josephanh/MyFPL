package nta.com.music.myfpl.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import nta.com.music.myfpl.R;

public class TextRegular extends androidx.appcompat.widget.AppCompatTextView {
    public TextRegular(@NonNull Context context) {
        super(context);
        init();
    }
    public TextRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("ResourceAsColor")
    private void init() {
        Typeface customFont = ResourcesCompat.getFont(getContext(), R.font.lexend_light);
        setTypeface(customFont);
        setTextSize(16);
    }
}
