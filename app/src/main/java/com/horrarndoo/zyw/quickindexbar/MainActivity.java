package com.horrarndoo.zyw.quickindexbar;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.OvershootInterpolator;
import android.widget.ListView;
import android.widget.TextView;

import com.horrarndoo.zyw.quickindexbar.domain.Person;
import com.horrarndoo.zyw.quickindexbar.view.QuickIndexBar;
import com.horrarndoo.zyw.quickindexbar.view.QuickIndexBarAdapter;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.Collections;

import static com.horrarndoo.zyw.quickindexbar.view.QuickIndexBar.OnTouchLetterListener;

public class MainActivity extends AppCompatActivity {
    private QuickIndexBar quickIndexBar;
    private ListView listview;
    private TextView currentWord;

    private ArrayList<Person> persons = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
        listview = (ListView) findViewById(R.id.listview);
        currentWord = (TextView) findViewById(R.id.currentWord);

        //1.准备数据
        fillList();
        //2.对数据进行排序
        Collections.sort(persons);
        //3.设置Adapter
        listview.setAdapter(new QuickIndexBarAdapter(this, persons));

        quickIndexBar.setOnTouchLetterListener(new OnTouchLetterListener() {
            @Override
            public void onTouchLetter(String letter) {
                //根据当前触摸的字母，去集合中找那个item的首字母和letter一样，然后将对应的item放到屏幕顶端
                for (int i = 0; i < persons.size(); i++) {
                    String firstWord = persons.get(i).getPinyin().charAt(0) + "";
                    if (letter.equals(firstWord)) {
                        //说明找到了，那么应该讲当前的item放到屏幕顶端
                        listview.setSelection(i);
                        break;//只需要找到第一个就行
                    }
                }
                //显示当前触摸的字母
                showCurrentWord(letter);
            }
        });

        //通过缩小currentWord来隐藏
        ViewHelper.setScaleX(currentWord, 0);
        ViewHelper.setScaleY(currentWord, 0);
    }

    private boolean isScale = false;
    private Handler handler = new Handler();

    protected void showCurrentWord(String letter) {
        currentWord.setText(letter);
        if (!isScale) {
            isScale = true;
            ViewPropertyAnimator.animate(currentWord).scaleX(1f)
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(450).start();
            ViewPropertyAnimator.animate(currentWord).scaleY(1f)
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(450).start();
        }

        //先移除之前的任务
        handler.removeCallbacksAndMessages(null);

        //延时隐藏currentWord
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //				currentWord.setVisibility(View.INVISIBLE);
                ViewPropertyAnimator.animate(currentWord).scaleX(0f).setDuration(450).start();
                ViewPropertyAnimator.animate(currentWord).scaleY(0f).setDuration(450).start();
                isScale = false;
            }
        }, 1500);
    }

    private void fillList() {
        // 虚拟数据
        persons.add(new Person("李四"));
        persons.add(new Person("张三"));
        persons.add(new Person("阿三"));
        persons.add(new Person("阿四"));
        persons.add(new Person("王宝宝"));
        persons.add(new Person("段正淳"));
        persons.add(new Person("张三丰"));
        persons.add(new Person("陈坤"));
        persons.add(new Person("林俊杰1"));
        persons.add(new Person("陈坤2"));
        persons.add(new Person("王二a"));
        persons.add(new Person("林俊杰a"));
        persons.add(new Person("张四"));
        persons.add(new Person("林俊杰"));
        persons.add(new Person("王二"));
        persons.add(new Person("王二b"));
        persons.add(new Person("赵四"));
        persons.add(new Person("杨坤"));
        persons.add(new Person("杨坤1"));
        persons.add(new Person("李伟1"));
        persons.add(new Person("宋江"));
        persons.add(new Person("宋2"));
        persons.add(new Person("aaaaa"));
        persons.add(new Person("bbbbb"));
        persons.add(new Person("abbbb"));
        persons.add(new Person("acccc"));
        persons.add(new Person("ddddd"));
        persons.add(new Person("dddd2"));
    }
}
