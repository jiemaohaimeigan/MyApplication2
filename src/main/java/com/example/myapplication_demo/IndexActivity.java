package com.example.myapplication_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class IndexActivity extends AppCompatActivity implements CallBacks {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }


    //重写接口中的方法(在fragment中具体按了哪个，重写这个方法用来跳转不同的页面)
    @Override
    public void onItemSelected(int position) {

        //处理不同的跳转
        Intent intent ;
        switch (position){
            //查看竞得物品
            case 0 :
                //跳转到ItemActivity类
                intent = new Intent(this,ItemActivity.class) ;
                startActivity(intent);
                break;
            //浏览流拍物品
            case 1:
                intent = new Intent(this,FailActivity.class);
                startActivity(intent);
                break;
        }


    }
}
