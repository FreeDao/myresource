package com.android.seting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.View.OnClickListener;


public class setting extends Activity {

	public static String[] array = { "ͼ������", "��������", "��������", "�߼�����",
		"�û�����", "��������"};	
	//SeekBar sb = (SeekBar)findViewById(R.id.seek_brightness);
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting); 
        /*sb.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //Do something
                }
            });*/
    }
	
	/*public OnSeekBarChangeListener sbLis=new OnSeekBarChangeListener(){
		 
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			//���ȸı�ʱ����
			//tv.setText(String.valueOf(sb.getProgress()));
 
		}
 
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// ��ʼ�϶�ʱ��������onProgressChanged��������onStartTrackingTouch��ֹͣ�϶�ǰֻ����һ��
			//��onProgressChangedֻҪ���϶����ͻ��ظ�����
		}
 
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			//�����϶�ʱ����
 
		}			
    };*/
	public void clickhandler(View v)
	{
		switch(v.getId())
		{
			case R.id.seek_brightness:
				System.out.println("Enter clickhandler");
				final TextView tv = (TextView)findViewById(R.id.text_brightness);
				final SeekBar sb = (SeekBar)findViewById(R.id.seek_brightness);
				
				 OnSeekBarChangeListener sbLis=new OnSeekBarChangeListener(){
					 
						@Override
						public void onProgressChanged(SeekBar seekBar, int progress,
								boolean fromUser) {
							//���ȸı�ʱ����
							tv.setText(String.valueOf(sb.getProgress()));
				 
						}
				 
						@Override
						public void onStartTrackingTouch(SeekBar seekBar) {
							// ��ʼ�϶�ʱ��������onProgressChanged��������onStartTrackingTouch��ֹͣ�϶�ǰֻ����һ��
							//��onProgressChangedֻҪ���϶����ͻ��ظ�����
						}
				 
						@Override
						public void onStopTrackingTouch(SeekBar seekBar) {
							//�����϶�ʱ����
				 
						}			
				    };
				    
				    //sb.setOnSeekBarChangeListener(sbLis);
				break;
			case R.id.seek_contrast:
				break;
			case R.id.seek_color:
				break;
			case R.id.seek_hue:
				break;
			case R.id.seek_sharpness:
				break;
			case R.id.bt_p1:
				System.out.println("click p1");
		}
	}

}
