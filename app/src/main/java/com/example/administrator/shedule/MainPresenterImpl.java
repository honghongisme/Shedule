package com.example.administrator.shedule;

import android.content.Context;

import com.example.administrator.shedule.common.MyApplication;
import com.example.administrator.shedule.course.OnCourseDataReqListener;
import com.example.administrator.shedule.bean.Course;
import com.example.administrator.shedule.course.data.ParseCourseDataManager;
import com.example.administrator.shedule.course.data.UrlParseManager;
import com.example.administrator.shedule.course.local.PersistenceStore;
import com.example.administrator.shedule.course.network.CourseDataSourceManager;
import com.example.administrator.shedule.login.local.LoginLocalModel;
import com.example.administrator.shedule.bean.User;

import java.util.List;

public class MainPresenterImpl implements MainContract.Presenter {

    private MainContract.View mView;
    private PersistenceStore mStore;
    private CourseDataSourceManager mCourseDataSourceManager;


    public MainPresenterImpl(MainContract.View view) {
        this.mView = view;
        mStore = new PersistenceStore();
        mCourseDataSourceManager = new CourseDataSourceManager();
    }

    @Override
    public void getData(final String fileName, final User user) {
        // 本地查找
        final List<Course> courses = mStore.loadCourse(fileName, (Context) mView);
        if (courses == null) { // 请求网络数据
            mCourseDataSourceManager.doRequest(user.getCookies(), UrlParseManager.parseToUrl(fileName), new OnCourseDataReqListener() {
                @Override
                public void onSuccess(String html) {
                    // html转化为list
                    List<Course> data = ParseCourseDataManager.parseDataHtml(html);
                    // 保存到本地
                    mStore.saveCourse(fileName, data, (Context) mView);
                    // 更新applicayion的user
                    user.setShowingSemester(fileName);
                    user.setShowingWeek(1);
                    ((MyApplication)((MainActivity)mView).getApplication()).setUser(user);
                    new LoginLocalModel().saveUser((Context) mView, user);
                    // 通知view刷新视图
                    mView.refreshGridLayout(data);
                }

                @Override
                public void onFailed() {
                    mView.onLoadDataFailed();
                }
            });
        } else {
            mView.refreshGridLayout(courses);
        }
    }

    @Override
    public void getData(String year, String semester, User user) {
        getData(UrlParseManager.parseQueryStr(year, semester), user);
    }
}
