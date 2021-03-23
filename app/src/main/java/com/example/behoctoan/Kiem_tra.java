package com.example.behoctoan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.behoctoan.Database.DatabaseHelper;
import com.example.behoctoan.Listcauhoi.ListKiemtra;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Kiem_tra extends AppCompatActivity implements View.OnClickListener {

    Button btn_a, btn_b, btn_c, btn_d, btn_back;
    TextView txt_thoigian, txt_diem, txt_cauhoi, txt_id;
    ImageView img_dongho;
    DatabaseHelper databaseHelper;
    List<ListKiemtra> ListKiemtra = new ArrayList<>();
    private int position;
    private int diem;
    private MediaPlayer mediaPlayer;

    CountDownTimer timer;
    long t = 600;
    int rePlay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiem_tra);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.openDataBase();
        anhxa();
        innit();
        getData();

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.xoay);
        img_dongho.setAnimation(animation);
        animation.start();

        timer = new CountDownTimer(t * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) t/60 ;
                int seconds = (int) t%60 ;
                String countTimer = String.format("%02d:%02d", minutes , seconds );
                txt_thoigian.setText(countTimer);
                t = t - 1;
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(Kiem_tra.this, Xem_diem.class);
                intent.putExtra("diemthi", diem);
                startActivity(intent);
            }
        }.start();
    }

    public void anhxa() {
        btn_back = (Button) findViewById(R.id.btn_back1);
        btn_a = (Button) findViewById(R.id.dapan_a);
        btn_b = (Button) findViewById(R.id.dapan_b);
        btn_c = (Button) findViewById(R.id.dapan_c);
        btn_d = (Button) findViewById(R.id.dapan_d);
        txt_cauhoi = (TextView) findViewById(R.id.txt_cauhoi);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_diem = (TextView) findViewById(R.id.txt_diem);
        txt_thoigian = (TextView) findViewById(R.id.txt_thoigian);
        img_dongho = (ImageView) findViewById(R.id.img_dh);

        btn_a.setOnClickListener(this);
        btn_b.setOnClickListener(this);
        btn_c.setOnClickListener(this);
        btn_d.setOnClickListener(this);

        position = 0;
        diem = 0;

    }

    private void getData() {
        ListKiemtra = databaseHelper.getDaTaKiemtra();
        setQuestion(0, 0);
    }

    private void selectDapAn(Button btn) {
        if (position < ListKiemtra.size() - 1) {// sosanh ví trí câu hỏi
            position = position + 1;// ví trí câu tiếp theo
            if (btn.getText().equals(checkDapAn(position - 1))) { // check câu hiện tại
                diem = diem + 10;
                mediaPlayer = MediaPlayer.create(this, R.raw.dung);
                mediaPlayer.start();
            } else {
                mediaPlayer = MediaPlayer.create(this, R.raw.sai);
                mediaPlayer.start();
            }
            setQuestion(position, diem);// chuyển câu tiếp
        } else {
            if (btn.getText().equals(checkDapAn(ListKiemtra.size() - 1))) { // check câu cuối cùng
                diem = diem + 10;
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Chúc mừng bé đã hoàn thành bài thi!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Kiem_tra.this, Xem_diem.class);

                    intent.putExtra("diemthi", diem);// truyền điểm

                    startActivity(intent);
                }
            }, 1000);
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });
    }

    private String checkDapAn(int position) {
        String dapAn;
        dapAn = ListKiemtra.get(position).getDapandung();
        return dapAn;
    }

    private void setQuestion(int position, int diem) {
        final int ch = position + 1;
        txt_id.setText("Câu  " + ch + " :");
        txt_cauhoi.setText(ListKiemtra.get(position).getCauhoi());
        btn_a.setText(ListKiemtra.get(position).getDapana());
        btn_b.setText(ListKiemtra.get(position).getDapanb());
        btn_c.setText(ListKiemtra.get(position).getDapanc());
        btn_d.setText(ListKiemtra.get(position).getDapand());
        txt_diem.setText("Điểm: " + diem + "");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dapan_a:
                selectDapAn(btn_a);
                break;
            case R.id.dapan_b:
                selectDapAn(btn_b);
                break;
            case R.id.dapan_c:
                selectDapAn(btn_c);
                break;
            case R.id.dapan_d:
                selectDapAn(btn_d);
                break;
            default:
                break;
        }
    }

    public void innit() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rePlay = 1;
                timer.cancel();
                t=t+1;// thoi gian cong thêm 1. lúc chạy tiếp nó sẽ chạy tiếp từ tg dừng.
                AlertDialog.Builder builder = new AlertDialog.Builder(Kiem_tra.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có chắc muốn thoát khỏi bài kiểm tra!");
                builder.setMessage("Hãy chọn để xác nhận!");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Kiem_tra.this, Xem_diem.class);
                        intent.putExtra("diemthi", diem);// truyền điểm
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        rePlay = 0;
                        timer.start();
                        t = t - 1;
                    }
                });
                builder.show();
            }
        });
    }

}
