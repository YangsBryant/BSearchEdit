package com.bryant.editlibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.jakewharton.rxbinding2.widget.RxTextView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class BSearchEdit extends View {
    private Activity activity;
    private SearchPopupWindow searchPopupWindow;
    private EditText editText;
    private int widthPopup;
    private int textWidth = ViewGroup.LayoutParams.MATCH_PARENT,textHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int textSize=14;
    private int textColor;
    private int line_color;
    private int line_height=1;
    private int line_width=ViewGroup.LayoutParams.MATCH_PARENT;
    private int popup_bg;
    private TextClickListener textClickListener;

    public BSearchEdit(Activity activity, EditText editText,int widthPopup) {
        super(activity);
        this.activity = activity;
        this.editText = editText;
        this.widthPopup = widthPopup;
        textColor  = activity.getResources().getColor(R.color.gray);
        line_color  = activity.getResources().getColor(R.color.gray2);
        popup_bg = R.drawable.popup_bg;
    }

    @SuppressLint("CheckResult")
    private void init(){
        searchPopupWindow = new SearchPopupWindow(activity,widthPopup);
        searchPopupWindow.setTextSize(textSize);
        searchPopupWindow.setTextColor(textColor);
        searchPopupWindow.setTextHeight(textHeight);
        searchPopupWindow.setTextWidth(textWidth);
        searchPopupWindow.setLine_bg(line_color);
        searchPopupWindow.setLine_height(line_height);
        searchPopupWindow.setLine_width(line_width);
        searchPopupWindow.setPopup_bg(popup_bg);
        searchPopupWindow.build();
        searchPopupWindow.setTextClickListener(new SearchPopupWindow.TextClickListener() {
            @Override
            public void onTextClick(int position, String text) {
                if(textClickListener!=null){
                    textClickListener.onTextClick(position,text);
                    searchPopupWindow.dismiss();
                }
            }
        });


        RxTextView.textChanges(editText)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(CharSequence charSequence) {
                        return charSequence.toString();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        if(s.length()>0){
                            searchPopupWindow.showAsDropDown(editText);
                        }
                    }
                });
    }

    //参数设置完毕，一定要build一下
    public BSearchEdit build(){
        init();
        return this;
    }

    public BSearchEdit setTextWidth(int textWidth) {
        this.textWidth = textWidth;
        return this;
    }

    public BSearchEdit setTextHeight(int textHeight) {
        this.textHeight = textHeight;
        return this;
    }

    public BSearchEdit setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public BSearchEdit setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public BSearchEdit setLine_color(int line_color) {
        this.line_color = line_color;
        return this;
    }

    public BSearchEdit setLine_height(int line_height) {
        this.line_height = line_height;
        return this;
    }

    public BSearchEdit setLine_width(int line_width) {
        this.line_width = line_width;
        return this;
    }

    public BSearchEdit setPopup_bg(int popup_bg) {
        this.popup_bg = popup_bg;
        return this;
    }

    public void setSearchList(ArrayList<String> list) {
        searchPopupWindow.setList(list);
    }

    //点击监听器
    public interface TextClickListener {
        void onTextClick(int position,String text);
    }

    public void setTextClickListener(TextClickListener textClickListener) {
        this.textClickListener = textClickListener;
    }
}
