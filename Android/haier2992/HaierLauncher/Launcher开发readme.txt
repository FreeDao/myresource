���������еľ�����ܽᣬ��������ӣ�

1.��launcher��Դ�뵼��Eclipseʱ�ᱨ���ܶ������Ҫadd library:android-common,android-core,android-framework,android-support-v13;

2.add library���裺�Ҽ�->Build Path->Add Librarys->User Library->User libraries->New...;
  a.ics_537312\out\target\common\obj\JAVA_LIBRARIES\android-common_intermediates
  b.ics_537312\out\target\common\obj\JAVA_LIBRARIES\core_intermediates
  c.ics_537312\out\target\common\obj\JAVA_LIBRARIES\framework_intermediates
  d.ics_537312\out\target\common\obj\JAVA_LIBRARIES\android-support-v13_intermediates

3.launcher�ı�����ics�Ĺ��̻�����ʹ��mm������룬��Ҫʹ��ecplipse���룬��������

4.����launcher����������������ͻ��ϵͳ�Դ���apk�����޷���װ����װʱ����ʹ��adb install Launcher.apk;

5.launcher�����������裺
  a.com.android.launcher2�Ҽ�->Refactor->Rename...->�ĸ���ѡ�򹳣�File name patterns:*.xml
  b.����ʵ����Ҫ�ֶ��޸�AndroidManifest.xml�ļ���
  c.����ʵ����Ҫ�ֶ��޸�/res/layout*/*.xml�е�com.android.launcher2.xxx��
  d.����ʵ����Ҫ�ֶ��޸�/res/layout*/*.xml�е�xmlns:launcher="http://schemas.android.com/apk/res/com.android.launcher"
  e.����ʵ����Ҫ�ֶ��޸�java���������import com.android.launcher.R;

6.�鿴launcher���й����еĴ�ӡ�����logcat -s Launcher:D Launcher.workspace:D;

7.�ڰ�װwidgetʱ����˵Ȩ�޲�����
java.lang.SecurityException: bindGagetId appWidgetId=8 provider=ComponentInfo{com.android.deskclock/com.android.alarmclock.AnalogAppWidgetProvider}: User 10044 does not have android.permission.BIND_APPWIDGET.
����Ҫ�����Launcher2.apk�� push��/System/app���������;

   
   