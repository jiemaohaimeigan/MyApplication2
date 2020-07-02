package com.example.myapplication_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText editTextname;
    EditText editTextpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextname = findViewById(R.id.editText);

        editTextpass = findViewById(R.id.editText2);
    }

    public void testgetRequest(View view) throws ExecutionException, InterruptedException {
        //输出客户端返回的数据
        System.out.println(OkHttpUtil.getRequest("https://www.baidu.com"));

    }

    public void login(View view) throws ExecutionException, InterruptedException {
        //获取输入框中的账号和密码
        String name = editTextname.getText().toString();
        String pass = editTextpass.getText().toString();

        //需要把获取到的数据打包到map
        Map<String,String> map = new HashMap<>();
        //把从editText中拿到的数据存到map中
        map.put("username",name);
        map.put("userpass",pass);
        String url = "http://172.18.85.254:8080/auction/api/users/login" ;

        //调用OkHttpUtil中的post方法
        String str = OkHttpUtil.postRequest(url,map) ;

        //判断服务器返回的字符串
        if (str.equals("1")){
            //如果返回的是1返回登录成功页面
            Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,IndexActivity.class) ;
            startActivity(intent);
        } else {
            Toast.makeText(this,"登录失败",Toast.LENGTH_LONG).show();
        }
    }
}
