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
import com.example.behoctoan.Listcauhoi.List1;
import com.example.behoctoan.Luyen_tap;
import com.example.behoctoan.R;
import com.example.behoctoan.so_sanh;

import java.util.ArrayList;
import java.util.List;

public class level1 extends AppCompatActivity implements View.OnClickListener {
    Button btn_a, btn_b, btn_c, btn_d, btn_back;
    TextView txt_id, txt_cauhoi, txt_score;
    DatabaseHelper databaseHelper;
    List<List1> List1 = new ArrayList<>();
    private int position;
    private int score;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.openDataBase();
        anhxa();
        innit();
        getData();
    }

    public void anhxa() {
        btn_back = (Button) findViewById(R.id.btn_back3);
        btn_a = (Button) findViewById(R.id.dapan_a);
        btn_b = (Button) findViewById(R.id.dapan_b);
        btn_c = (Button) findViewById(R.id.dapan_c);
        btn_d = (Button) findViewById(R.id.dapan_d);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_cauhoi = (TextView) findViewById(R.id.txt_cauhoi);
        txt_score = (TextView) findViewById(R.id.tv_score);

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
        List1 = databaseHelper.getDaTa1();
        setQuestion(0, 0);
    }

    private void selectDapAn(Button btn) {
        if (position < List1.size() - 1) {// ví trí câu hỏi
            position = position + 1;// ví trí câu tiếp theo
            if (btn.getText().equals(checkDapAn(position - 1))) {// check câu hiện tại
                score = score + 1;
                mediaPlayer = MediaPlayer.create(this, R.raw.dung);
                mediaPlayer.start();
            } else {
                mediaPlayer = MediaPlayer.create(this, R.raw.sai);
                mediaPlayer.start();
            }
            setQuestion(position, score);// chuyển câu tiếp
        } else {
            if (btn.getText().equals(checkDapAn(List1.size() - 1))) {// check câu cuối cùng
                score = score + 1;
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(level1.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                    builder.setTitle("Chúc mừng bé đã trả lời đúng: " + score + "/" + List1.size());
                    builder.setMessage("Chọn xác nhận chơi lại hoặc thoát");
                    builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(level1.this, Tinh_nham.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Chơi lại", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(level1.this, level1.class);
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
        dapAn = List1.get(position).getDapandung();
        return dapAn;
    }

    private void setQuestion(int position, int score) {
        final int ch = position + 1;
        txt_id.setText("Câu  " + ch + " :");
        txt_cauhoi.setText(List1.get(position).getCauhoi());
        btn_a.setText(List1.get(position).getDapana());
        btn_b.setText(List1.get(position).getDapanb());
        btn_c.setText(List1.get(position).getDapanc());
        btn_d.setText(List1.get(position).getDapand());
        txt_score.setText("Bé trả lời đúng số câu: " + score + "/" + List1.size());
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
                Intent intent = new Intent(level1.this, Tinh_nham.class);
                startActivity(intent);
            }
        });
    }
}
