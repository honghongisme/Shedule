package com.example.administrator.shedule.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.administrator.shedule.MainActivity;
import com.example.administrator.shedule.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private static final int LOGIN_SUCCESS = 0;
    private static final int LOGIN_INPUT_ERROR = 1;
    private static final int LOGIN_NOT_KNOWN_ERROR = 2;

    @BindView(R.id.account_et)
    EditText mAccountEt;
    @BindView(R.id.password_et)
    EditText mPasswordEt;
    @BindView(R.id.login_btn)
    Button mLoginBtn;

    private LoginPresenterImpl mPresenter;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LOGIN_SUCCESS:
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    break;
                case LOGIN_INPUT_ERROR:
                    Toast.makeText(getApplicationContext(), "账号或密码错误，请重新输入！", Toast.LENGTH_SHORT).show();
                    break;
                case LOGIN_NOT_KNOWN_ERROR:
                    Toast.makeText(getApplicationContext(), "服务器错误，请稍后再试！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenterImpl(this);
    }

    @OnClick(R.id.login_btn)
    public void login() {
        String account = mAccountEt.getText().toString();
        String password = mPasswordEt.getText().toString();
        if (account.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            mPresenter.login(account, password);
        }
    }

    @Override
    public void onLoginSuccess() {
        mHandler.sendMessage(mHandler.obtainMessage(LOGIN_SUCCESS));
    }

    @Override
    public void onLoginAccountOrPasswordError() {
        mHandler.sendMessage(mHandler.obtainMessage(LOGIN_INPUT_ERROR));
    }

    @Override
    public void onLoginNotKnownError() {
        mHandler.sendMessage(mHandler.obtainMessage(LOGIN_NOT_KNOWN_ERROR));
    }
}
