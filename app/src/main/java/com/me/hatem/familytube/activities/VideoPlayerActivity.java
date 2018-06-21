package com.me.hatem.familytube.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.rtoshiro.view.video.FullscreenVideoLayout;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.MimeTypes;
import com.me.hatem.familytube.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import static android.os.Build.VERSION_CODES.M;

public class VideoPlayerActivity extends AppCompatActivity {



  SimpleExoPlayerView videoView;
  SimpleExoPlayer simpleExoPlayer;
  Uri videoURL,subtitleURI;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_video_player);


    /* Retrieve data */
    videoURL      = Uri.parse(getIntent().getExtras().getString("videoURL"));
    subtitleURI   = Uri.parse(getIntent().getExtras().getString("subtitleURI"));
    ////////////////
    videoView     = (SimpleExoPlayerView) findViewById(R.id.videoView);
    videoLoader();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    /*if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
      simpleExoPlayer.release();*/
    // Checks the orientation of the screen
   /* if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
      Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
    }*/

  }

  public void videoLoader() {
    // for the exoPlayer setup
    // 1. Create a default TrackSelector
    Handler mainHandler = new Handler();
    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
    TrackSelection.Factory videoTrackSelectionFactory =
      new AdaptiveTrackSelection.Factory(bandwidthMeter);
    TrackSelector trackSelector =
      new DefaultTrackSelector(videoTrackSelectionFactory);

    simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

    DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer");
    ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
    MediaSource mediaSource = new ExtractorMediaSource(videoURL,defaultHttpDataSourceFactory,
      extractorsFactory,null,null);

    // for subtitle
    Format subtitleFormat = Format.createTextSampleFormat(null, MimeTypes.APPLICATION_SUBRIP,
      null, Format.NO_VALUE, Format.NO_VALUE, "ar", null);
    MediaSource subtitleSource = new SingleSampleMediaSource(
      subtitleURI,
      defaultHttpDataSourceFactory,
      subtitleFormat,
      C.TIME_UNSET);
    // Plays the video with the sideloaded subtitle.
    MergingMediaSource mergedSource =
      new MergingMediaSource(mediaSource, subtitleSource);
    // play the marge video and subtitle
    videoView.setPlayer(simpleExoPlayer);
    simpleExoPlayer.prepare(mergedSource);
    simpleExoPlayer.setPlayWhenReady(true);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    simpleExoPlayer.release(); // release the video
    //this.finish();
  }
}
