package com.example.previous2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.previous2.R;
import com.example.previous2.ArcMenu.SateliteClickedListener;

public class ArcShow extends Activity 
{
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arc_show);
        this.setTitle("Arc Menu");
        
        ArcMenu menu = (ArcMenu) findViewById(R.id.menu);
        
        List<ArcMenuItem> items = new ArrayList<ArcMenuItem>();
        items.add(new ArcMenuItem(1, R.drawable.arc_1));
        items.add(new ArcMenuItem(2, R.drawable.arc_2));
        items.add(new ArcMenuItem(3, R.drawable.arc_3));
        menu.addItems(items);        
        
        menu.setOnItemClickedListener(new SateliteClickedListener() 
        {			
			public void eventOccured(int id)
			{
				switch(id)
				{
					case 1:
						Toast.makeText(getApplicationContext(), "AIR", Toast.LENGTH_SHORT).show();
						break;
						
					case 2:
						Toast.makeText(getApplicationContext(), "Camera", Toast.LENGTH_SHORT).show();
						break;
						
					case 3:
						Toast.makeText(getApplicationContext(), "Twitter", Toast.LENGTH_SHORT).show();
						break;
						
					default:
						System.out.println("Default");
						break;
				}
				
			}
		});
    }
}