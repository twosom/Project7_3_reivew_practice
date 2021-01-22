package com.cookandroid.project7_3_reivew_practice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button mainBtn;
    EditText mainName, mainEmail, dialogName, dialogEmail;
    TextView toastText;
    View dialogView, toastView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("사용자 정보 입력(수정)");

        mainName = (EditText) findViewById(R.id.mainName);
        mainEmail = (EditText) findViewById(R.id.mainEmail);
        mainBtn = (Button) findViewById(R.id.mainBtn);


        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);

                dialogName = (EditText) dialogView.findViewById(R.id.dialogName);
                dialogEmail = (EditText) dialogView.findViewById(R.id.dialogEmail);

                dialogName.setText(mainName.getText().toString());
                dialogEmail.setText(mainEmail.getText().toString());


                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("사용자 정보 입력")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setView(dialogView)
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //랜덤하게 나타내기 위해서 객체 생성
                                int xOffset = (int) (Math.random() * getDisplay().getWidth());
                                int yOffset = (int) (Math.random() * getDisplay().getHeight());
                                Log.e("Resolution", String.valueOf(getDisplay().getWidth()) +", " +
                                        String.valueOf(getDisplay().getHeight()));
                                //토스트 뷰 할당
                                toastView = View.inflate(MainActivity.this, R.layout.toast, null);
                                //빈 토스트 객체 생성
                                Toast toast = new Toast(getApplicationContext());
                                toastText = (TextView) toastView.findViewById(R.id.toastText);
                                toastText.setText("취소했습니다.");
                                toast.setGravity(Gravity.TOP | Gravity.LEFT, xOffset, yOffset);
                                toast.setView(toastView);
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mainName.setText(dialogName.getText().toString());
                                mainEmail.setText(dialogEmail.getText().toString());
                            }
                        })
                        .show();
            }
        });

    }
}