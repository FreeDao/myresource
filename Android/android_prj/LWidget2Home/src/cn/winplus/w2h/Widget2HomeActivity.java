package cn.winplus.w2h;

import android.app.Activity;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;

import java.util.List;

public class Widget2HomeActivity extends Activity implements OnLongClickListener{

	
	private static final String TAG = "Widget2HomeActivity";
	
	private AppWidgetHost mAppWidgetHost;
	private AppWidgetManager mAppWidgetManager;
	private WidgetLayout mWidgetLayout;
	private Context context;
	private static final int APPWIDGET_HOST_ID = 0x100;
	private static final int REQUEST_PICK_APPWIDGET = 0;
	private static final int REQUEST_CREATE_APPWIDGET = 1;
	private static final String EXTRA_CUSTOM_WIDGET = "custom_widget";
	
	
	private Intent mBaseIntent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		context = getApplicationContext();

		mAppWidgetManager = AppWidgetManager.getInstance(context);
		mAppWidgetHost = new AppWidgetHost(context, APPWIDGET_HOST_ID);
		mAppWidgetHost.startListening(); 

		mWidgetLayout = (WidgetLayout)findViewById(R.id.widget_layouts);

		//mWidgetLayout.setOnLongClickListener(this);
		//setContentView(mWidgetLayout);
		//setWidget(200, 200, "com.android.browser");
		setWidget(200, 200, "com.example.myweatherwidget2");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case REQUEST_PICK_APPWIDGET:
				addAppWidget(data);
				break;
			case REQUEST_CREATE_APPWIDGET:
				completeAddAppWidget(data);
				break;
			}
		} else if (requestCode == REQUEST_PICK_APPWIDGET
				&& resultCode == RESULT_CANCELED && data != null) {
			int appWidgetId = data.getIntExtra(
					AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
			if (appWidgetId != -1) {
				mAppWidgetHost.deleteAppWidgetId(appWidgetId);
			}
		}
	}

	private void addAppWidget(Intent data) {
		int appWidgetId = data.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,-1);
		String customWidget = data.getStringExtra(EXTRA_CUSTOM_WIDGET);
		if ("search_widget".equals(customWidget)) {
			mAppWidgetHost.deleteAppWidgetId(appWidgetId);
		} else {
			AppWidgetProviderInfo appWidget = mAppWidgetManager
					.getAppWidgetInfo(appWidgetId);

			Log.d("addAppWidget", "configure:" + appWidget.configure);
			if (appWidget.configure != null) {
				// 弹出配置界面
				Intent intent = new Intent(
						AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
				intent.setComponent(appWidget.configure);
				intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
						appWidgetId);

				startActivityForResult(intent, REQUEST_CREATE_APPWIDGET);
			} else {
				// 直接添加到界面
				onActivityResult(REQUEST_CREATE_APPWIDGET, Activity.RESULT_OK,
						data);
			}
		}
	}

	

	/**
	 * 添加widget
	 * 
	 * @param data
	 */
	private void completeAddAppWidget(Intent data) {
		Bundle extras = data.getExtras();
		int appWidgetId = extras
				.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);

		AppWidgetProviderInfo appWidgetInfo = mAppWidgetManager
				.getAppWidgetInfo(appWidgetId);

		View hostView = mAppWidgetHost.createView(this, appWidgetId,
				appWidgetInfo);

		mWidgetLayout.addInScreen(hostView, appWidgetInfo.minWidth,
				appWidgetInfo.minHeight);
	}
	
	@Override
	protected void onDestroy() {
		try {
            mAppWidgetHost.stopListening();
        } catch (NullPointerException ex) {
            Log.i(TAG, "problem while stopping AppWidgetHost during Launcher destruction", ex);
        }
		super.onDestroy();
	}

	@Override
	public boolean onLongClick(View v) {
		Intent pickIntent = new Intent(AppWidgetManager.ACTION_APPWIDGET_PICK);
		int appWidgetId = mAppWidgetHost.allocateAppWidgetId();
		//appWidgetId =100;
		pickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		
		// start the pick activity
		startActivityForResult(pickIntent, REQUEST_PICK_APPWIDGET);
		
		
		
        // Read base intent from extras, otherwise assume default
 /*     Parcelable parcel = pickIntent.getParcelableExtra(Intent.EXTRA_INTENT);
        if (parcel instanceof Intent) {
            mBaseIntent = (Intent) parcel;
        } else {
            mBaseIntent = new Intent(Intent.ACTION_MAIN, null);
            mBaseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        }
		
    	AppWidgetProviderInfo appWidgetInfo=null;
	    List<AppWidgetProviderInfo> widgets =mAppWidgetManager.getInstalledProviders();
    for (AppWidgetProviderInfo widget : widgets) {
        if (widget.minWidth > 0 && widget.minHeight > 0 && widget.provider.getPackageName().equals("com.android.browser")) {
            appWidgetInfo = widget;
            break;
        } else {
            //Log.e(LOG_TAG, "Widget " + widget.provider + " has invalid dimensions (" +
                    //widget.minWidth + ", " + widget.minHeight + ")");
        }
    }
    if(appWidgetInfo !=null)
    {		//completeAddAppWidgets(appWidgetInfo);
        Intent intent = new Intent(mBaseIntent);
        
        if (appWidgetInfo.provider.getPackageName() != null && appWidgetInfo.provider.getClassName() != null) 
        {
            // Valid package and class, so fill details as normal intent
            intent.setClassName(appWidgetInfo.provider.getPackageName(), appWidgetInfo.provider.getClassName());
        }
        else {
            // No valid package or class, so treat as shortcut with label
            return false;
        }
        
        try {
        	//boolean done = mAppWidgetManager.bindAppWidgetIdIfAllowed(appWidgetId, intent.getComponent());
        	//if(!done)
        	{
        		//return false;
        	}
            mAppWidgetManager.bindAppWidgetId(appWidgetId, intent.getComponent());
        } catch (IllegalArgumentException e) {
            return false;
        }
        
        Intent result = new Intent();
        result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        addAppWidget(result);
    }*/
		return false;
	}
	
		private void completeAddAppWidgets(AppWidgetProviderInfo appWidgetInfo) {
		//Bundle extras = data.getExtras();
		//int appWidgetId = extras
				//.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);

		//AppWidgetProviderInfo appWidgetInfo = mAppWidgetManager
				//.getAppWidgetInfo(appWidgetId);

		View hostView = mAppWidgetHost.createView(this, 100,
				appWidgetInfo);

		mWidgetLayout.addInScreen(hostView, appWidgetInfo.minWidth,
				appWidgetInfo.minHeight);
	}
		
		
		
		
		
		
		
		
		public boolean setWidget(int x, int y, String packageName) {
			Log.d("widget", "11");
			mWidgetLayout.setTheXY(x, y);

			 /*Intent pickIntent = new
			 Intent(AppWidgetManager.ACTION_APPWIDGET_PICK); int appWidgetId =
			 mAppWidgetHost.allocateAppWidgetId(); //appWidgetId =100;
			 pickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
			 appWidgetId);
	         // start the pick activity 
			 startActivityForResult(pickIntent, 0);*/


			
			Intent pickIntent = new Intent(AppWidgetManager.ACTION_APPWIDGET_PICK);
			int appWidgetId = mAppWidgetHost.allocateAppWidgetId();
			pickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			Parcelable parcel = pickIntent.getParcelableExtra(Intent.EXTRA_INTENT);
			if (parcel instanceof Intent) {
				mBaseIntent = (Intent) parcel;
			} else {
				mBaseIntent = new Intent(Intent.ACTION_MAIN, null);
				mBaseIntent.addCategory(Intent.CATEGORY_DEFAULT);
			}

			AppWidgetProviderInfo appWidgetInfo = null;
			List<AppWidgetProviderInfo> widgets = mAppWidgetManager
					.getInstalledProviders();
			for (AppWidgetProviderInfo widget : widgets) {
				if (widget.minWidth > 0 && widget.minHeight > 0
						&& widget.provider.getPackageName().equals(packageName)) {
					appWidgetInfo = widget;
					break;
				} else {
					Log.e("LOG_TAG", "Widget " + widget.provider
							+ " has invalid dimensions (" + widget.minWidth + ", "
							+ widget.minHeight + ")");
				}
			}
			Log.d("widget", "12");
			if (appWidgetInfo != null) {
				Intent intent = new Intent(mBaseIntent);

				if (appWidgetInfo.provider.getPackageName() != null
						&& appWidgetInfo.provider.getClassName() != null) {
					// Valid package and class, so fill details as normal intent
					intent.setClassName(appWidgetInfo.provider.getPackageName(),
							appWidgetInfo.provider.getClassName());
				} else {
					// No valid package or class, so treat as shortcut with label
					return false;
				}
				Log.d("widget", "befer bind");
				try {
					// boolean done =
					// mAppWidgetManager.bindAppWidgetIdIfAllowed(appWidgetId,
					// intent.getComponent());
					// if(!done)
					{
						// return false;
					}
					mAppWidgetManager.bindAppWidgetId(appWidgetId,
							intent.getComponent());
					Log.d("widget", "after bind");
				} catch (IllegalArgumentException e) {
					Log.d("widget", "error");
					return false;
				}

				Intent result = new Intent();
				result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
				addAppWidget(result);
			}
			
			return false;
		}
}