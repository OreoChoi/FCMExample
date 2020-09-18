package com.kcejjh.gcmexample.Activity;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 2020/09/15 - jhChoi
 * 공통으로 사용되는 액티비티입니다.
 * */
public class BaseActivity extends AppCompatActivity {

    /**
     * 2020/09/15 - jhChoi
     * 토스트 메세지를 출력합니다.
     * */
    public void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
