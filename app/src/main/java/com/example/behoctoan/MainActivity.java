package com.example.behoctoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_luyentap, btn_kiemtra, btn_xemdiem;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_luyentap = (Button)findViewById(R.id.btn_luyentap);
        btn_kiemtra = (Button)findViewById(R.id.btn_kiemtra);
        btn_xemdiem = (Button)findViewById(R.id.btn_xemdiem);

        btn_luyentap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Luyen_tap.class);
                startActivity(intent);
            }
        });
        btn_kiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Kiem_tra.class);
                startActivity(intent);
            }
        });
        btn_xemdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Xem_diem.class);
                startActivity(intent);
            }
        });
    }
}
