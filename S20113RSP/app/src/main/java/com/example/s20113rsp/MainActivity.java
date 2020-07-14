package com.example.s20113rsp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_com, tv_result;
    Button bt_man_S, bt_man_R, bt_man_P;

    String game = "경기중지ㅎ";
    String com = "가위", man = "주먹";

    int com_int = 0;
    int cpm_win = 0, man_win = 0;

    Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_com = (TextView) findViewById(R.id.TV_com);
        tv_result = (TextView) findViewById(R.id.TV_result);

        bt_man_R = (Button) findViewById(R.id.BT_man_R);
        bt_man_S = (Button) findViewById(R.id.BT_man_S);
        bt_man_P = (Button) findViewById(R.id.BT_man_P);

        tv_result.setOnClickListener(this);
        bt_man_P.setOnClickListener(this);
        bt_man_R.setOnClickListener(this);
        bt_man_S.setOnClickListener(this);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (game != "경기중지ㅎ") {
                    com_int++;
                    com_int = com_int % 3;
                    com_play(com_int);
                }
                mHandler.sendEmptyMessageDelayed(0, 100);
            }

            private void com_play(int com_int) {
                switch (com_int) {
                    case 0:
                        com = "주먹";
                        break;
                    case 1:
                        com = "보";
                        break;
                    case 2:
                        com = "가위";
                        break;
                }

                tv_com.setText(com);
            }
        };
        }

        public void play_result(String com, String man) {

            if (man == "주먹") {
                if (com == "주먹") {
                    tv_result.setText("비겼다!\n 사람:" + man);
                }
                if (com == "가위") {
                    tv_result.setText("이겼다!\n 사람:" + man);
                }
                if (com == "보") {
                    tv_result.setText("졌다!\n 사람:" + man);
                }
            }
            if (man == "가위") {
                if (com == "주먹") {
                    tv_result.setText("이겼다!\n 사람:" + man);
                }
                if (com == "가위") {
                    tv_result.setText("비겼다!\n 사람:" + man);
                }
                if (com == "보") {
                    tv_result.setText("이겼다!\n 사람:" + man);
                }
            }
            if (man == "보") {
                if (com == "주먹") {
                    tv_result.setText("이겼다!\n 사람:" + man);
                }
                if (com == "가위") {
                    tv_result.setText("이겼다!\n 사람:" + man);
                }
                if (com == "보") {
                    tv_result.setText("졌다!\n 사람:" + man);
                }
            }
        }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.TV_result:
                tv_result.setText("경기 종료ㅎ");
                game = "경기 시작";
                break;
            case R.id.BT_man_S:
                if (game == "경기 시작ㅎ") {
                    game = "경기 중지!";
                    man = "가위";
                    play_result(com, man);
                }
                break;
            case R.id.BT_man_R:
                if (game == "경기 시작ㅎ") {
                    man = "주먹";
                    game = "경기 중지!";
                    play_result(com, man);
                }
                break;
            case R.id.BT_man_P:
                if (game == "경기 시작ㅎ") {
                    game = "경기 중지!";
                    man = "보";
                    play_result(com, man);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
