package com.example.previous2;

import java.io.BufferedReader;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.app.Activity;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;













import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SelectionFragment extends Fragment {
	private ProfilePictureView profilePictureView;
	ImageButton share, logout;
	private TextView userNameView;	
	private TextView profileNameView;
	static String userID="12345";
	static int uploadedShared=0;
	static int searchedShared=0;
	private static final String TAG = "SelectionFragment";
	static private CheckBox agree, disagree;
	private UiLifecycleHelper uiHelper;
	private static final int REAUTH_ACTIVITY_CODE = 100;
	EditText displayPhone;
	
	URL img_value=null;
	int check=0;
	String url="";
	String uid="",auth="",album="";
	JSONArray jsonArray = new JSONArray();
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(final Session session, final SessionState state, final Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    if (requestCode == REAUTH_ACTIVITY_CODE) {
	        uiHelper.onActivityResult(requestCode, resultCode, data);
	    }
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	    loadSavedPreferences();
		 

	}

	@Override
	public void onSaveInstanceState(Bundle bundle) {
	    super.onSaveInstanceState(bundle);
	    uiHelper.onSaveInstanceState(bundle);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	    
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	    StrictMode.setThreadPolicy(policy);
	   
	   
	
	}
	
	public void addListenerOnShare(View view) {
		 
		
 
		share.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			   Toast.makeText(SelectionFragment.this.getActivity(),
				"Share Button is clicked!", Toast.LENGTH_SHORT).show();
			   
			   savePreferences("CheckBox_Value", agree.isChecked());
			  
				 if(agree.isChecked())
				  {
						
					 savePreferences("storedID", userID);
					 savePreferences("storedSearched", searchedShared);
					 savePreferences("storedUploaded", uploadedShared);
					 
					 
					 Toast.makeText(SelectionFragment.this.getActivity(),
									"Preferences Stored!", Toast.LENGTH_SHORT).show();
						   Toast.makeText(SelectionFragment.this.getActivity(),
									"ID is " + userID + " Searched is " + searchedShared + " Uploaded is "+ uploadedShared, Toast.LENGTH_SHORT).show();
				  }	   

				  else
				  {
						
					
						   Toast.makeText(SelectionFragment.this.getActivity(),
								"Click on 'I agree' checkbox first!", Toast.LENGTH_SHORT).show();
				  }			   
			   Intent intentMain = new Intent(SelectionFragment.this.getActivity() , 
                       MainActivityOpenCamera.class);
SelectionFragment.this.startActivity(intentMain);
Log.i("Content "," Main layout ");
		
			}
		});
 
	}
	
	public void addListenerOnLogOut(View view) {
		 
		logout = (ImageButton) view.findViewById(R.id.logout);
 
		logout.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			   Toast.makeText(SelectionFragment.this.getActivity(),
				"Log Out Button is clicked!", Toast.LENGTH_SHORT).show();
 
			}
 
		});
 
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, Bundle savedInstanceState) {
	    super.onCreateView(inflater, container, savedInstanceState);
	    View view = inflater.inflate(R.layout.gotiy, container, false);
	    profilePictureView = (ProfilePictureView) view.findViewById(R.id.profile_pic);
	    displayPhone = (EditText) view.findViewById(R.id.editText1);

	    // Find the user's name view
	    userNameView = (TextView) view.findViewById(R.id.textView2);
	    profileNameView = (TextView) view.findViewById(R.id.textView4);
	    
	    share = (ImageButton) view.findViewById(R.id.share);
	    //loadSavedPreferences();
	    addListenerOnShare(view);
	    addListenerOnLogOut(view);
	    addListenerOnAgree(view);
		addListenerOnDisagree(view);
		
		
	    
	    Session session = Session.getActiveSession();
	    if (session != null && session.isOpened()) {
	        // Get the user's data
	        makeMeRequest(session);
	    }
	    
	    return view;
	}
	
	private void loadSavedPreferences() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		boolean checkBoxValue = sharedPreferences.getBoolean("CheckBox_Value", false);
		String ID = sharedPreferences.getString("storedID", userID);		 
		 int searched = sharedPreferences.getInt("storedSearched", searchedShared);
		 int uploaded = sharedPreferences.getInt("storedUploaded", uploadedShared);
		 
		
		
		
		
		if (checkBoxValue) {
			agree.setChecked(true);
			Toast.makeText(SelectionFragment.this.getActivity(),
					"Preferences Stored!", Toast.LENGTH_SHORT).show();
		   Toast.makeText(SelectionFragment.this.getActivity(),
					"ID is " + ID + " Searched is " + searched + " Uploaded is "+ uploaded, Toast.LENGTH_SHORT).show();
			
		} else {
			agree.setChecked(false);
		}
	

	}
	
	private void savePreferences(String key, boolean value) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	private void savePreferences(String key, int value) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	private void savePreferences(String key, String value) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	

	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }
	

	 public void addListenerOnAgree(View view) {
		 
			agree = (CheckBox) view.findViewById(R.id.agree);
		 
			agree.setOnClickListener(new OnClickListener() {
		 
			  @Override
			  public void onClick(View v) {
		                //is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(SelectionFragment.this.getActivity(),
				 	   "Agreed!", Toast.LENGTH_SHORT).show();
				}
				
			  }
			});
		 
		  }	
	
	 public void addListenerOnDisagree(View view) {
		 
			disagree = (CheckBox) view.findViewById(R.id.disagree);
		 
			disagree.setOnClickListener(new OnClickListener() {
		 
			  @Override
			  public void onClick(View v) {
		                //is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(SelectionFragment.this.getActivity(),
				 	   "Disagreed!", Toast.LENGTH_SHORT).show();
				}
		 
			  }
			});
		 
		  }	
	
	 public static String GET(String url){
	        InputStream inputStream = null;
	        String result = "";
	        try {
	 
	            // create HttpClient
	            HttpClient httpclient = new DefaultHttpClient();
	 
	            // make GET request to the given URL
	            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
	 
	            // receive response as inputStream
	            inputStream = httpResponse.getEntity().getContent();
	 
	            // convert inputstream to string
	            if(inputStream != null)
	                result = convertInputStreamToString(inputStream);
	            else
	                result = "Did not work!";
	 
	        } catch (Exception e) {
	            Log.d("InputStream", e.getLocalizedMessage());
	        }
	 
	        return result;
	    }
	 private class ProgressTask extends AsyncTask<String, Void, String> {
	       
	       
	        // private List<Message> messages;
	        
	        private Context context;
	 
	        private ProgressDialog dialog;
	        

	        /** progress dialog to show user that the backup is processing. */
	        /** application context. */
	       
	 
	        @Override
	        protected void onPostExecute(final String success) {
	     //   	Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
	        if(check==0)
	        {
	        	JSONObject json = null;
				try {
					json = new JSONObject(success);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // convert String to JSONObject
	        	try {
					jsonArray = json.getJSONArray("data");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // get articles array
	            // select single ListView item
	        	for(int i=0;i<jsonArray.length();i++)
	        	{
	        		try {
						if(jsonArray.getJSONObject(i).getString("name").equals("Profile Pictures"))
							album=jsonArray.getJSONObject(i).getString("id");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        	next();
	        }
	        else
	        {
	        	JSONObject json = null;
				JSONArray jarray=new JSONArray();
	        	try {
					json = new JSONObject(success);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // convert String to JSONObject
	        	try {
					jarray = json.getJSONArray("data");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // get articles array
	            // select single ListView item
	        	String str="";
	        	userNameView.setText("");
	        	for(int i=0;i<jarray.length();i++)
	        	{	try {
				str=jarray.getJSONObject(i).getString("source");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	userNameView.setText(userNameView.getText());
	        	profileNameView.setText(userNameView.getText());
	        	}
	        
	        }
	        
	        }



			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				return GET(params[0]);
				 		}
	        }
	        
	 
	   
 	public void next()
 	{check=1;
        new ProgressTask().execute("https://graph.facebook.com/"+album+"/photos?access_token="+auth);
	    

 	}

	private void makeMeRequest(final Session session) {
	    // Make an API call to get user data and define a 
	    // new callback to handle the response.
	    Request request = Request.newMeRequest(session, 
	            new Request.GraphUserCallback() {
	        
			@Override
			public void onCompleted(GraphUser user, Response response) {
				// TODO Auto-generated method stub
				int count=0;
				if (session == Session.getActiveSession()) {
	                if (user != null) {
	                    // Set the id for the ProfilePictureView
	                    // view that in turn displays the profile picture.
	                    profilePictureView.setProfileId(user.getId());
	                    profileNameView.setText(user.getUsername());
	                   
	    	            userID = user.getId();
	                    userNameView.setText(user.getFirstName() +  " "+ user.getLastName());
	                    
	                   	String str = session.getAccessToken();
	                   	uid=user.getId();
	                   	
	                   	
	                   	auth=str;
	                   	url = "https://graph.facebook.com/"+user.getId()+"/albums?access_token="+str;
	                  //  new ProgressTask().execute(url);
	                    
	                    
	                   	   // Set the Textview's text to the user's name.
	                }
	            }
	            if (response.getError() != null) {
	                // Handle errors, will do so later.
	            }
	        }
	    });
	    request.executeAsync();
	}
	
	private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
	    if (session != null && session.isOpened()) {
	        // Get the user's data.
	        makeMeRequest(session);
	    }
	}
}
