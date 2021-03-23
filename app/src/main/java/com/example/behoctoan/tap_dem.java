package com.example.behoctoan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.behoctoan.Database.DatabaseHelper;
import com.example.behoctoan.Listcauhoi.ListTapdem;

import java.util.ArrayList;
import java.util.List;

public class tap_dem extends AppCompatActivity implements View.OnClickListener {
    Button btn_a, btn_b, btn_c, btn_d, btn_back;
    TextView txt_id, txt_score;
    ImageView imganh;
    DatabaseHelper databaseHelper;
    List<ListTapdem> listtapdem = new ArrayList<>();
    private int position;
    private int score;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_dem);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.openDataBase();
        anhxa();
        innit();
        getData();
    }

    public void anhxa() {
        btn_back = (Button) findViewById(R.id.btn_back2);
        btn_a = (Button) findViewById(R.id.dapan_a);
        btn_b = (Button) findViewById(R.id.dapan_b);
        btn_c = (Button) findViewById(R.id.dapan_c);
        btn_d = (Button) findViewById(R.id.dapan_d);
        txt_id = (TextView) findViewById(R.id.txt_id);
        imganh = (ImageView) findViewById(R.id.img_anh);
        txt_score = (TextView) findViewById(R.id.txt_score);

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
        listtapdem = databaseHelper.getDaTaTapdem();
        setQuestion(0, 0);
}

    private void selectDapAn(Button btn) {
        if (position < listtapdem.size() - 1) {
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
            if (btn.getText().equals(checkDapAn(listtapdem.size() - 1))) {
                score = score + 1;
            }

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(tap_dem.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                    builder.setTitle("Chúc mừng bé đã trả lời đúng: " + score + "/" + listtapdem.size());
                    builder.setMessage("Chọn xác nhận chơi lại hoặc thoát");
                    builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(tap_dem.this, Luyen_tap.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Chơi lại", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(tap_dem.this, tap_dem.class);
                            startActivity(intent);
                        }
                    });
                    builder.show();

                }
            }, 1000);
        }
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//            }
//        });
    }

    private String checkDapAn(int position) {
        String dapAn;
        dapAn = listtapdem.get(position).getDapandung();
        return dapAn;
    }

    private void setQuestion(int position, int score) {
        final int ch = position + 1;
        txt_id.setText("Câu  " + ch + " :");
        Bitmap bitmap = BitmapFactory.decodeByteArray(listtapdem.get(position).getAnh(), 0, listtapdem.get(position).getAnh().length);
        imganh.setImageBitmap(bitmap);
        btn_a.setText(listtapdem.get(position).getDapana());
        btn_b.setText(listtapdem.get(position).getDapanb());
        btn_c.setText(listtapdem.get(position).getDapanc());
        btn_d.setText(listtapdem.get(position).getDapand());
        txt_score.setText("Bé trả lời đúng số câu: " + score + "/" + listtapdem.size());
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
                Intent intent = new Intent(tap_dem.this, Luyen_tap.class);
                startActivity(intent);
            }
        });
    }
}

