package com.example.previous2;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TwoButtonSelfieReturn extends Activity {
	Button twoselfie, recognise;
	Preview p;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twobuttonlayout);
		twoselfie = (Button) findViewById(R.id.twoselfie);
		recognise = (Button) findViewById(R.id.recognise);
		
		recognise.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(),
						"Recognise Clicked", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent( TwoButtonSelfieReturn.this.getApplicationContext(), 
	                    MainActivityOpenCamera2.class);
	TwoButtonSelfieReturn.this.startActivity(intent);

			}
		});
 
		twoselfie.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(),
						"twoselfie clicked", Toast.LENGTH_SHORT).show();
				p.numberOfPictures=0;
Intent intent = new Intent( TwoButtonSelfieReturn.this.getApplicationContext(), 
	                    MainActivityOpenCamera.class);
	TwoButtonSelfieReturn.this.startActivity(intent);

			}
		});
 
		

}
}