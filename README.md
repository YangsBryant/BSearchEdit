# BSearchEdit

## 引入module
```java
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://www.jitpack.io' }
    }
}
```
```java
implementation 'com.github.YangsBryant:BGradualProgress:1.0.3'
```

#支持自动展示搜索条目

#支持手动展示搜索条目(可自己记录历史数据，可等请求回调后弹出搜索条目)

#背景支持颜色，selector，图片等资源属性

## BSearchEdit动态属性
方法名 | 属性
--------- | -------------
build() | 参数设置完毕，一定要build一下
setTimely(boolean timely) | 是否自动展示搜索条目，默认true
showPopup()  | 手动弹出搜索条目，当setTimely为true时失效
setTextClickListener(TextClickListener textClickListener) | 点击监听器   
setTextWidth(int textWidth) | 设置文本宽度，单位dp
setTextHeight(int textHeight) | 设置文本高度，单位dp
setTextSize(int textSize) | 设置文本字体大小
setTextColor(int textColor) | 设置文本颜色
setLine_height(int line_height) | 设置线条高度，单位dp
setLine_width(int line_width) | 设置线条宽度，单位dp
setIsLine(boolean isLine) | 是否显示分割线
setPopup_bg(int popup_bg) | 设置窗体背景，可以是图片，颜色，selector等资源

## 关键代码
```java
        EditText editText = findViewById(R.id.edit_text);//获取一个EditText
        bSearchEdit = new BSearchEdit(this,editText,200);//第三个必须要设置窗体的宽度，单位dp
        bSearchEdit.build();
        //更新数据
        bSearchEdit.setSearchList(list);
```

##演示代码
```java
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
```
#演示代码module里有

## 联系QQ：961606042

