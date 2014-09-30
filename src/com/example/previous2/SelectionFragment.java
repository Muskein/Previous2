package com.example.previous2;

import java.io.BufferedReader;

import android.support.v4.app.Fragment;
import android.media.FaceDetector;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Build;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.TimeZone;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



























import com.cengalabs.flatui.views.FlatButton;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;
import com.example.previous2.NotificationOne;
import com.example.previous2.NotificationTwo;
import com.example.previous2.R;
import com.example.previous2.TemperatureService;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class SelectionFragment extends Fragment {
FlatButton agree;
private NotificationManager myNotificationManager;
private int notificationIdOne = 111;
private int numMessagesOne = 0;
Uri pathuri;
private static final int MAX_FACES = 10;
String[] paths;
String[] selectedimages;

Bitmap myBitmap;
int ipl=0;
ProgressDialog progress;


public static int numberOfFaceDetected;
private int imageWidth, imageHeight;

private int numberOfFace = 5;
int i=0;

private FaceDetector myFaceDetect;

private FaceDetector.Face[] myFace;
String picturepath;

String s="";





private ImageView iv_image;

//a variable to store a reference to the Surface View at the main.xml file
private SurfaceView sv;
TemperatureService ts;
//a bitmap to display the captured image
private Bitmap bmp;

//Camera variables
//a surface holder
private SurfaceHolder sHolder;  
//a variable to control the camera
private Camera mCamera;
//the camera parameters
private Parameters parameters;

	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.gotiy, container, false);
 Intent intent = new Intent("MyCustomIntent");
        
        // EditText et = (EditText)findViewById(R.id.extraIntent);
         // add data to the Intent
        // intent.putExtra("message", (CharSequence)et.getText().toString());
         intent.setAction("com.javacodegeeks.android.A_CUSTOM_INTENT");
         getActivity().sendBroadcast(intent);
