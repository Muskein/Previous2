package com.example.previous2;

import java.io.File;
import java.io.FileOutputStream;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
	FileOutputStream out;
	int n=0;

	@Override
	public void onReceive(Context context, Intent intent) {
	
		Log.d("PHOTO", "Clicked!!!!!");
		Toast.makeText(context, "Photo Clicked!", Toast.LENGTH_LONG).show();
		
		    String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + 
		                                "/Whozwhoo";
		        File dir = new File(file_path);
		        if(!dir.exists())
		           dir.mkdirs();
		        
		        File[] images = new File("/sdcard/DCIM/Camera").listFiles();
				File latestSavedImage = images[0];
				for(int i=1; i<images.length; ++i){
				 if(images[i].lastModified() > latestSavedImage.lastModified()){
				   latestSavedImage = images[i];
				 }
				   Bitmap bitmap = BitmapFactory.decodeFile(latestSavedImage.getPath());
	//			   Toast.makeText(context, "Path is " + latestSavedImage, Toast.LENGTH_LONG).show();

		        
				  
				   try {
				          out = new FileOutputStream("image " + n +  ".PNG");
				          bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
				          Toast.makeText(context, "Phnch gye", Toast.LENGTH_LONG).show();

				   } catch (Exception e) {
				       e.printStackTrace();
				   } finally {
				          try{
				              out.close();
				          } catch(Throwable ignore) {}
				   }  			
		
		    
	}

	}
}
