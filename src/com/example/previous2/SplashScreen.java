package com.example.previous2;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreen extends Activity {

	Thread splashTread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_scrren);
		
		splashTread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while (waited < 2000) {
						sleep(100);
						waited += 100;
					}
				//	Intent intent = new Intent(SplashScreen.this,
							//MainActivity.class);
					//intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					//startActivity(intent);
					SplashScreen.this.finish();
				} catch (InterruptedException e) {
					// do nothing
				} finally {
					SplashScreen.this.finish();
				}

			}
		};
		splashTread.start();
	}

}
