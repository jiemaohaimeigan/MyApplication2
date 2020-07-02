package com.example.myapplication_demo;

import android.content.Context;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class IndexFragment extends Fragment {

    CallBacks callBacks ;

    //初始化callBacks
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callBacks = (CallBacks) context ;  //context就是activity（把activity强转为CallBacks对象）
    }

    //重写onCreateView，通过inflater加载布局文件,返回view对象
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index,container,false) ;
        ListView listView = view.findViewById(R.id.listview) ;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //使用callBacks对象的onItemSelected方法传递position参数
                callBacks.onItemSelected(position);
            }
        });
        return view;
    }
}
