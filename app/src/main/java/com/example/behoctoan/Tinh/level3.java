package com.example.behoctoan.Tinh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.behoctoan.Database.DatabaseHelper;
import com.example.behoctoan.Listcauhoi.List3;
import com.example.behoctoan.R;

import java.util.ArrayList;
import java.util.List;

public class level3 extends AppCompatActivity implements View.OnClickListener {
    Button btn_a, btn_b, btn_c, btn_d, btn_back;
    TextView txt_id, txt_cauhoi, txt_score;
    DatabaseHelper databaseHelper;
    List<List3> List3 = new ArrayList<>();
    private int position;
    private int score;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.openDataBase();
        anhxa();
        innit();
        getData();
    }
    public void anhxa(){
        btn_back = (Button)findViewById(R.id.btn_back3);
        btn_a = (Button)findViewById(R.id.dapan_a);
        btn_b = (Button)findViewById(R.id.dapan_b);
        btn_c = (Button)findViewById(R.id.dapan_c);
        btn_d = (Button)findViewById(R.id.dapan_d);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_cauhoi = (TextView) findViewById(R.id.txt_cauhoi);
        txt_score = (TextView)findViewById(R.id.txt_score);

        btn_a.setOnClickListener(this);
        btn_b.setOnClickListener(this);
        btn_c.setOnClickListener(this);
        btn_d.setOnClickListener(this);

        position = 0;
        score = 0;

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.chaychu);
        txt_score.setAnimation(animation);
        animation.start();
  }
    private void getData() {
        List3 = databaseHelper.getDaTa3();
        setQuestion(0, 0);
    }

    private void selectDapAn(Button btn) {
        if (position < List3.size() - 1) {
            position = position + 1;
            if (btn.getText().equals(checkDapAn(position - 1))) {
                score = score + 1;
                mediaPlayer = MediaPlayer.create(this, R.raw.dung);
                mediaPlayer.start();
            } else {
                mediaPlayer = MediaPlayer.create(this, R.raw.sai);
                mediaPlayer.start();
            }
            setQuestion(position, score);
        } else {
            if (btn.getText().equals(checkDapAn(List3.size() - 1))) {
                score = score + 1;
                //txt_score.setText("Số câu trả lời đúng: " + score + "/" + List3.size());
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(level3.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                    builder.setTitle("Chúc mừng bé đã trả lời đúng: " + score + "/" + List3.size());
                    builder.setMessage("Chọn xác nhận chơi lại hoặc thoát");
                    builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(level3.this, Tinh_nham.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Chơi lại", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(level3.this, level3.class);
                            startActivity(intent);
                        }
                    });
                    builder.show();

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
        dapAn = List3.get(position).getDapandung();
        return dapAn;
    }

    private void setQuestion(int position, int score) {
        final int ch = position + 1;
        txt_id.setText("Câu  " + ch + " :");
        txt_cauhoi.setText(List3.get(position).getCauhoi());
        btn_a.setText(List3.get(position).getDapana());
        btn_b.setText(List3.get(position).getDapanb());
        btn_c.setText(List3.get(position).getDapanc());
        btn_d.setText(List3.get(position).getDapand());
        txt_score.setText("Bé trả lời đúng số câu: " + score + "/" + List3.size());
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


    public void innit(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(level3.this, Tinh_nham.class);
                startActivity(intent);
            }
        });
    }
}
