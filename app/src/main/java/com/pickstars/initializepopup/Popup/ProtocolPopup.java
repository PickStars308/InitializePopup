package com.pickstars.initializepopup.Popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.core.BottomPopupView;
import com.pickstars.initializepopup.R;
import com.pickstars.initializepopup.SharedPreferencesHelper;
import com.pickstars.initializepopup.Utils.ClickableTextHelper;
import com.pickstars.initializepopup.WelcomeActivity;

import java.util.ArrayList;
import java.util.List;

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
    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate() {
        super.onCreate();
        CheckBox CheckBox = findViewById(R.id.CheckBox);

        LinearLayout ReadTheAgreementLayout = findViewById(R.id.ReadTheAgreementLayout);

        ReadTheAgreementLayout.setOnClickListener(v->{
            CheckBox.setChecked(!CheckBox.isChecked());
        });

        TextView ReadTheAgreement = findViewById(R.id.ReadTheAgreement);

        // 创建可点击文本列表
        List<ClickableTextHelper.ClickableText> clickableTexts = new ArrayList<>();
        clickableTexts.add(new ClickableTextHelper.ClickableText(getContext().getString(R.string.UserAgreement), v -> {
            Toast.makeText(getContext(), "你点击了： 用户协议", Toast.LENGTH_SHORT).show();
        }));
        clickableTexts.add(new ClickableTextHelper.ClickableText(getContext().getString(R.string.PrivacyPolicy), v -> {
            Toast.makeText(getContext(), "你点击了： 隐私政策", Toast.LENGTH_SHORT).show();
        }));

        // 设置可点击文本
        ClickableTextHelper.setClickableText(ReadTheAgreement, getContext().getString(R.string.dialog_message), clickableTexts, ContextCompat.getColor(getContext(), android.R.color.system_on_primary_light));


        findViewById(R.id.AgreeButton).setOnClickListener(v -> {
            if (!CheckBox.isChecked()) {
                Toast.makeText(getContext(), "请先阅读并同意协议", Toast.LENGTH_SHORT).show();
                return;
            }

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