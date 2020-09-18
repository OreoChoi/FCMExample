package com.kcejjh.gcmexample.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kcejjh.gcmexample.R;

import static com.kcejjh.gcmexample.Constant.TOPIC_WEATHER;

/**
 * jhChoi - 20200918
 * FCM Example MainActivity 입니다.
 */
public class MainActivity extends BaseActivity {
    private Button subOk, subCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findId();
        getCurrentToken();
        setOnClickEvent();
    }

    /**
     * jhChoi - 20200915
     * View id를 획득합니다.
     */
    private void findId() {
        subOk = findViewById(R.id.subOk);
        subCancel = findViewById(R.id.subCancel);
    }

    /**
     * jhChoi - 20200915
     * 클릭 이벤트를 셋팅합니다.
     */
    private void setOnClickEvent() {
        subOk.setOnClickListener((v) -> {
            FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_WEATHER).addOnCompleteListener((task) -> {
                String msg;

                if (!task.isSuccessful()) {
                    msg = TOPIC_WEATHER + " 구독 성공";
                } else {
                    msg = TOPIC_WEATHER + " 구독 실패";
                }

                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            });
        });

        subCancel.setOnClickListener((v) -> FirebaseMessaging.getInstance().unsubscribeFromTopic(TOPIC_WEATHER));
    }

    /**
     * jhChoi - 20200918
     * 현재 토큰 값을 확인합니다.
     */
    private void getCurrentToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener((task) -> {
            if (!task.isSuccessful()) {
                toast("Failed" + task.getException());
            } else {
                toast("Current Token" + task.getResult().getToken());
            }
        });
    }
}
