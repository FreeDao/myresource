/*
 * PhoneGap is available under *either* the terms of the modified BSD license *or* the
 * MIT License (2008). See http://opensource.org/licenses/alphabetical for full text.
 *
 * Copyright (c) 2005-2010, Nitobi Software Inc.
 * Copyright (c) 2011, IBM Corporation
 */

package org.apache.cordova.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;

public class VideoPlayer extends Plugin {
    private static final String YOU_TUBE = "youtube.com";
    private static final String ASSETS = "file:///android_asset/";

	private SurfaceHolder holder=null;	
	private MediaPlayer player = null;
	
	VideoSink mSelectedVideoSink;
	VideoSink mJavaMediaPlayerVideoSink;
	VideoSink mNativeMediaPlayerVideoSink;
	SurfaceHolderVideoSink mSurfaceHolder1VideoSink;
	
    @Override
    public PluginResult execute(String action, JSONArray args, String callbackId) {
        PluginResult.Status status = PluginResult.Status.OK;
        String result = "";

        try {
            if (action.equals("playVideo")) {
                playVideo(args.getString(0));
            }
            else {
                status = PluginResult.Status.INVALID_ACTION;
            }
            return new PluginResult(status, result);
        } catch (JSONException e) {
            return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
        } catch (IOException e) {
            return new PluginResult(PluginResult.Status.IO_EXCEPTION);
        }
    }

