package com.example.wangbeimin.cashbook.UI;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wangbeimin.cashbook.R;

public class ShowActivity extends Activity {
    private TextView showTag;
    private String tag;
    private TextView showType;
    private String type;
    private TextView showIncome;
    private String income;
    private TextView showNote;
    private String note;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ininView();
}
    public void ininView(){
        showTag = findViewById(R.id.show_tag);
        tag = getIntent().getStringExtra("TAG");
        showTag.setText(tag);
        showType = findViewById(R.id.show_type);
        type = getIntent().getStringExtra("TYPE");
        showType.setText(type);
        showIncome = findViewById(R.id.show_income);
        income = getIntent().getStringExtra("INCOME");
        showIncome.setText(income);
        showNote = findViewById(R.id.show_note);
        note = getIntent().getStringExtra("NOTE");
        showNote.setText(note);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
