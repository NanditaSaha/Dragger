package app.ceva.petapp.share.wigeds;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import app.ceva.petapp.utils.CommonUtils;

public class TextViewSemibold extends AppCompatTextView {

    public TextViewSemibold(Context context) {
        super(context);
       setTypeface(CommonUtils.getTypefaceSemiBold(getContext()));
    }

    public TextViewSemibold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(CommonUtils.getTypefaceSemiBold(getContext()));
    }

    public TextViewSemibold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(CommonUtils.getTypefaceSemiBold(getContext()));
    }
}
