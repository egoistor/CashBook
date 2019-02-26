package com.example.wangbeimin.cashbook.UI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wangbeimin.cashbook.R;

import org.angmarch.views.NiceSpinner;
import org.json.JSONException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    private Button finish;
    private NiceSpinner edittag;
    private String tag;
    private NiceSpinner editType;
    private String type;
    private EditText editMoney;
    private EditText editNote;
    private String income;
    private String note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ininView();
    }

    public void ininView(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

        finish = findViewById(R.id.finish_edit);
        edittag = findViewById(R.id.button_tag);
        editType = findViewById(R.id.button_type);
        editMoney = findViewById(R.id.money_count);
        editNote = findViewById(R.id.note_about);
        ininedittag();
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editMoney.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "金额不能为空", Toast.LENGTH_SHORT).show();
                }
                else{
                    income = editMoney.getText().toString();
                    note = editNote.getText().toString();
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
                    intent.putExtra("TAG", tag);
                    intent.putExtra("TYPE", type);
                    intent.putExtra("INCOME", income+"元");
                    intent.putExtra("NOTE", note);
                    startActivity(intent);
                }
            }
        });
    }

    public void ininedittag(){
        final List<String> spinnerData = new LinkedList<>(Arrays.asList("","支出","收入"));
        edittag.attachDataSource(spinnerData);
        edittag.setBackgroundResource(R.drawable.textview_round_border);
        edittag.setTextColor(Color.BLACK);
        edittag.setTextSize(13);
        edittag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){

                }
                if (position == 1) {
                    tag = "支出";
                    method1();
                }
                if (position==2){
                    tag = "收入";
                    method1();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void method1() {
        new Thread(new Runnable() {
        @Override public void run() {
            try {
                Thread.sleep(0);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {
                @Override public void run() {
                    inineditType();
                }
            });
        }
    }).start();
    }

    public void inineditType(){
        final List<String> spinnerOutData = new LinkedList<>(Arrays.asList("","食物","娱乐","购物","医疗","其他"));
        final List<String> spinnerInData = new LinkedList<>(Arrays.asList("","工资","奖金","外快","其他"));
        if (tag == "支出"){
            editType.attachDataSource(spinnerOutData);
            editType.setBackgroundResource(R.drawable.textview_round_border);
            editType.setTextColor(Color.BLACK);
            editType.setTextSize(13);
            editType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position==1){
                        type = "食物";
                    }
                    if (position==2){
                        type = "娱乐";
                    }
                    if (position==3){
                        type = "购物";
                    }
                    if (position==4){
                        type = "医疗";
                    }
                    if (position==5){
                        type = "其他";
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else{
            editType.attachDataSource(spinnerInData);
            editType.setBackgroundResource(R.drawable.textview_round_border);
            editType.setTextColor(Color.BLACK);
            editType.setTextSize(13);
            editType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position==1){
                        type = "工资";
                    }
                    if (position==2){
                        type = "奖金";
                    }
                    if (position==3){
                        type = "外快";
                    }
                    if (position==4){
                        type = "其他";
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

}
