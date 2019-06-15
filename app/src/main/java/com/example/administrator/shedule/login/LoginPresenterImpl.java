package com.example.administrator.shedule.login;

import android.content.Context;
import com.example.administrator.shedule.common.MyApplication;
import com.example.administrator.shedule.login.local.LoginLocalModel;
import com.example.administrator.shedule.bean.User;
import com.example.administrator.shedule.login.network.LoginNetManager;
import com.example.administrator.shedule.login.network.OnLoginReqListener;

public class LoginPresenterImpl implements LoginContract.Presenter {

    private LoginNetManager mManager;
    private LoginLocalModel mModel;
    private LoginContract.View mView;

    public LoginPresenterImpl(LoginContract.View view) {
        this.mView = view;
        mManager = new LoginNetManager();
        mModel = new LoginLocalModel();
    }

    @Override
    public void login(String account, String password) {
        mManager.setLoginReqListener(new OnLoginReqListener() {
            @Override
            public void onSuccess(User user) {
                // 保存到本地
                mModel.saveUser((Context) mView, user);
                mView.onLoginSuccess();
            }

            @Override
            public void onAccountOrPasswordError() {
                mView.onLoginAccountOrPasswordError();
            }

            @Override
            public void onNotKnownFailed() {
                mView.onLoginNotKnownError();
            }
        });
        mManager.requestLogin(account, password);
    }
}
