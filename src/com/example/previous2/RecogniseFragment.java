package com.example.previous2;


import com.cengalabs.flatui.views.FlatButton;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class RecogniseFragment extends Fragment {
	FlatButton agree2;
	public RecogniseFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.gotiy2, container, false);
         
        agree2 = (FlatButton) rootView.findViewById(R.id.flatButton2);
        agree2.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View v) {
				Toast.makeText(RecogniseFragment.this.getActivity(), "Agreed", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(RecogniseFragment.this.getActivity(), Recognise2Fragment.class);
				startActivity(i);

			}
       	 
        });
       
       return rootView;
   }
}
