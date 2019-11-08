package app.ceva.petapp.share.wigeds;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import app.ceva.petapp.utils.CommonUtils;

public class TextViewBold extends AppCompatTextView {

    public TextViewBold(Context context) {
        super(context);
       setTypeface(CommonUtils.getTypefaceBold(getContext()));
    }

    public TextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(CommonUtils.getTypefaceBold(getContext()));
    }

    public TextViewBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(CommonUtils.getTypefaceBold(getContext()));
    }
}
