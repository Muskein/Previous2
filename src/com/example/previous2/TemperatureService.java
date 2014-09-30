package com.example.previous2;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.FaceDetector;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.widget.Toast;
public class TemperatureService extends Service {
	FileOutputStream out;
	int n=0;
	
	

	   @Override
	   public int onStartCommand(Intent intent, int flags, int startId) {
	      // Let it continue running until it is stopped.
	      Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
	      return START_STICKY;
	   }
	   
	   
	   @Override
	   public void onDestroy() {
	      super.onDestroy();
	      Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	   }
	   

	private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
		
			Log.d("PHOTO", "Clicked!!!!!");
			Toast.makeText(context, "Photo Clicked!", Toast.LENGTH_LONG).show();
			
			    
			    
		}

			
		
		
		
	
	};
		

	@Override
	public void onCreate() {
		 this.registerReceiver(this.batteryInfoReceiver,	new IntentFilter(Intent.ACTION_CAMERA_BUTTON));
		
			
  	}
		

	

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}



	
	 
}
