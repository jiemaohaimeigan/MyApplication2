package com.example.myapplication_demo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText6 = (EditText) findViewById(R.id.editText6);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final EditText editText3 = (EditText) findViewById(R.id.editText3);
        final EditText editText4 = (EditText) findViewById(R.id.editText4);
        final EditText editText5 = (EditText) findViewById(R.id.editText5);
        final EditText editText6 = (EditText) findViewById(R.id.editText6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String itemDesc =editText2.getText().toString();
                String itemRemark = editText3.getText().toString();
                String price = editText4.getText().toString();
                String id = editText5.getText().toString();
                String time = editText6.getText().toString();

                Map<String,String> map = new HashMap<>();
                map.put("itemName",name) ;
                map.put("itemDesc",itemDesc) ;
                map.put("itemRemark",itemRemark) ;
                map.put("price",price) ;
                map.put("kindId",id) ;
                map.put("avail",time) ;


                try {
                    String result = OkHttpUtil.postRequest("http://172.18.85.254:8080/auction/api/items",map);
                    System.out.println(result);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void submit() {
        // validate
        String editTextString = editText.getText().toString().trim();
        if (TextUtils.isEmpty(editTextString)) {
            Toast.makeText(this, "editTextString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String editText2String = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(editText2String)) {
            Toast.makeText(this, "editText2String不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String editText3String = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(editText3String)) {
            Toast.makeText(this, "editText3String不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String editText4String = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(editText4String)) {
            Toast.makeText(this, "editText4String不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String editText5String = editText5.getText().toString().trim();
        if (TextUtils.isEmpty(editText5String)) {
            Toast.makeText(this, "editText5String不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String editText6String = editText6.getText().toString().trim();
        if (TextUtils.isEmpty(editText6String)) {
            Toast.makeText(this, "editText6String不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
