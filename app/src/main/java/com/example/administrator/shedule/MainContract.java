package com.example.administrator.shedule;

import com.example.administrator.shedule.common.BasePresenter;
import com.example.administrator.shedule.common.BaseView;
import com.example.administrator.shedule.bean.Course;
import com.example.administrator.shedule.bean.User;

import java.util.List;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void refreshGridLayout(List<Course> courses);
        void onLoadDataFailed();
    }

    interface Presenter extends BasePresenter {
        void getData(String fileName, User user);
        void getData(String year, String semester, User user);
    }
}
