package com.kcejjh.gcmexample;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.annotation.NonNull;

public class FcmService extends FirebaseMessagingService {
    private final String TAG = this.getClass().getName();

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification noti = remoteMessage.getNotification();
        String title = noti.getTitle();
        String msg = noti.getBody();

        showToast("msgTitle : " + title + "\n"
                + "msg : " + msg + "\n");
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        showToast("onDeletedMessages가 실행되었습니다.");
    }

    /**
     * jhChoi - 2020915
     * toast를 출력합니다.
     */
    private void showToast(String msg) {
        Handler handler = new Handler(getMainLooper());
        handler.post(() -> Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show());
    }

}
