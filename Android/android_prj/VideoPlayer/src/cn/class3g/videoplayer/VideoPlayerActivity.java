package cn.class3g.videoplayer;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class VideoPlayerActivity extends Activity {

	private ImageButton playIBtn, pauseIBtn, resetIBtn, stopIBtn,playIBtn2,playIBtn3,playIBtn4;
	private SurfaceView videoSv;
	
	private String videoFileName = null;
	private File videoFile = null;
	
	private SurfaceHolder holder=null;
	
	private MediaPlayer player = null;
	
	private int position = 0;
	

	private GridView gridView;
	private SlidingDrawer slidingDrawer;
	private ImageView imageView;
	private int[] icons={
		R.drawable.title1, R.drawable.title2,
		R.drawable.title3, R.drawable.title4,
		R.drawable.title5, R.drawable.title6
	};
	
	private String[] items={
		"Phone", "Message", "AddImage", "Music", "Telephone", "SMS"	
	};
	
	private final static String url1 ="http://127.0.0.1:5657/play?" +
			"url='vlive://121.10.173.122:3110/live/cid=1&" +
			"name=cctv1&oemid=634&hid=bc83a761fba1&uid=1234'";
	//private final static String url1 ="/mnt/udisk/sda1/igun.mp4";
	//private final static String url2 ="/mnt/udisk/sda1/igun2.mp4";
	private final static String url2 ="http://127.0.0.1:5657/play?" +
			"url='vlive://121.10.173.122:3110/live/cid=9&" +
			"name=cctv5&oemid=634&hid=bc83a761fba1&uid=1234'";
	private final static String url3 ="http://127.0.0.1:5657/play?" +
			"url='vlive://121.10.173.122:3110/live/cid=10&" +
			"name=cctv6&oemid=634&hid=bc83a761fba1&uid=1234'";
	private final static String url4 ="http://127.0.0.1:5657/play?" +
			"url='vlive://121.10.173.122:3110/live/cid=18&" +
			"name=����&oemid=634&hid=bc83a761fba1&uid=1234'";
	String url = null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        player = new MediaPlayer();//��ʱplayer����idle״̬
        
        init();
        //initDrawer();
    }


	private void init() {
		
		playIBtn = (ImageButton)findViewById(R.id.playIBtn);
		playIBtn2 = (ImageButton)findViewById(R.id.playIBtn2);
		playIBtn3 = (ImageButton)findViewById(R.id.playIBtn3);
		playIBtn4 = (ImageButton)findViewById(R.id.playIBtn4);
		pauseIBtn = (ImageButton)findViewById(R.id.pauseIBtn);
		resetIBtn = (ImageButton)findViewById(R.id.resetIBtn);
		stopIBtn = (ImageButton)findViewById(R.id.stopIBtn);
		
		videoSv = (SurfaceView)findViewById(R.id.videoSv);
		
		//ȡ��SurfaceView�����е�Surface�Ŀ�����
		holder = (SurfaceHolder) videoSv.getHolder();
		//Ҫ��SurfaceView���󲻱�ȥά���Լ��Ļ���
		holder.setFixedSize(176,144);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		holder.addCallback(new SurfaceCallback());

		
		
		MyListener listener = new MyListener();
		
		playIBtn.setOnClickListener(listener);
		playIBtn2.setOnClickListener(listener);
		playIBtn3.setOnClickListener(listener);
		playIBtn4.setOnClickListener(listener);
		pauseIBtn.setOnClickListener(listener);
		resetIBtn.setOnClickListener(listener);
		stopIBtn.setOnClickListener(listener);
				
		
	}
	
	private class MyListener implements View.OnClickListener{

		public void onClick(View v) {
			try {
				doWork(v);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
			
		}

		private void doWork(View v) throws Exception {
			switch(v.getId()){
			case R.id.playIBtn:
				playVideo(url1);
				url = url1;
				break;
			case R.id.playIBtn2:
				playVideo(url2);
				url = url2;
				break;
			case R.id.playIBtn3:
				playVideo(url3);
				url = url3;
				break;
			case R.id.playIBtn4:
				playVideo(url4);
				url = url4;
				break;
			case R.id.pauseIBtn:
				if(player.isPlaying()){
					player.pause();
				}else{
					player.start();
				}
				break;
			case R.id.resetIBtn:
				player.reset();
				/*if(player.isPlaying()){
					player.seekTo(0);
				}else{
					playVideo("");
				}*/
				break;
			case R.id.stopIBtn:
				//if(player.isPlaying()){
					player.reset();
				//}
				break;
			}
		}


		
	}

	private void playVideo(String url) throws IOException {
		player.reset();//��֤player����idle״̬
		//������Ƶ������
		//player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		//�趨����Դ����������ý���ļ�			
		//player.setDataSource("http://devimages.apple.com/iphone/" +
				//"samples/bipbop/gear1/prog_index.m3u8");
		player.setDataSource(url);
		//����Ƶ���潻��holder��ʾ������Ƶ�surface����֮��
		player.setDisplay(holder);
		//��playerת��prepared״̬
		player.prepare();
		//����
		player.start();
		//int speed = player.getDownloadSpeed();
		//Log.e("speed", speed +"");
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
			if(position > 0 && url != null){
				try {
					playVideo(url);
					player.seekTo(position);
					position = 0;
				} catch (IOException e) {
					Log.e("TAG", e.toString());
							
				}				
			}			
			Log.i("TAG", "surfaceCreated ...");
		}

		//����ʱ������һ�������ｫ��ͼ���߳�ֹͣ���ͷš�
		//�����滻onPause����
		public void surfaceDestroyed(SurfaceHolder holder) {
			if(player.isPlaying()){
				position = player.getCurrentPosition();
				player.stop();
			}
			Log.i("TAG", "surfaceDestroyed ...");
		}
		
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		
		position  = savedInstanceState.getInt("position");
		//videoFileName = savedInstanceState.getString("fileName");
		
		if(url != null){
			//videoFile = new File(Environment.getExternalStorageDirectory(), videoFileName); 
			try {
				playVideo(url);
				player.seekTo(position);
				position = 0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		super.onRestoreInstanceState(savedInstanceState);
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
		outState.putInt("positon", position);
		outState.putString("fileName", videoFileName);
		
		super.onSaveInstanceState(outState);
	}
	
	
	
	/*
	protected void onPause() {
		if(player.isPlaying()){
			player.pause();
		}		
		super.onPause();
	}


	@Override
	protected void onResume() {
		if(!player.isPlaying()){
			player.start();
		}
		super.onResume();
	}*/
	
	
	private void initDrawer()
	{
        /*gridView = (GridView)findViewById(R.id.mycontent);
        slidingDrawer = (SlidingDrawer)findViewById(R.id.sliding_drawer);
        imageView = (ImageView)findViewById(R.id.my_image);
        MyGridViewAdapter adapter = new MyGridViewAdapter(this, items, icons);
        gridView.setAdapter(adapter);
        slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
			
			public void onDrawerOpened() {
				imageView.setImageResource(R.drawable.down1);
			}
		});
        slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
			
			public void onDrawerClosed() {
				imageView.setImageResource(R.drawable.up1);
			}
		});*/
	}
	
}