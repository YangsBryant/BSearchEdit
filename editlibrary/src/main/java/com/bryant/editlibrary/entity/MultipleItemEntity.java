package com.bryant.editlibrary.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

public class MultipleItemEntity implements MultiItemEntity,Serializable {
    public static final int mItem = 1;
    private int itemType;
    private TitleEntity titleEntity;

    public MultipleItemEntity(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public TitleEntity getTitlEntity() {
        return titleEntity;
    }

    public void setTitlEntity(TitleEntity titleEntity) {
        this.titleEntity = titleEntity;
    }
}
