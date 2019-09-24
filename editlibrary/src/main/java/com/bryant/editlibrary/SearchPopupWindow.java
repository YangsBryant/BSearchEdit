package com.bryant.editlibrary;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.bryant.editlibrary.entity.MultipleItemEntity;
import com.bryant.editlibrary.entity.TitleEntity;
import java.util.ArrayList;
import java.util.List;

class SearchPopupWindow extends PopupWindow {

    private Activity activity;
    private TitleAdapter adapter;
    private List<MultipleItemEntity> beautyData;
    private int popup_width;
    private int textWidth,textHeight;
    private int textSize;
    private int textColor;
    private int line_bg;
    private int line_height;
    private int line_width;
    private int popup_bg;
    private TextClickListener textClickListener;

    SearchPopupWindow(Activity activity,int popup_width){
        this.activity = activity;
        this.popup_width = popup_width;
        beautyData = new ArrayList<>();
    }

    private void initView(){
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.list_popupp, null);
        setContentView(view);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(getPixelsFromDp(popup_width));
        view.setBackgroundResource(popup_bg);
        this.setFocusable(false);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popup_style);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        adapter = new TitleAdapter(beautyData,activity);
        adapter.setTextSize(textSize);
        adapter.setTextColor(textColor);
        adapter.setTextHeight(textHeight);
        adapter.setTextWidth(textWidth);
        adapter.setLine_bg(line_bg);
        adapter.setLine_height(line_height);
        adapter.setLine_width(line_width);
        recyclerView.setAdapter(adapter);
        adapter.setTextClickListener(new TitleAdapter.TextClickListener() {
            @Override
            public void onTextClick(int position, String text) {
                if(textClickListener!=null){
                    textClickListener.onTextClick(position,text);
                }
            }
        });
    }

    public void setList(List<String> list) {
        beautyData.clear();
        for(int i=0;i<list.size();i++){
            MultipleItemEntity multipleItemEntity = new MultipleItemEntity(MultipleItemEntity.mItem);
            TitleEntity entity = new TitleEntity();
            entity.setTitle(list.get(i));
            multipleItemEntity.setTitlEntity(entity);
            beautyData.add(multipleItemEntity);
        }
        adapter.notifyDataSetChanged();
    }

    //参数设置完毕，一定要build一下
    public void build(){
        initView();
    }

    public void setTextWidth(int textWidth) {
        this.textWidth = textWidth;
    }

    public void setTextHeight(int textHeight) {
        this.textHeight = textHeight;
    }

    public SearchPopupWindow setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setLine_bg(int line_bg) {
        this.line_bg = line_bg;
    }

    public void setLine_height(int line_height) {
        this.line_height = line_height;
    }

    public void setLine_width(int line_width) {
        this.line_width = line_width;
    }

    public void setPopup_bg(int popup_bg) {
        this.popup_bg = popup_bg;
    }

    //点击监听器
    public interface TextClickListener {
        void onTextClick(int position,String text);
    }

    public void setTextClickListener(TextClickListener textClickListener) {
        this.textClickListener = textClickListener;
    }

    //px转dp
    private int getPixelsFromDp(int size){
        DisplayMetrics metrics =new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;
    }
}
