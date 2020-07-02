package com.example.myapplication_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication_demo.dummy.DummyContent;

public class ItemActivity extends AppCompatActivity implements  ItemFragment.OnListFragmentInteractionListener {

    Button tianjia ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment,ItemFragment.newInstance(1))
                .commit();


        tianjia = findViewById(R.id.tianjia);
        tianjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemActivity.this,AddItemActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
