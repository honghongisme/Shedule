package com.example.administrator.shedule.login.network;

import com.example.administrator.shedule.bean.User;

public interface OnLoginReqListener {

    void onSuccess(User user);
    void onAccountOrPasswordError();
    void onNotKnownFailed();
}
