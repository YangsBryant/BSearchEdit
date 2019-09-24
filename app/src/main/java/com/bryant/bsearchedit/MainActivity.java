package com.bryant.bsearchedit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bryant.editlibrary.BSearchEdit;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> list;
    private BSearchEdit bSearchEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        list.add("江西省赣州市");
        list.add("广东省深圳市");
        list.add("广东省珠海市");

        EditText editText = findViewById(R.id.edit_text);
        bSearchEdit = new BSearchEdit(this,editText,200);
        bSearchEdit.build();
        bSearchEdit.setSearchList(list);

        bSearchEdit.setTextClickListener(new BSearchEdit.TextClickListener() {
            @Override
            public void onTextClick(int position, String text) {
                Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
            }
        });

        Button button01 = findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                list.add("小米 9pro");
                list.add("华为 mate30");
                list.add("vivo nex3");
                list.add("iPhone 11");
                bSearchEdit.setSearchList(list);
                Toast.makeText(MainActivity.this,"更新数据成功",Toast.LENGTH_SHORT).show();
            }
        });

        Button button02 = findViewById(R.id.button02);
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                list.add("瑞士民众抵制5G");
                list.add("威马汽车起火");
                list.add("王者荣耀新英雄西施");
                list.add("黄渤出演姜子牙");
                list.add("北京天空飞机刷屏");
                list.add("Kimi名字由来");
                bSearchEdit.setSearchList(list);
                Toast.makeText(MainActivity.this,"更新数据成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}