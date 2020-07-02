package com.example.myapplication_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication_demo.dummy.DummyContent;

public class FailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment2, FailFragment.newInstance(1))
                .commit();
    }

}
