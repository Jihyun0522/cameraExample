package com.example.cameraexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 2;

    SurfaceView surfaceView;
    SurfaceHolder holder;
    MediaRecorder recorder;

    String path = "/sdcard/recorded_video.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permssionCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permssionRecord = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        if(permssionCamera != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 승인이 필요합니다.", Toast.LENGTH_LONG).show();

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "면접을 녹화하기 위해 카메라 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                Toast.makeText(this, "면접을 녹화하기 위해 카메라 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            }
        }

        if(permssionRecord != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 승인이 필요합니다.", Toast.LENGTH_LONG).show();

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(this, "면접을 녹화하기 위해 오디오 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
                Toast.makeText(this, "면접을 녹화하기 위해 오디오 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            }
        }

        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);

        holder = surfaceView.getHolder();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,"승인이 허가되어 있습니다.",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"아직 승인받지 않았습니다.",Toast.LENGTH_LONG).show();
                }
                return;
            }

            case MY_PERMISSIONS_REQUEST_RECORD_AUDIO: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,"승인이 허가되어 있습니다.",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"아직 승인받지 않았습니다.",Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

    public void onBtn1Clicked(View v){
        try {
            if(recorder != null){
                recorder.stop();
                recorder.release();
                recorder = null;
            }

            recorder = new MediaRecorder();

            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);

            recorder.setOutputFile(path);
            recorder.setPreviewDisplay(holder.getSurface());
            recorder.prepare();
            recorder.start();

            Toast.makeText(getApplicationContext(), "녹화를 시작합니다.", Toast.LENGTH_LONG).show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void onBut2Clicked(View v) {
        if(recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }

        Toast.makeText(getApplicationContext(), "녹화를 중지합니다.", Toast.LENGTH_LONG).show();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
