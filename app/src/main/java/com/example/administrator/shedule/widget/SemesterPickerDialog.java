package com.example.administrator.shedule.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.*;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import com.example.administrator.shedule.common.MyApplication;
import com.example.administrator.shedule.course.PickerDataManager;
import com.example.administrator.shedule.R;
import com.itheima.wheelpicker.WheelPicker;
import com.orhanobut.logger.Logger;

public class SemesterPickerDialog extends Dialog implements WheelPicker.OnItemSelectedListener {

    private Context mContext;
    private OnButtonClickListener mListener;

    private String mSelectedYear;
    private String mSelectedSemester;

    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.year_picker)
    WheelPicker yearPicker;
    @BindView(R.id.semester_picker)
    WheelPicker semesterPicker;
    private String account;

    public SemesterPickerDialog(@NonNull Context context, String account) {
        super(context);
        this.mContext = context;
        this.account = account;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_semester_picker);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);

        WindowManager windowManager = ((Activity)mContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = (int) (display.getWidth() * 0.8);
        lp.height = (int) (display.getHeight() * 0.5);
        getWindow().setAttributes(lp);
    }

    public void init() {
        PickerDataManager manager = new PickerDataManager(account);

        yearPicker.setData(manager.getYears());
        yearPicker.setOnItemSelectedListener(this);
        semesterPicker.setData(manager.getSemesters());
        semesterPicker.setOnItemSelectedListener(this);

        // 设置初始值
        mSelectedYear = manager.getYears().get(0);
        mSelectedSemester = manager.getSemesters().get(0);
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onItemSelected(WheelPicker wheelPicker, Object o, int i) {
        switch (wheelPicker.getId()) {
            case R.id.year_picker:
                Logger.d("year_picker i = " + i + ";  " + o.toString());
                mSelectedYear = o.toString();
                break;
            case R.id.semester_picker:
                Logger.d("semester_picker i = " + i + ";  " + o.toString());
                mSelectedSemester = o.toString();
                break;
        }
    }

    @OnClick(R.id.confirm)
    public void doConfirm() {
        mListener.onClickPositiveButton(mSelectedYear, mSelectedSemester);
        dismiss();
    }

    @OnClick(R.id.cancel)
    public void doCancel() {
        dismiss();
    }

    public interface OnButtonClickListener {
        void onClickPositiveButton(String year, String semester);
    }
}
