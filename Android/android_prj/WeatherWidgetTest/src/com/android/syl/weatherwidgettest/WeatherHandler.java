package com.android.syl.weatherwidgettest;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class WeatherHandler extends DefaultHandler {
    private final static String TAG = "WeatherHandler_syl";
    public String[] currentWeather;
    
    private boolean in_forecast_information = false;
    private boolean in_current_conditions = false;
    private boolean in_forecast_conditions = false;
    
    private boolean usingSITemperature = false; // falseΪ���϶ȣ�trueΪ���϶�

    // ��ʼ��������
    @Override
    public void startDocument() {
        currentWeather = new String[6];
    }

    // ������������
    @Override
    public void endDocument() {
    }

    // �����ڵ�ͷʱ����
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) {
        // �ж����ڽ���ʲô�ڵ㣬
//        String attr = attributes.getValue("data");
//        if (localName.endsWith("wind_condition")) {
//            currentWeather[5] = attr;
//            Log.v(TAG, "wind_condition: " + attr);
//        } else if (localName.endsWith("condition")) {
//            currentWeather[0] = "��ǰ����:" + attr;
//            Log.d("localName", attr);
//        } else if (localName.endsWith("temp_f")) {
//            currentWeather[1] = "temp_f��" + attr;
//            Log.v(TAG, currentWeather[1]);
//        } else if (localName.endsWith("temp_c")) {
//            currentWeather[2] = "�¶ȣ�" + attr;
//            Log.v(TAG, currentWeather[2]);
//        } else if (localName.endsWith("humidity")) {
//            currentWeather[3] = attr;
//            Log.v(TAG, "humidity: " + currentWeather[3]);
//        } else if (localName.endsWith("icon")) {
//            currentWeather[4] = attr;
//            Log.v(TAG, "icon" + currentWeather[4]);
//        }
    	if (localName.equals("forecast_information")) {
            this.in_forecast_information = true;
       } else if (localName.equals("current_conditions")) {
            this.in_current_conditions = true;
       } else if (localName.equals("forecast_conditions")) {
            this.in_forecast_conditions = true;
       } else {
            String dataAttribute = attributes.getValue("data");
            // 'Inner' Tags of "<forecast_information>"
            if (localName.equals("city")) {
            } else if (localName.equals("postal_code")) {
            } else if (localName.equals("latitude_e6")) {
                 /* One could use this to convert city-name to Lat/Long. */
            } else if (localName.equals("longitude_e6")) {
                 /* One could use this to convert city-name to Lat/Long. */
            } else if (localName.equals("forecast_date")) {
            } else if (localName.equals("current_date_time")) {
            } else if (localName.equals("unit_system")) {
            	if (dataAttribute.equals("SI")) {
                    this.usingSITemperature = true;
            	}
            }
            // SHARED(!) 'Inner' Tags within "<current_conditions>" AND
            // "<forecast_conditions>"
            else if (localName.equals("day_of_week")) {
                 if (this.in_current_conditions) {
                     //����չ
                 } else if (this.in_forecast_conditions) {
                     //����չ
                 }
            } else if (localName.equals("icon")) {
                 if (this.in_current_conditions) {
                   currentWeather[4] = dataAttribute;
                   Log.v(TAG, "icon" + currentWeather[4]);

                 } else if (this.in_forecast_conditions) {
                 	//����չ
                 }
            } else if (localName.endsWith("temp_f")) {
            	if (this.in_current_conditions) {
                    currentWeather[1] = "temp_f��" + dataAttribute;
                    Log.v(TAG, currentWeather[1]);
            	} else if (this.in_forecast_conditions) {
            		// ����չ
            	}
            } else if (localName.equals("condition")) {
                 if (this.in_current_conditions) {
                	 currentWeather[0] = "��ǰ����:" + dataAttribute;
                     Log.d("localName", dataAttribute);
                 } else if (this.in_forecast_conditions) {
                 	//����չ
                 }
            } else if (localName.endsWith("wind_condition")) {
            	if (this.in_current_conditions) {
                    currentWeather[5] = dataAttribute;
                    Log.v(TAG, "wind_condition: " + dataAttribute);
            	} else if (this.in_forecast_conditions) {
                 	//����չ
                 }
            }
            // 'Inner' Tags within "<current_conditions>"
            else if (localName.equals("temp_f")) {
                 //this.setCurrentTemp(Integer.parseInt(dataAttribute));
            } else if (localName.equals("temp_c")) {
//         	    this.setCurrentTemp(Integer.parseInt(dataAttribute));
            	currentWeather[2] = "�¶ȣ�" + dataAttribute;
                Log.v(TAG, currentWeather[2]);
            } else if (localName.equals("humidity")) {
//                 this.setCurrentHum(dataAttribute);
            	currentWeather[3] = dataAttribute;
                Log.v(TAG, "humidity: " + currentWeather[3]);
            } else if (localName.equals("wind_condition")) {
                 //����չ     
            }
            // 'Inner' Tags within "<forecast_conditions>"
            else if (localName.equals("low")) {
                 int temp = Integer.parseInt(dataAttribute);
                 if (this.usingSITemperature) {
                 	//����չ  
                 } else {
                 	//����չ  
                 }
            } else if (localName.equals("high")) {
                 //int temp = Integer.parseInt(dataAttribute);
                 if (this.usingSITemperature) {
                 	//����չ  
                 } else {
                 	//����չ  
                 }
            }
       }

    }

    // ����һ���ڵ��������
    @Override
    public void endElement(String uri, String localName, String qName) {
    	if (localName.equals("forecast_information")) {
            this.in_forecast_information = false;
       } else if (localName.equals("current_conditions")) {
            this.in_current_conditions = false;
       } else if (localName.equals("forecast_conditions")) {
            this.in_forecast_conditions = false;
       }
    }

    // ÿ���һ���ڵ����ʱ����
    @Override
    public void characters(char[] ch, int start, int length) {

    }

    public String[] getCurrentWeather() {
        return currentWeather;
    }
}
