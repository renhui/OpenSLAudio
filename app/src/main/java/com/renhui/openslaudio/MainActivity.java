package com.renhui.openslaudio;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.FileInputStream;
import java.io.InputStream;

public class MainActivity extends Activity {

    static {
        System.loadLibrary("native-lib");
    }

    AssetManager assetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assetManager = getAssets();
    }

    public native void playAudioByOpenSL_assets(AssetManager assetManager, String filename);

    public native void playAudioByOpenSL_pcm(String pamPath);


    // 测试m4a格式
    // public void play_assets(View view) {
    //     playAudioByOpenSL_assets(assetManager, "mydream.m4a");
    // }

    public void play_assets(View view) {
        playAudioByOpenSL_assets(assetManager, "shy.mp3");
    }


    public void play_pcm(View view) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/111.pcm";
        playAudioByOpenSL_pcm(path);
    }


    /**
     * java层提供pcm数据，opensl底层播放
     * <p>
     * 只是演示播放方式，停止回收资源逻辑自行设计
     *
     * @param data
     * @param size
     */
    public native void sendPcmData(byte[] data, int size);

    boolean isstart = false;

    public void play_javapcm(View view) {

        if (!isstart) {
            isstart = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream in = new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/111.pcm");
                        byte[] buffer = new byte[44100 * 2 * 2];
                        int n = 0;
                        while ((n = in.read(buffer)) != -1) {
                            sendPcmData(buffer, n);
                            Thread.sleep(800);
                        }
                        isstart = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }
}
