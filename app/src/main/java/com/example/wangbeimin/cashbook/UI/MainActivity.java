package com.example.wangbeimin.cashbook.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wangbeimin.cashbook.R;
import com.example.wangbeimin.cashbook.utils.Cash;
import com.example.wangbeimin.cashbook.utils.CashAdapter;
import com.example.wangbeimin.cashbook.utils.DividerItemDecoration;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Cash> cashList = new ArrayList<>();
    final Calendar calendar = Calendar.getInstance();
    private Button editCash;
    private Button checkCash;
    private String tag;
    private String type;
    private String income;
    private String note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.initialize(this);
        ininView();
        addData();
        ininDataBase();
        ininRecycleView(cashList);
    }

    public void addData(){
        tag = getIntent().getStringExtra("TAG");
        type = getIntent().getStringExtra("TYPE");
        income = getIntent().getStringExtra("INCOME");
        note = getIntent().getStringExtra("NOTE");
        if (tag != null) {
            Cash cash = new Cash();
            cash.setTag(tag);
            cash.setType(type);
            cash.setIncome(income);
            cash.setNote(note);
            cash.setYear(calendar.get(Calendar.YEAR));
            cash.setMonth(calendar.get(Calendar.MONTH)+1);
            cash.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            cash.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            cash.save();
        }
    }

    public void ininDataBase(){
        SharedPreferences sharedPreferences=this.getSharedPreferences("share",MODE_PRIVATE);
        boolean isFirstRun=sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(isFirstRun){
            SQLiteDatabase db = LitePal.getDatabase();
            editor.putBoolean("isFirstRun", false);
            editor.commit();
        }else{
            cashList = LitePal.findAll(Cash.class);
        }
    }

    public  void ininRecycleView(final List<Cash> cashList){

        final RecyclerView recyclerView = findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Collections.reverse(cashList);
        final CashAdapter adapter = new CashAdapter(cashList);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);

        adapter.setRecyclerViewOnItemClickListener(new CashAdapter.RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                Cash cash = cashList.get(position);
                intent.putExtra("TAG", cash.getTag());
                intent.putExtra("TYPE", cash.getType());
                intent.putExtra("INCOME", cash.getIncome());
                intent.putExtra("NOTE", cash.getNote());
                startActivity(intent);
            }
        });

        adapter.setOnItemLongClickListener(new CashAdapter.RecyclerViewOnItemLongClickListener(){
            @Override
            public boolean onItemLongClickListener(View view,final int position) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("警告");
                dialog.setMessage("是否要删除这条记录");
                dialog.setCancelable(false);
                dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Cash cash = cashList.get(position);
                        cash.delete();
                        cashList.remove(position);
                        ininRecycleView(cashList);
                    }
                });
                dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();
                return true;
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void ininView(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        editCash = findViewById(R.id.edit_cash);
        editCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
        checkCash = findViewById(R.id.check_cash);
        checkCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckActivity.class);
                startActivity(intent);
            }
        });
    }
}
