package com.ichliebephone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class AndroidPreferenceDemoII extends Activity {
    /** Called when the activity is first created. */
	// 菜单项
	final private int menuSettings=Menu.FIRST;
	private static final int REQ_SYSTEM_SETTINGS = 0;  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
		// 建立菜单
    	menu.add(0, menuSettings, 2, "设置");
		return super.onCreateOptionsMenu(menu);
    }
    //菜单选择事件处理
    @Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
    	switch (item.getItemId())
    	{
	    	case menuSettings:
	    		//转到Settings设置界面
	    		startActivityForResult(new Intent(this, Settings.class), REQ_SYSTEM_SETTINGS);
	    		break;
	    	default:
	    		break;
    	}
    	return super.onMenuItemSelected(featureId, item);
    }
    //Settings设置界面返回的结果
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQ_SYSTEM_SETTINGS)
		{
			//获取设置界面PreferenceActivity中各个Preference的值
	        String updateSwitchKey = getResources().getString(R.string.auto_update_switch_key);
	        String updateFrequencyKey = getResources().getString(R.string.auto_update_frequency_key);
	        //取得属于整个应用程序的SharedPreferences
			SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
			Boolean updateSwitch = settings.getBoolean(updateSwitchKey, true);
			String updateFrequency = settings.getString(updateFrequencyKey, "10");
			//打印结果
			Log.v("CheckBoxPreference_Main", updateSwitch.toString());
			Log.v("ListPreference_Main", updateFrequency);
		}
		else
		{
			//其他Intent返回的结果
		}
    }
}