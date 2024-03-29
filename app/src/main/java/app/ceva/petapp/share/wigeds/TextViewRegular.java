package app.ceva.petapp.share.wigeds;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import app.ceva.petapp.utils.CommonUtils;

public class TextViewRegular extends AppCompatTextView {

    public TextViewRegular(Context context) {
        super(context);
       setTypeface(CommonUtils.getTypefaceRegular(getContext()));
    }

    public TextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(CommonUtils.getTypefaceRegular(getContext()));
    }

    public TextViewRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(CommonUtils.getTypefaceRegular(getContext()));
    }
}
