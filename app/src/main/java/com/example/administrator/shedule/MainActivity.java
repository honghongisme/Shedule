package com.example.administrator.shedule;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.administrator.shedule.common.MyApplication;
import com.example.administrator.shedule.course.GridConfig;
import com.example.administrator.shedule.bean.Course;
import com.example.administrator.shedule.login.LoginActivity;
import com.example.administrator.shedule.login.local.LoginLocalModel;
import com.example.administrator.shedule.bean.User;
import com.example.administrator.shedule.util.TextViewWidthUtil;
import com.example.administrator.shedule.widget.CourseDetailDialog;
import com.example.administrator.shedule.widget.SemesterPickerDialog;
import com.orhanobut.logger.Logger;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainContract.View {

    private static final int COURSE_TAG = 1;
    private static final int GET_DATA_SUCCESS = 2;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.row_header_gl)
    GridLayout mRowHeaderGl;
    @BindView(R.id.course_gl)
    GridLayout mCourseGl;
    @BindView(R.id.switch_week_spinner)
    Spinner mWeekSwitchSpinner;

    // 正在展示的周
    private int isShownWeek = 1;
    private String[] mRowHeaderData = {"周一", "周二", "周三", "周四", "周五", "周六", "周日",};
    private GridConfig mConfig;
    private MainPresenterImpl mPresenter;
    private List<Course> mCourseData;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET_DATA_SUCCESS:
                    drawCourse(msg.arg1, (List<Course>) msg.obj);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        init();

        mPresenter = new MainPresenterImpl(this);
        User user = ((MyApplication)getApplication()).getUser();
        if (user != null) { // 登录跳转过来的
            // 提示帮助
        } else {
            user = new LoginLocalModel().loadUser(this);
            if (user != null) {
                ((MyApplication)getApplication()).setUser(user);
                if (user.getShowingSemester() != null) { // 已导入学期
                    mPresenter.getData(user.getShowingSemester(), user);
                } else { // 未导入学期
                    // 提示导入学期
                }
            } else { // 用户不存在
                // 提示登录
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_course) {

            if (((MyApplication)getApplication()).getUser() == null) {
                startActivityForResult(new Intent(this, LoginActivity.class), 0);
                Toast.makeText(getApplicationContext(), "请先登录！", Toast.LENGTH_LONG).show();
            } else {
                showSemesterPickerDialog();
            }
        }
        return true;
    }

    public void init() {
        mConfig = new GridConfig(0.5f, 2, 2, 1, 5, 5, 4, 5);
        mWeekSwitchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                int weekNum = Integer.parseInt(s.substring(1, s.indexOf('周')));
                initColHeader();
                showCourse(weekNum, mCourseData);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        initRowHeader();
        initColHeader();
    }

    public void initRowHeader() {
        int i = 0;
        for (String s : mRowHeaderData) {
            TextView tv = new TextView(this);
            tv.setText(s);
            tv.setBackgroundColor(Color.RED);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(0), GridLayout.spec(i, 1.0f));
            params.setGravity(Gravity.FILL_HORIZONTAL & Gravity.CENTER);
            params.setMargins(5, 5, 5, 5);
            mRowHeaderGl.addView(tv, params);
            i++;
        }
    }

    public void initColHeader() {
        for (int i = 0; i < mCourseGl.getRowCount(); i++) {
            TextView tv = new TextView(this);
            GridLayout.LayoutParams params;
            tv.setText((i + 1) + "");
            tv.setBackgroundColor(Color.GREEN);
            params = new GridLayout.LayoutParams(GridLayout.spec(i, mConfig.getLeftHeaderWeight()), GridLayout.spec(0));
            params.setGravity(Gravity.FILL_VERTICAL);
            params.setMargins(mConfig.getLeftHeaderLeftMargin(), 2, mConfig.getLeftHeaderRightMargin(), 2);
            mCourseGl.addView(tv, params);
        }
    }

    public void drawCourse(int weekNum, List<Course> courses) {
        mCourseGl.removeAllViews();
        initColHeader();
        for (Course course : courses) {
            int[] starkWeek = course.getStartWeek();
            int[] endWeek = course.getEndWeek();
            boolean flag = false;
            for (int i = 0; i < starkWeek.length; i++) {
                if (weekNum >= starkWeek[i] && weekNum <= endWeek[i]) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                Logger.d(course);
                TextView tv = new TextView(this);
                GridLayout.LayoutParams params;
                tv.setText(course.getTitle() + "\n" + course.getLocation());
                tv.setMaxWidth(TextViewWidthUtil.getTextViewWidth(this, mConfig));
                tv.setPadding(mConfig.getSingleCourseHorizontalPadding(), mConfig.getSingleCourseVerticalPadding(), mConfig.getSingleCourseHorizontalPadding(), mConfig.getSingleCourseVerticalPadding());
                tv.setBackgroundColor(Color.BLUE);
                tv.setTextSize(12);
                tv.setOnClickListener(this);
                tv.setTag(course);
                params = new GridLayout.LayoutParams(GridLayout.spec(course.getStartNum() - 1, 2, mConfig.getSingleCourseWeight()), GridLayout.spec(course.getWeekDayNum() + 1));
                params.setGravity(Gravity.FILL_VERTICAL);
                params.setMargins(mConfig.getSingleCourseLeftMargin(), 2, mConfig.getSingleCourseRightMargin(), 2);
                mCourseGl.addView(tv, params);
            }
        }
    }

    /**
     *
     * @param weekNum 第几周
     * @param courses
     */
    public void showCourse(int weekNum, List<Course> courses) {
        if(courses == null || courses.size() == 0) return;
        mHandler.sendMessage(mHandler.obtainMessage(GET_DATA_SUCCESS, weekNum, 0, courses));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 打开学期选择dialog
        Toast.makeText(getApplicationContext(), "登录成功！", Toast.LENGTH_SHORT).show();
        showSemesterPickerDialog();
    }

    public void showSemesterPickerDialog() {
        SemesterPickerDialog dialog = new SemesterPickerDialog(MainActivity.this);
        dialog.show();
        dialog.init();
        dialog.setOnButtonClickListener(new SemesterPickerDialog.OnButtonClickListener() {
            @Override
            public void onClickPositiveButton(String year, String semester) {
                Logger.d(year + semester);
                mPresenter.getData(year, semester, ((MyApplication)getApplication()).getUser());
            }
        });
    }

    public void showDetailCourseDataDialog(Course course) {
        CourseDetailDialog dialog = new CourseDetailDialog(MainActivity.this);
        dialog.show();
        dialog.init(course);
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() instanceof Course) {
            Course course = (Course) v.getTag();
            if (course != null) {
                // show detail dialog
                showDetailCourseDataDialog(course);
            }
        }
    }

    @Override
    public void refreshGridLayout(List<Course> courses) {
        mCourseData = courses;
        isShownWeek = ((MyApplication)getApplication()).getUser().getShowingWeek();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            showCourse(isShownWeek, courses);
        } else {
            mHandler.sendMessage(mHandler.obtainMessage(GET_DATA_SUCCESS, isShownWeek, 0, courses));
        }

    }

    @Override
    public void onLoadDataFailed() {

    }

  /*  public void test() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                return 0;
            }
        });
    }*/
}
