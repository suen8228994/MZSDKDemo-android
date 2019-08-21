package com.mengzhu.live.sdk.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.mengzhu.live.sdk.business.view.widgets.MZVideoView;
import com.mengzhu.live.sdk.core.MZSDKInitManager;
import com.mengzhu.live.sdk.core.SDKInitListener;
import com.mengzhu.live.sdk.ui.player.IMZPlayerManager;

/**
 * Created by DELL on 2018/10/12.
 */
public class TestActivity extends Activity implements SDKInitListener{
    private MZVideoView video_view;
    private Switch live_back_switch;
    private EditText live_url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        MZSDKInitManager.getInstance().initLive("zGLygo_YbOSuWcOHJBZ8VtrsLMs9_ba705e83a11b36348f838cb34187827fe3398ab5");
        MZSDKInitManager.getInstance().registerInitListener(this);
        video_view=findViewById(R.id.video_view);
        video_view.setVideoPath("http://vod.dev.zmengzhu.com/record/base/hls-sd/042dca9d63ae07d300006491.m3u8");
        live_back_switch=findViewById(R.id.live_back_switch);
        live_url=findViewById(R.id.live_url);
    }
    public  void onPlayClick(View view){
        Intent intent=new Intent(this,PlayerActivity.class);
        intent.putExtra("TYPE",live_back_switch.isChecked()? IMZPlayerManager.TYPE_LIVE:IMZPlayerManager.TYPE_VIDEO);
        if(live_url.getText()!=null&&!live_url.getText().equals("")) {
            intent.putExtra("live_URL", live_url.getText().toString());
        }
        startActivity(intent);
    }

    public void onLiveClick(View view){
        startActivity(new Intent(this,LiveActivity.class));

    }

    @Override
    public void dataResult(int status) {
        video_view.start();
        Toast.makeText(this,"初始化成功 ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorResult(int code, String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
