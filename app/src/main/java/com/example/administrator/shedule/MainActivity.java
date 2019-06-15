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
import com.example.administrator.shedule.util.TextViewSizeUtil;
import com.example.administrator.shedule.widget.CourseDetailDialog;
import com.example.administrator.shedule.widget.SemesterPickerDialog;
import com.orhanobut.logger.Logger;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainContract.View {

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
    private String[] mRowHeaderData = {"周一", "周二", "周三", "周四", "周五", "周六", "周日",};
    private GridConfig mConfig;
    private MainPresenterImpl mPresenter;
    private List<Course> mCourseData;
    // 通过屏幕尺寸和权重计算出来的课程格子宽高
    private int mWidth;
    private int mHeight;
    private User mUser;
    private LoginLocalModel mLoginLocalModel;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET_DATA_SUCCESS:
                    mUser.setShowingWeek(msg.arg1);
                    mLoginLocalModel.saveUser(MainActivity.this, mUser);
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
        mLoginLocalModel = new LoginLocalModel();
        mUser = mLoginLocalModel.loadUser(this);
        if (mUser != null) { // 已登录过
            if (mUser.getShowingSemester() != null) { // 已导入学期
                mPresenter.getData(mUser.getShowingSemester(), mUser);
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
            if (mUser == null) {
                startActivityForResult(new Intent(this, LoginActivity.class), 0);
                Toast.makeText(getApplicationContext(), "请先登录！", Toast.LENGTH_LONG).show();
            } else {
                showSemesterPickerDialog();
            }
        }
        return true;
    }

    public void init() {
        mConfig = new GridConfig()
                .setLeftHeaderLeftRightMargin(2)
                .setSingleCourseLeftRightMargin(5)
                .setSingleCourseTopBottomMargin(4)
                .setSingleCourseTopBottomPadding(13)
                .setSingleCourseLeftRightPadding(5);
        mWidth = TextViewSizeUtil.getTextViewWidth(this, mConfig);
        mHeight = TextViewSizeUtil.getTextViewHeight(this, mConfig);
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
            tv.setWidth(mWidth);
            tv.setGravity(Gravity.CENTER);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(0), GridLayout.spec(i));
            params.setGravity(Gravity.CENTER);
            params.setMargins(5, 5, 5, 5);
            mRowHeaderGl.addView(tv, params);
            i++;
        }
    }

    public void initColHeader() {
        for (int i = 0, j = 1; i < mCourseGl.getRowCount(); i++, j+=2) {
            View v = getLayoutInflater().inflate(R.layout.course_num, null);
            TextView top = v.findViewById(R.id.top_num_tv);
            top.setText(j + "");
            top.setHeight(mHeight/2);
            top.setWidth(mWidth/2);
            TextView bottom = v.findViewById(R.id.bottom_num_tv);
            bottom.setText((j + 1) + "");
            bottom.setHeight(mHeight/2);
            bottom.setWidth(mWidth/2);
            GridLayout.LayoutParams params;
            params = new GridLayout.LayoutParams(GridLayout.spec(i, 0.5f), GridLayout.spec(0));
            params.setGravity(Gravity.FILL_VERTICAL);
            params.setMargins(mConfig.getLeftHeaderLeftRightMargin(), 0, mConfig.getLeftHeaderLeftRightMargin(), 0);
            mCourseGl.addView(v, params);
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
                View v = getLayoutInflater().inflate(R.layout.course_grid, null);
                TextView tv = v.findViewById(R.id.content_tv);
                GridLayout.LayoutParams params;
                tv.setText(course.getTitle() + "\n" + course.getLocation());
                tv.setWidth(mWidth);
                tv.setHeight(mHeight);
                tv.setPadding(mConfig.getSingleCourseLeftRightPadding(), mConfig.getSingleCourseTopBottomPadding(), mConfig.getSingleCourseLeftRightPadding(), mConfig.getSingleCourseTopBottomPadding());
                tv.setTextSize(12);
                v.setBackgroundColor(Color.parseColor(course.getColor()));
                v.setOnClickListener(this);
                v.setTag(course);
                params = new GridLayout.LayoutParams(GridLayout.spec((course.getStartNum() + 1) / 2 - 1), GridLayout.spec(course.getWeekDayNum() + 1));
                params.setGravity(Gravity.FILL_VERTICAL);
                params.setMargins(mConfig.getSingleCourseLeftRightMargin(), mConfig.getSingleCourseTopBottomMargin(), mConfig.getSingleCourseLeftRightMargin(), mConfig.getSingleCourseTopBottomMargin());
                mCourseGl.addView(v, params);
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
        mUser = mLoginLocalModel.loadUser(this);
        showSemesterPickerDialog();
    }

    public void showSemesterPickerDialog() {
        SemesterPickerDialog dialog = new SemesterPickerDialog(MainActivity.this, mUser.getAccount());
        dialog.show();
        dialog.init();
        dialog.setOnButtonClickListener(new SemesterPickerDialog.OnButtonClickListener() {
            @Override
            public void onClickPositiveButton(String year, String semester) {
                Logger.d(year + semester);
                mPresenter.getData(year, semester, mUser);
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
    public void refreshGridLayout(List<Course> courses, User user) {
        mCourseData = courses;
        if (user != null) {
            mUser = user;
        }
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            mWeekSwitchSpinner.setSelection(mUser.getShowingWeek() - 1);
            showCourse(mUser.getShowingWeek(), courses);
        } else {
            mHandler.sendMessage(mHandler.obtainMessage(GET_DATA_SUCCESS, mUser.getShowingWeek(), 0, courses));
        }
    }

    @Override
    public void onLoadDataFailed() {

    }
}
