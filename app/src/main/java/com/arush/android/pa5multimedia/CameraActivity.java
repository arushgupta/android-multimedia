package com.arush.android.pa5multimedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class CameraActivity extends AppCompatActivity {

    ImageView imageView;
    VideoView videoView;
    TextView textView;
    static Uri uriVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.imageView);
        videoView = (VideoView) findViewById(R.id.videoView);
        textView = (TextView) findViewById(R.id.textView3);

    }
    private final int MY_VIDEO_REQUEST_CODE = 1;
    private final int MY_CAMERA_REQUEST_CODE = 2;

    public void doTakePhotoButton(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, MY_CAMERA_REQUEST_CODE);
    }

    public void doRecordVideo(View view) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, MY_VIDEO_REQUEST_CODE);
    }

    public void doPlayVideo(View view) {
        if (uriVideo == null) {
            Toast.makeText(this, "No video to play", Toast.LENGTH_LONG).show();
            return;
        }
        videoView.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) return;
            if (data == null) return;
            Bitmap bitmap = data.getParcelableExtra("data");
            imageView.setImageBitmap(bitmap);
            return;
        }
        else {
            if (requestCode == MY_VIDEO_REQUEST_CODE) {
                if (resultCode == RESULT_CANCELED) return;
                if (data == null) return;
                uriVideo = data.getData();
                videoView.setVideoURI(uriVideo);
                textView.setText(uriVideo.toString());
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
