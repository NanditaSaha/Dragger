package app.ceva.petapp.share.wigeds;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import app.ceva.petapp.utils.CommonUtils;

public class TextViewMedium extends AppCompatTextView {

    public TextViewMedium(Context context) {
        super(context);
       setTypeface(CommonUtils.getTypefaceMedium(getContext()));
    }

    public TextViewMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(CommonUtils.getTypefaceMedium(getContext()));
    }

    public TextViewMedium(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(CommonUtils.getTypefaceMedium(getContext()));
    }
}
