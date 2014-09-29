package cn.m15.xys;




import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SimpleActivity extends Activity {

    /**���Ŷ�����ť**/
    Button button0 = null;
  
    /**ֹͣ������ť**/
    Button button1 = null;
    
    /**���ö���ѭ��ѡ���**/
    RadioButton radioButton0= null;
    RadioButton radioButton1= null;
    RadioGroup  radioGroup = null;
  
    /**�϶�ͼƬ�޸�Alphaֵ**/
    SeekBar seekbar = null;
  
    /**���ƶ���View**/
    ImageView imageView = null;
   
    /**���ƶ�������**/
    AnimationDrawable animationDrawable = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.simple);

	/**�õ�ImageView����**/
	imageView = (ImageView)findViewById(R.id.imageView);
	/**ͨ��ImageView�����õ�������ʾ��AnimationDrawable**/
	animationDrawable = (AnimationDrawable) imageView.getBackground();
	
	
	/**��ʼ���Ŷ���**/
	button0 = (Button)findViewById(R.id.button0);
	button0.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		/**���Ŷ���**/
		if(!animationDrawable.isRunning()) {
		    animationDrawable.start();
		}
	    }
	});
	
	/**ֹͣ���Ŷ���**/
	button1 = (Button)findViewById(R.id.button1);
	button1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		/**ֹͣ����**/
		if(animationDrawable.isRunning()) {
		    animationDrawable.stop();
		}
	    }
	});
	/**���β���**/
	radioButton0 = (RadioButton)findViewById(R.id.checkbox0);
	/**ѭ������**/
	radioButton1 = (RadioButton)findViewById(R.id.checkbox1);
	/**��ѡ�б���**/
	radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
	radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
	    
	    @Override
	    public void onCheckedChanged(RadioGroup radioGroup, int checkID) {
		if(checkID == radioButton0.getId()) {
		    //���õ��β���
		    animationDrawable.setOneShot(true);
		}else if (checkID == radioButton1.getId()) {
		    //����ѭ������
		    animationDrawable.setOneShot(false);
		}
		
		//�����ı���ö������²���
		animationDrawable.stop();
		animationDrawable.start();
	    }
	});
	
	/**�����Ľ������޸�͸����**/
	seekbar = (SeekBar)findViewById(R.id.seekBar);
	seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
	    @Override
	    public void onStopTrackingTouch(SeekBar seekBar) {
		
	    }
	    @Override
	    public void onStartTrackingTouch(SeekBar seekBar) {
		
	    }
	    @Override
	    public void onProgressChanged(SeekBar seekBar, int progress, boolean frameTouch) {
		/**���ö���Alphaֵ**/
		animationDrawable.setAlpha(progress);
		/**֪ͨimageView ˢ����Ļ**/
		imageView.postInvalidate();
	    }
	});
	
    }
}