package app.ceva.petapp.feature.Calendar;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.FetchScheduling.FetchSchedulingResponse;
import app.ceva.petapp.share.base.BaseActivity;
import app.ceva.petapp.share.wigeds.TextViewRegular;
import butterknife.BindView;
import butterknife.ButterKnife;
import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnMonthChangeListener;
import sun.bob.mcalendarview.vo.DateData;

public class CalenderActivity extends BaseActivity implements CalendarMvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private Calendar calendar;
    Date today,tomorrow;
    DateFormat dateFormat;
    private DateData selectedDate;

    private ArrayList<String> nextdate=new ArrayList<String>();

    @BindView(R.id.calendarview)
    MCalendarView  calendarview;
    @BindView(R.id.tvCurrentYear)
    TextViewRegular tvCurrentYear;
    @BindView(R.id.tvCurrentMonth)
    TextViewRegular tvCurrentMonth;


   // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Inject
    CalendarPresenter<CalendarMvpView> presenter;
    private  String  ProductId,PetId,ProductUniqueId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setUp();
        setToolbar();
        getIntentData();




    }

    private void getIntentData()
    {
        ProductId=getIntent().getStringExtra("ProductId");
        PetId=getIntent().getStringExtra("PetId");
        ProductUniqueId=getIntent().getStringExtra("ProductUniqueId");

       // Toast.makeText(getApplicationContext(),ProductId+""+PetId,Toast.LENGTH_SHORT).show();

      presenter.getscheduling(PetId,ProductId,ProductUniqueId);
    }




    @TargetApi(Build.VERSION_CODES.P)
    private void setCalendar()
    {
        calendar = Calendar.getInstance();
        today=calendar.getTime();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate =  mdformat.format(calendar.getTime());


        int dayOfWeek = calendar.get(Calendar.DATE); // 6
        int dayOfMonth = calendar.get(Calendar.MONTH); // 17
        int dayOfYear = calendar.get(Calendar.YEAR);
      //  Toast.makeText(getApplication(),strDate+""+dayOfWeek+""+dayOfMonth+""+dayOfYear,Toast.LENGTH_SHORT).show();

        calendarview.setOnMonthChangeListener(new OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {

                tvCurrentYear.setText(year+"");

                switch (month)
                {
                    case 1:
                        tvCurrentMonth.setText("JANUARY");
                        break;
                    case 2:
                        tvCurrentMonth.setText("FEBRUARY");
                        break;

                    case 3:
                        tvCurrentMonth.setText("MARCH");
                        break;
                    case 4:
                        tvCurrentMonth.setText("APRIL");
                        break;
                    case 5:
                        tvCurrentMonth.setText("MAY");
                        break;
                    case 6:
                        tvCurrentMonth.setText("JUNE");
                        break;
                    case 7:
                        tvCurrentMonth.setText("JULY");
                        break;
                    case 8:
                        tvCurrentMonth.setText("AUGUST");
                        break;
                    case 9:
                        tvCurrentMonth.setText("SEPTEMBER");
                        break;
                    case 10:
                        tvCurrentMonth.setText("OCTOBER");
                        break;
                    case 11:
                        tvCurrentMonth.setText("NOVEMBER");
                        break;
                    case 12:
                        tvCurrentMonth.setText("DECEMBER");
                        break;

                }
               // Toast.makeText(getApplication(),year+"scroll"+month,Toast.LENGTH_SHORT).show();
            }
        });





    }


    protected void setUp()
    {
        presenter.onAttached(this);

    }

    private void setToolbar()
    {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here


                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void succeessfullyGetScheduling(FetchSchedulingResponse fetchSchedulingResponse) {

        setCalendar();
        calendarview.getMarkedDates().removeAdd();

        if(fetchSchedulingResponse.getResponseCode()==1) {

            for(int i=0;i<fetchSchedulingResponse.getResponseData().size();i++) {

                String date=fetchSchedulingResponse.getResponseData().get(i);
                String[] separated = date.split("-");

                Date current = new Date();
                Date date1= null;
                try {
                    date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);


                    if(date1.before(current)){
                        System.out.println("The date is older than current day");
                        calendarview.markDate(new DateData(Integer.parseInt(separated[0]), Integer.parseInt(separated[1]), Integer.parseInt(separated[2])).setMarkStyle(new MarkStyle(MarkStyle.BACKGROUND, getResources().getColor(R.color.colorRed))));
                    }else if(date1.equals(current))
                    {
                        calendarview.markDate(new DateData(Integer.parseInt(separated[0]), Integer.parseInt(separated[1]), Integer.parseInt(separated[2])).setMarkStyle(new MarkStyle(MarkStyle.BACKGROUND, getResources().getColor(R.color.colorGreen))));
                    }
                    else {
                        System.out.println("The date is future day");
                        calendarview.markDate(new DateData(Integer.parseInt(separated[0]), Integer.parseInt(separated[1]), Integer.parseInt(separated[2])).setMarkStyle(new MarkStyle(MarkStyle.BACKGROUND, getResources().getColor(R.color.colorGreen))));
                    }



                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