    private void playVideo(String url) throws IOException {
    	Log.e("","playVideo error");
        // Create URI
        Uri uri = Uri.parse(url);

        Intent intent = null;
        // Check to see if someone is trying to play a YouTube page.
        if (url.contains(YOU_TUBE)) {
            // If we don't do it this way you don't have the option for youtube
            uri = Uri.parse("vnd.youtube:" + uri.getQueryParameter("v"));
            intent = new Intent(Intent.ACTION_VIEW, uri);
        } else if(url.contains(ASSETS)) {
            // get file path in assets folder
            String filepath = url.replace(ASSETS, "");
            // get actual filename from path as command to write to internal storage doesn't like folders
            String filename = filepath.substring(filepath.lastIndexOf("/")+1, filepath.length());

            // Don't copy the file if it already exists
            File fp = new File(this.cordova.getActivity().getFilesDir() + "/" + filename);
            if (!fp.exists()) {
                this.copy(filepath, filename);
            }

            // change uri to be to the new file in internal storage
            uri = Uri.parse("file://" + this.cordova.getActivity().getFilesDir() + "/" + filename);

            // Display video player
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "video/*");

        } else {
            // Display video player
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "video/*");
        }

        //this.cordova.getActivity().startActivity(intent);
        play("/mnt/sdcard/videoplayback.m3u8");
    }

    private void copy(String fileFrom, String fileTo) throws IOException {
        // get file to be copied from assets
        InputStream in = this.cordova.getActivity().getAssets().open(fileFrom);
        // get file where copied too, in internal storage.
        // must be MODE_WORLD_READABLE or Android can't play it
        FileOutputStream out = this.cordova.getActivity().openFileOutput(fileTo, Context.MODE_WORLD_READABLE);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0)
            out.write(buf, 0, len);
        in.close();
        out.close();
    }
    
    private void play(String path)
    {
    	Log.e("","going to play");
    	/*if(player == null)
    		player = new MediaPlayer();
    	
		holder = (SurfaceHolder) cordovaExample.suf.getHolder();
		//Ҫ��SurfaceView���󲻱�ȥά���Լ��Ļ���
		//holder.setFixedSize(176,144);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		holder.addCallback(new SurfaceCallback());
		try{
		player.reset();//��֤player����idle״̬
		//������Ƶ������
		//player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		//�趨����Դ����������ý���ļ�			
		//player.setDataSource(videoFile.getAbsolutePath());
		player.setDataSource("http://172.29.52.33/easley_li/101/videoplayback.m3u8");
		//����Ƶ���潻��holder��ʾ������Ƶ�surface����֮��
		player.setDisplay(holder);
		//��playerת��prepared״̬
		player.prepare();
		//����
		player.start();
		Log.e("","Playing");
		}
		catch(Exception e)
		{
			Log.e("","Play failed");
		}*/
    	
    	createEngine();
    	Log.e("","createEngine done");
		holder = (SurfaceHolder) cordovaExample.suf.getHolder();
		//Ҫ��SurfaceView���󲻱�ȥά���Լ��Ļ���
		//holder.setFixedSize(176,144);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		holder.addCallback(new SurfaceCallback());
		
			if (mSurfaceHolder1VideoSink == null) {
				mSurfaceHolder1VideoSink = new SurfaceHolderVideoSink(
						holder);
			}
			mSelectedVideoSink = mSurfaceHolder1VideoSink;
		if (mNativeMediaPlayerVideoSink == null) {
			if (mSelectedVideoSink == null) {
				return;
			}
			mSelectedVideoSink.useAsSinkForNative();
			mNativeMediaPlayerVideoSink = mSelectedVideoSink;
		}
		Log.e("","createStreamingMediaPlayer bf");
			//Parser.getFileList(mSourceString);
		createStreamingMediaPlayer(path);

    }
    
	private class SurfaceCallback implements Callback{

		//��Surface��״̬����С�͸�ʽ�������仯��ʱ�����øú�����
		//��surfaceCreated���ú�ú������ٻᱻ����һ��
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			Log.i("TAG", "surfaceChanged ...");
		}

		/*
		 * ��Surface��һ�δ�������������øú�����
		 * ��������ڸú�������Щ�ͻ��ƽ�����صĳ�ʼ��������
		 * һ������¶�����������߳������ƽ��棬���Բ�Ҫ����������л���Surface��
		 * 
		 * �ڴ������滻onResume����
		 */
		public void surfaceCreated(SurfaceHolder holder) {
			/*if(position > 0 && videoFile != null){
				try {
					playVideo(videoFile);
					player.seekTo(position);
					position = 0;
				} catch (IOException e) {
					Log.e("TAG", e.toString());
							
				}			
			}*/			

		}

		//����ʱ������һ�������ｫ��ͼ���߳�ֹͣ���ͷš�
		//�����滻onPause����
		public void surfaceDestroyed(SurfaceHolder holder) {
			if(player.isPlaying()){
				//position = player.getCurrentPosition();
				player.stop();
			}
			Log.i("TAG", "surfaceDestroyed ...");
		}
		
	}
	
	
	/** Native methods, implemented in jni folder */
	public static native void createEngine();

	public static native boolean createStreamingMediaPlayer(String filename);

	public static native boolean setFile(ArrayList<String> list);
	
	public static native boolean setFilename(String filename);

	public static native void setPlayingStreamingMediaPlayer(boolean isPlaying);

	public static native void shutdown();
	
	public static native void reset(String filename);

	public static native void setSurface(Surface surface);

	public static native void rewindStreamingMediaPlayer();


	static {
		System.loadLibrary("native-media-jni");
	}
	public static void getFile(int n) {
		//setFile(Parser.getAll());
		setFilename("");
	}
	
	
	
	
	
	
	// VideoSink abstracts out the difference between Surface and SurfaceTexture
	// aka SurfaceHolder and GLSurfaceView
	static abstract class VideoSink {

		abstract void setFixedSize(int width, int height);

		//abstract void useAsSinkForJava(MediaPlayer mediaPlayer);

		abstract void useAsSinkForNative();

	}

	static class SurfaceHolderVideoSink extends VideoSink {

		private final SurfaceHolder mSurfaceHolder;

		SurfaceHolderVideoSink(SurfaceHolder surfaceHolder) {
			mSurfaceHolder = surfaceHolder;
		}

		void setFixedSize(int width, int height) {
			mSurfaceHolder.setFixedSize(width, height);
		}

		/*void useAsSinkForJava(MediaPlayer mediaPlayer) {
			// Use the newer MediaPlayer.setSurface(Surface) since API level 14
			// instead of MediaPlayer.setDisplay(mSurfaceHolder) since API level
			// 1,
			// because setSurface also works with a Surface derived from a
			// SurfaceTexture.
			Surface s = mSurfaceHolder.getSurface();
			mediaPlayer.setSurface(s);
			s.release();
		}*/

		void useAsSinkForNative() {
			Surface s = mSurfaceHolder.getSurface();
			//myholder = mSurfaceHolder;
			setSurface(s);
			s.release();
		}

	}

}
