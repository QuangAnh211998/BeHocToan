package com.example.behoctoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.behoctoan.Tinh.Tinh_nham;

public class Luyen_tap extends AppCompatActivity {
    Button btn_tinhnham, btn_sosanh, btn_tapdem, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_tap);
        btn_back = (Button)findViewById(R.id.btn_back1);
        btn_tinhnham = (Button)findViewById(R.id.btn_tinhnham);
        btn_sosanh = (Button)findViewById(R.id.btn_sosanh);
        btn_tapdem = (Button)findViewById(R.id.btn_tapdem);

        btn_tinhnham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Luyen_tap.this, Tinh_nham.class);
                startActivity(intent);
            }
        });
        btn_sosanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Luyen_tap.this, so_sanh.class);
                startActivity(intent);
            }
        });
        btn_tapdem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Luyen_tap.this, tap_dem.class);
                startActivity(intent);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Luyen_tap.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
