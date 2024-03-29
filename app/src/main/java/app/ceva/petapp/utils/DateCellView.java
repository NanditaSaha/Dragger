package app.ceva.petapp.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import app.ceva.petapp.R;
import sun.bob.mcalendarview.views.BaseCellView;
import sun.bob.mcalendarview.vo.DayData;

public class DateCellView extends BaseCellView {

    public DateCellView(Context context) {
        super(context);
    }

    public DateCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setDisplayText(DayData day) {
        ((TextView) this.findViewById(R.id.id_cell_text)).setText(day.getText());
    }
}
