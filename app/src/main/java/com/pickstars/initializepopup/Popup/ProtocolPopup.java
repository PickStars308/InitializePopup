package com.pickstars.initializepopup.Popup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pickstars.initializepopup.R;
import com.pickstars.initializepopup.SharedPreferencesHelper;
import com.pickstars.initializepopup.Utils.ClickableTextHelper;
import com.pickstars.initializepopup.WelcomeActivity;

import java.util.ArrayList;
import java.util.List;

public class ProtocolPopup extends Dialog {

    private final Activity activity;
    public ProtocolPopup(@NonNull Context context) {
        super(context);
        init();
        activity = (Activity) context;
    }

    private void init() {
        // 设置布局
        setContentView(R.layout.protocol_popup);

        // 配置窗口参数
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.BOTTOM; // 设置为底部显示
            params.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度全屏
            params.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度自适应
            window.setAttributes(params);

            // 移除默认背景边距
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setWindowAnimations(R.style.BottomDialogAnimation); // 设置动画样式
        }

        // 设置背景透明
        setCancelable(true); // 点击外部可关闭
        setCanceledOnTouchOutside(true);

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
            activity.startActivity(intent);

            activity.finish();
        });
        findViewById(R.id.CloseButton).setOnClickListener(v -> {
            dismiss();
            activity.finish();
        });

    }
}
