package com.example.administrator.shedule.login;

import com.example.administrator.shedule.common.BasePresenter;
import com.example.administrator.shedule.common.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void onLoginSuccess();
        void onLoginAccountOrPasswordError();
        void onLoginNotKnownError();
    }

    interface Presenter extends BasePresenter {
        void login(String account, String password);
    }
}
