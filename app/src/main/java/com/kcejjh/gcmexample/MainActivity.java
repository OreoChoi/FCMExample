package com.kcejjh.gcmexample;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kcejjh.gcmexample.Activity.BaseActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {
    private final String TAG = this.getClass().getName();
    private String TOKEN;

    private Button subOk, subCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findId();
        getCurrentToken();
        setOnClickEvent();
        checkBackgroundRestricted();
    }

    /**
     * jhChoi - 20200915
     * 백그라운드 제한인지 체크합니다.
     */
    private void checkBackgroundRestricted() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            Log.d(TAG, "not check");
            return;
        }

        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        if (am == null) {
            Log.e(TAG, "Not object ActivityManager");
            return;
        }

        if (am.isBackgroundRestricted()) {
            Log.e(TAG, "Current State Background Restricted");
        } else {
            Log.e(TAG, "Current State Not Background Restricted");
        }
    }

    /**
     * jhChoi - 20200915
     * view id를 획득합니다.
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
            FirebaseMessaging.getInstance().subscribeToTopic("weather")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg;
                            if (!task.isSuccessful()) {
                                msg = "구독 실패 -> weather";
                            } else {
                                msg = "구독 성공 -> weather";
                            }

                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        subCancel.setOnClickListener((v) -> FirebaseMessaging.getInstance().unsubscribeFromTopic("weather"));
    }

    private void getCurrentToken() {
        FirebaseInstanceId
                .getInstance()
                .getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            toast("getInstanceId failed" + task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        TOKEN = task.getResult().getToken();
                    }
                });
    }
}