//startService(rootView);
	      new BackgroundTask().execute();

	    new Intent(SelectionFragment.this.getActivity().getBaseContext(), TemperatureService.class);
			selectedimages = new String[10]; 		    
		    ipl=0;


        agree = (FlatButton) rootView.findViewById(R.id.flatButton1);
         
        agree.setOnClickListener(new OnClickListener()
         {

			@Override
			public void onClick(View v) {
				Toast.makeText(SelectionFragment.this.getActivity(), "Agreed", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(SelectionFragment.this.getActivity(), MainActivityOpenCamera.class);
				startActivity(i);

			}
        	 
         });
        
        return rootView;
    }
	
	
	  private class BackgroundTask extends AsyncTask<Void, Void, Void> {
	 	     protected Void doInBackground(Void... args) {
	 	         final String CAMERA_IMAGE_BUCKET_NAME =
	 	      	        Environment.getExternalStorageDirectory().toString()
	 	      	        + "/DCIM/Camera";
	 	      	 final String CAMERA_IMAGE_BUCKET_ID =
	 	      	        getBucketId(CAMERA_IMAGE_BUCKET_NAME);

	 	          final String[] projection = { MediaStore.Images.Media.DATA };
	 	     	    
	 	         String orderBy = MediaStore.Images.Media.DATE_TAKEN + " DESC";
	 	          final String selection = MediaStore.Images.Media.BUCKET_ID + " = ?";
	 	     	    final String[] selectionArgs = { CAMERA_IMAGE_BUCKET_ID };
	 	     	    final Cursor cursor = SelectionFragment.this.getActivity().getContentResolver().query(Images.Media.EXTERNAL_CONTENT_URI, 
	 	     	            projection, 
	 	     	            selection, 
	 	     	            selectionArgs, 
	 	     	            orderBy);
	 	     final	    ArrayList<String> result = new ArrayList<String>(cursor.getCount());
//Collections.sort(result);
	 	     
	   		    if (cursor.moveToFirst()) {
	 		        final int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	 		        do {
	 		            final String data = cursor.getString(dataColumn);
	 		            result.add(data);
	 		        } while (cursor.moveToNext());
	 		    }
	 		    cursor.close();
	 	if(i<result.size())
	 	{
	 		
	 		//list.setText(result.toString());
	 		picturepath = result.get(i);
	 		File f = new File(picturepath);
	 		//long lastModified = f.lastModified();

		      //  Date lastModifiedDate = new Date(lastModified);

	 		BitmapFactory.Options bitmap_options = new BitmapFactory.Options();
	 		            bitmap_options.inPreferredConfig = Bitmap.Config.RGB_565;
	 		Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath(),bitmap_options);

	 		FaceDetector face_detector = new FaceDetector(
	 				   bmp.getWidth(), bmp.getHeight(),
	 			    MAX_FACES);
	 		
	 		myFace = new FaceDetector.Face[MAX_FACES];
	 			                // The bitmap must be in 565 format (for now).
	 		                numberOfFaceDetected = face_detector.findFaces(bmp, myFace);
	 		
/*		 		   imageWidth = bmp.getWidth();
	 			
	 		   imageHeight = bmp.getHeight();
	 		
	 		   myFace = new FaceDetector.Face[numberOfFace];
	 		
	 		   myFaceDetect = new FaceDetector(imageWidth, imageHeight,
	 		
	 		     numberOfFace);
	 		
	 		   numberOfFaceDetected = myFaceDetect.findFaces(bmp, myFace);
*/
	 		                
	 			s = s + result.get(i) + " and faces-" + numberOfFaceDetected + "\n";
	 				if(numberOfFaceDetected ==1)
	 				{
	 				
	 					
	 			//        Toast.makeText(SelectionFragment.this.getActivity(), "File: " + result.get(i) + " and lastModifiedDate is: " + lastModifiedDate , Toast.LENGTH_SHORT).show();
	 					selectedimages[ipl]= result.get(i);
	 					ipl++;
	 				}
	 			i++;
if(i==result.size())
{
//	String date = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
	
	//Toast.makeText(SelectionFragment.this.getActivity(), date, Toast.LENGTH_LONG).show();
displayNotificationOne();

}//  list.setText(s);
}
	 	

	 	    	 // do background work here
	 	        return null;
	 	     }

	 	     
	 	     protected void onPostExecute(Void result) {
//list.setText(s);
	 	    	 new BackgroundTask().execute();

	 	    //	 progress.dismiss();
	 	         // do UI work here
	 	     }
	 	     
	 	     @Override
	 	        protected void onPreExecute() {
	 	            super.onPreExecute();

	 	      /*      progress = ProgressDialog.show(MainActivity.this,
	 	                    "Loading", "Please Wait");
*/
	 	        }

	 	 } 

	   protected void displayNotificationOne() {

			// define sound URI, the sound to be played when there's a notification
			Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			
			// intent triggered, you can add other intent for other actions
			Intent intent = new Intent(SelectionFragment.this.getActivity(), NotificationOne.class);
		      intent.putExtra("notificationId", notificationIdOne);
intent.putExtra("ipl", ipl);
intent.putExtra("path", selectedimages);
			PendingIntent pIntent = PendingIntent.getActivity(SelectionFragment.this.getActivity(), 0, intent, 0);
			
			// this is it, we'll build the notification!
			// in the addAction method, if you don't want any icon, just set the first param to 0
			Notification mNotification = new Notification.Builder(SelectionFragment.this.getActivity())
				
				.setContentTitle("Complete!")
				.setContentText("All Photos Checked!")
				.setSmallIcon(R.drawable.ninja)
				.setContentIntent(pIntent)
				.setSound(soundUri)
				.setAutoCancel(true)
				.setWhen(System.currentTimeMillis())
				.setDefaults(Notification.DEFAULT_VIBRATE)
				.setLights(Color.RED, 3000, 3000)
				.setTicker("Photos Checked")
				.addAction(R.drawable.ninja, "View", pIntent)
				.addAction(0, "Remind", pIntent)
				
				.build();
			
			SelectionFragment.this.getActivity();
			NotificationManager notificationManager = (NotificationManager) SelectionFragment.this.getActivity().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

			// If you want to hide the notification after it was selected, do the code below
			// myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
			
			notificationManager.notify(notificationIdOne, mNotification);

	   }



	   
	      public static String getBucketId(String path) {
	  	    return String.valueOf(path.toLowerCase().hashCode());
	  	}
	
}
