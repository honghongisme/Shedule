package com.example.administrator.shedule.login.network;


import com.example.administrator.shedule.bean.User;
import com.orhanobut.logger.Logger;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginNetManager {

    // 登录url
    private static final String LOGIN_URL = "http://authserver.tjut.edu.cn/authserver/login?service=http%3A%2F%2Fehall.tjut.edu.cn%2Flogin%3Fservice%3Dhttp%3A%2F%2Fehall.tjut.edu.cn%2Fnew%2Findex.html";
    // 师生服务url
    private static final String ST_SERVICE_URL = "http://ssfw.tjut.edu.cn/ssfw/j_spring_ids_security_check";

    private Map<String, String> mData;
    private Map<String, String> mCookies;
    private OnLoginReqListener mListener;

    public void setLoginReqListener(OnLoginReqListener listener) {
        this.mListener = listener;
    }

    public void requestLogin(final String account, final String password) {
        mData = new HashMap<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 第一次get请求
                    Connection.Response resp = Jsoup.connect(LOGIN_URL).execute();
                    Document doc = resp.parse();
                    Elements elements = doc.select("form#casLoginForm > input");
                    for (Element element : elements) {
                        mData.put(element.attr("name"), element.attr("value"));
                    }
                    mData.put("username", account);
                    mData.put("password", password);
                    mCookies = resp.cookies();

                    // 第二次post请求
                    resp = Jsoup.connect(LOGIN_URL).data(mData).cookies(mCookies).execute();
                    doc = resp.parse();
                    try {
                        doc.select("div#ampHasLogin > span").get(0).text();
                    }catch (Exception e) {
                        // 没抓取到节点则表示登录失败
                        mListener.onAccountOrPasswordError();
                        return ;
                    }
                    Logger.d(resp.cookies());
                    mCookies.putAll(resp.cookies());

                    // 请求师生服务页面
                    resp = Jsoup.connect(ST_SERVICE_URL).cookies(mCookies).ignoreContentType(true).execute();
                    mCookies.putAll(resp.cookies());
                    Logger.d(resp.body());
                    Logger.d(resp.cookies());

                    mListener.onSuccess(createUser());
                } catch (IOException e) {
                    e.printStackTrace();
                    mListener.onNotKnownFailed();
                }
            }
        }).start();
    }

    private User createUser() {
        return new User(mData.get("username"), mData.get("password"), (HashMap<String, String>) mCookies);
    }
}
