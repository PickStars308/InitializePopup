package com.pickstars.initializepopup.Utils;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ClickableTextHelper {

    public static void setClickableText(TextView textView, String fullText, List<ClickableText> clickableTexts, @ColorInt int linkColor) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(fullText);

        // 遍历每个可点击文本项，并添加到 SpannableStringBuilder 中
        for (ClickableText clickableText : clickableTexts) {
            int start = fullText.indexOf(clickableText.getText());
            if (start != -1) {
                spannableStringBuilder.setSpan(new CustomClickableSpan(clickableText.getListener(), linkColor), start,
                        start + clickableText.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
        textView.setHighlightColor(ContextCompat.getColor(textView.getContext(), android.R.color.transparent));
    }

    public static class ClickableText {
        private final String text;
        private final View.OnClickListener listener;

        public ClickableText(String text, View.OnClickListener listener) {
            this.text = text;
            this.listener = listener;
        }

        public String getText() {
            return text;
        }

        public View.OnClickListener getListener() {
            return listener;
        }
    }

    // 自定义 ClickableSpan 实现，支持点击事件和高亮颜色
    private static class CustomClickableSpan extends ClickableSpan {
        private final View.OnClickListener mListener;
        private final int mLinkColor;

        CustomClickableSpan(View.OnClickListener listener, int linkColor) {
            mListener = listener;
            mLinkColor = linkColor;
        }

        @Override
        public void onClick(@NonNull View widget) {
            if (mListener != null) mListener.onClick(widget);
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(mLinkColor); // 设置高亮颜色
            ds.setUnderlineText(false); // 取消下划线
        }
    }
}

