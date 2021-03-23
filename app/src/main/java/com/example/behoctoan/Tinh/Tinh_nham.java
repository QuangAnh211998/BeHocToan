package com.example.behoctoan.Tinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.behoctoan.Luyen_tap;
import com.example.behoctoan.R;

public class Tinh_nham extends AppCompatActivity {
    Button btn_back, btn_level1, btn_level2, btn_level3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_nham);
        btn_back = (Button)findViewById(R.id.btn_back2);
        btn_level1 = (Button)findViewById(R.id.btn_id1);
        btn_level2 = (Button)findViewById(R.id.btn_id2);
        btn_level3 = (Button)findViewById(R.id.btn_id3);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tinh_nham.this, Luyen_tap.class);
                startActivity(intent);
            }
        });
        btn_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tinh_nham.this, level1.class);
                startActivity(intent);
            }
        });
        btn_level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tinh_nham.this, level2.class);
                startActivity(intent);
            }
        });
        btn_level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tinh_nham.this, level3.class);
                startActivity(intent);
            }
        });
    }
}
