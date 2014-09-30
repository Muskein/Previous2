package com.example.previous2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.cengalabs.flatui.views.FlatCheckBox;
import com.cengalabs.flatui.views.FlatEditText;
import com.cengalabs.flatui.views.FlatRadioButton;
import com.cengalabs.flatui.views.FlatSeekBar;
import com.cengalabs.flatui.views.FlatTextView;
import com.cengalabs.flatui.views.FlatToggleButton;

public class HomeFragment extends Fragment {
	
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
         
        return rootView;
    }
	
	@Override
    public void onDetach() {
        }
}
