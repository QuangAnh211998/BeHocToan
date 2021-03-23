package com.example.behoctoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Xem_diem extends AppCompatActivity {
    TextView txt_diemcao, txt_diemthi;
    Button btn_back;
    SharedPreferences luuDiemcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_diem);

        txt_diemthi = (TextView) findViewById(R.id.txt_diemthi);
        txt_diemcao = (TextView) findViewById(R.id.txt_diemcao);
        btn_back = (Button) findViewById(R.id.btn_back1);

        final Intent intent = getIntent(); // lấy dữ liệu
        int diemvuathi = intent.getIntExtra("diemthi", 0);// lấy điểm vừa thi ra

        luuDiemcao = getSharedPreferences("diemsochoi", MODE_PRIVATE);// lấy điểm số đã lưu
        int diemcaonhat = luuDiemcao.getInt("diem", 0);

        if (diemvuathi > diemcaonhat) {
            luudiem(diemvuathi);// lưu điểm cao
            txt_diemthi.setText("Điểm vừa thi: " + diemvuathi + "");
            txt_diemcao.setText("Điểm cao nhất: " + diemvuathi);
        } else {
            txt_diemthi.setText("Điểm vừa thi: " + diemvuathi + "");
            txt_diemcao.setText("Điểm cao nhất: " + diemcaonhat);
        }
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Xem_diem.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void luudiem(int diemThi) {
        SharedPreferences.Editor editor = luuDiemcao.edit();
        editor.putInt("diem", diemThi);
        editor.commit();// lệnh lưu
    }
}
