package com.example.wangbeimin.cashbook.UI;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangbeimin.cashbook.R;
import com.example.wangbeimin.cashbook.utils.Cash;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckActivity extends AppCompatActivity {
    private List<Cash> cashList = new ArrayList<>();
    private Button back;
    private EditText line1Year;
    private EditText line1Month;
    private EditText line1Day;
    private Button query1;
    private TextView out1;
    private double dayIncome;
    private double dayPayment;
    private TextView in1;
    private EditText line2Year;
    private EditText line2Month;
    private Button query2;
    private TextView out2;
    private TextView in2;
    private double monthIncome;
    private double monthPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        LitePal.initialize(this);
        ininDataBase();
        ininView();
    }

    public void ininDataBase(){
        cashList = LitePal.findAll(Cash.class);
    }

    public double toNum(String s){
        double num;
        String newS="";
        char[] c = s.toCharArray();
        for (int i=0;i<s.length();i++) {
            if (c[i]>=48&&c[i]<=57||c[i]=='.'){
                newS += c[i];
            }
        }

        return num = Double.parseDouble(newS);
    }

    public void ininView(){
        SQLiteDatabase db = LitePal.getDatabase();
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        line1Year = findViewById(R.id.line1_year);
        line1Month = findViewById(R.id.line1_month);
        line1Day = findViewById(R.id.line1_day);
        out1 = findViewById(R.id.show_day_out);
        in1 = findViewById(R.id.show_day_in);
        query1 = findViewById(R.id.finish_day_check);
        query1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year = line1Year.getText().toString();
                String month = line1Month.getText().toString();
                String day = line1Day.getText().toString();
                if (year.isEmpty()){
                    Toast.makeText(getApplicationContext(), "请输入查询的年份", Toast.LENGTH_SHORT).show();
                }else if (month.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入查询的月份", Toast.LENGTH_SHORT).show();
                }else if(day.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入查询的日期", Toast.LENGTH_SHORT).show();
                }else{
                    int iYear = Integer.valueOf(year).intValue();
                    int iMonth = Integer.valueOf(month).intValue();
                    int iDay = Integer.valueOf(day).intValue();
                    for (Cash cash :cashList ){
                        if (cash.getYear() == iYear && cash.getMonth() == iMonth && cash.getDay() == iDay) {
                            if (cash.getTag().equals("收入")) {
                                dayIncome += toNum(cash.getIncome().toString());
                            }else{
                                dayPayment += toNum(cash.getIncome().toString());
                            }
                        }
                    }
                    in1.setTextSize(20);
                    in1.setText(String.valueOf(dayIncome)+"元");
                    out1.setTextSize(20);
                    out1.setText(String.valueOf(dayPayment)+"元");
                }
            }
        });
        line2Year = findViewById(R.id.line2_year);
        line2Month = findViewById(R.id.line2_month);
        out2 = findViewById(R.id.show_month_out);
        in2 = findViewById(R.id.show_month_in);
        query2 = findViewById(R.id.finish_month_check);
        query2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year = line2Year.getText().toString();
                String month = line2Month.getText().toString();
                if (year.isEmpty()){
                    Toast.makeText(getApplicationContext(), "请输入查询的年份", Toast.LENGTH_SHORT).show();
                }else if (month.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入查询的月份", Toast.LENGTH_SHORT).show();
                } else{
                    int iYear = Integer.parseInt(year);
                    int iMonth = Integer.parseInt(month);
                    for (Cash cash :cashList ){
                        if (cash.getYear() == iYear && cash.getMonth() == iMonth ) {
                            if (cash.getTag().equals("收入")) {
                                monthIncome += toNum(cash.getIncome().toString());
                            }else{
                                monthPayment += toNum(cash.getIncome().toString());
                            }
                        }
                    }
                    out2.setTextSize(20);
                    out2.setText(String.valueOf(monthPayment)+"元");
                    in2.setTextSize(20);
                    in2.setText(String.valueOf(monthIncome)+"元");
                }
            }
        });
    }
}
