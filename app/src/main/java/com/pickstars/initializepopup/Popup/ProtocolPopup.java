package com.pickstars.initializepopup.Popup;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.core.BottomPopupView;
import com.pickstars.initializepopup.MainActivity;
import com.pickstars.initializepopup.R;
import com.pickstars.initializepopup.SharedPreferencesHelper;
import com.pickstars.initializepopup.WelcomeActivity;

public class ProtocolPopup extends BottomPopupView {
    //注意：自定义弹窗本质是一个自定义View，但是只需重写一个参数的构造，其他的不要重写，所有的自定义弹窗都是这样。
    public ProtocolPopup(@NonNull Context context) {
        super(context);
    }
    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.protocol_popup;
    }
    // 执行初始化操作，比如：findView，设置点击，或者任何你弹窗内的业务逻辑
    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.AgreeButton).setOnClickListener(v -> {
            Toast.makeText(getContext(), " AgreeButton Test ", Toast.LENGTH_SHORT).show();
            SharedPreferencesHelper.commitBoolean("FirstRun", true);
            dismiss();

            Intent intent = new Intent(getContext(), WelcomeActivity.class);
            getActivity().startActivity(intent);

            getActivity().finish();
        });
        findViewById(R.id.CloseButton).setOnClickListener(v -> {
            dismiss();
            getActivity().finish();
        });
    }
    // 设置最大宽度，看需要而定，
    @Override
    protected int getMaxWidth() {
        return super.getMaxWidth();
    }
    // 设置最大高度，看需要而定
    @Override
    protected int getMaxHeight() {
        return super.getMaxHeight();
    }
    // 设置自定义动画器，看需要而定
    @Override
    protected PopupAnimator getPopupAnimator() {
        return super.getPopupAnimator();
    }

}