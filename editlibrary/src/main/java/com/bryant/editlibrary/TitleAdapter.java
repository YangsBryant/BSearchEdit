package com.bryant.editlibrary;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.bryant.editlibrary.entity.MultipleItemEntity;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

public class TitleAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, BaseViewHolder> {

    private Activity activity;
    private int textWidth,textHeight;
    private int textSize;
    private int textColor;
    private int line_bg;
    private int line_height;
    private int line_width;
    private boolean isLine;
    private TextClickListener textClickListener;

    TitleAdapter(@Nullable List<MultipleItemEntity> data, Activity activity) {
        super(data);
        this.activity = activity;
        addItemType(MultipleItemEntity.mItem, R.layout.title_item);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultipleItemEntity item) {

        final TextView textView = helper.getView(R.id.item_title);
        textView.setText(item.getTitlEntity().getTitle());
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        ConstraintLayout.LayoutParams linearParams =(ConstraintLayout.LayoutParams) textView.getLayoutParams();
        linearParams.width = textWidth;
        linearParams.height = textHeight;
        textView.setLayoutParams(linearParams);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textClickListener!=null){
                    textClickListener.onTextClick(helper.getLayoutPosition(),textView.getText().toString());
                }
            }
        });

        View line = helper.getView(R.id.item_line);
        ConstraintLayout.LayoutParams linearParams2 =(ConstraintLayout.LayoutParams) line.getLayoutParams();
        linearParams2.height = line_height;
        linearParams2.width = line_width;
        line.setLayoutParams(linearParams2);
        helper.setBackgroundColor(R.id.item_line,line_bg);
        if(!isLine){
            helper.setVisible(R.id.item_line,false);
        }
        if(helper.getLayoutPosition()==getData().size()-1){
            helper.setVisible(R.id.item_line,false);
        }
    }

    public void setTextWidth(int textWidth) {
        if(textWidth>=0) {
            this.textWidth = getPixelsFromDp(textWidth);
        }else{
            this.textWidth = textWidth;
        }
    }

    public void setTextHeight(int textHeight) {
        if(textHeight>=0) {
            this.textHeight = getPixelsFromDp(textHeight);
        }else{
            this.textHeight = textHeight;
        }
    }

    public void setLine_height(int line_height) {
        if(line_height>=0) {
            this.line_height = getPixelsFromDp(line_height);
        }else{
            this.line_height = line_height;
        }
    }

    public void setLine_width(int line_width) {
        if(line_width>=0){
            this.line_width = getPixelsFromDp(line_width);
        }else {
            this.line_width = line_width;
        }
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setLine_bg(int line_bg) {
        this.line_bg = line_bg;
    }

    public void setIsLine(boolean isLine) {
        this.isLine = isLine;
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

