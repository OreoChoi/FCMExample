package com.kcejjh.gcmexample.Service;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.annotation.NonNull;

/**
 * jhChoi - 20200918
 * FCM 수신할 Service 컴포넌트입니다.
 */
public class FCMService extends FirebaseMessagingService {

    private final String TAG = this.getClass().getName();

    /**
     * jhChoi - 20200918
     * FCM에서 토큰이 새롭게 갱신될 경우 본 메서드가 실행됩니다.
     * */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
    }

    /**
     * jhChoi - 20200918
     * 앱 실행도중 FCM이 온 경우 본 메서드가 실행됩니다.
     * */
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification noti = remoteMessage.getNotification();
        String title = noti.getTitle();
        String msg = noti.getBody();

        showToast("msgTitle : " + title + "\n" + "msg : " + msg + "\n");
    }

    /**
     * jhChoi - 20200918
     * PUSH가 100개를 초과하거나 한달이상 FCM과 연결되지 않은 경우 호출됩니다.
     */
    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.d(TAG, "onDeletedMessages");
    }

    /**
     * jhChoi - 20200915
     * toast를 출력합니다.
     */
    private void showToast(String msg) {
        Handler handler = new Handler(getMainLooper());
        handler.post(() -> Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show());
    }
}
