package com.example.administrator.shedule.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.administrator.shedule.R;
import com.example.administrator.shedule.bean.Course;


public class CourseDetailDialog extends Dialog {

    private Context mContext;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.teacher_tv)
    TextView teacher_tv;
    @BindView(R.id.num_tv)
    TextView num_tv;
    @BindView(R.id.week_tv)
    TextView week_tv;
    @BindView(R.id.place_tv)
    TextView place_tv;

    public CourseDetailDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_course_detail);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);//点击外部Dialog消失
    }

    public void init(Course course) {
        title_tv.setText(course.getTitle());
        teacher_tv.setText(course.getTeacher());
        num_tv.setText(course.getStartNum() + "-" + course.getEndNum() + "节");
        String weekStr = "";
        for (int i = 0; i < course.getStartWeek().length; i++) {
            int startWeek = course.getStartWeek()[i];
            int endWeek = course.getEndWeek()[i];
            weekStr += (startWeek + "-" + endWeek + "周 ");
        }
        week_tv.setText(weekStr);
        place_tv.setText(course.getLocation());
    }
}
