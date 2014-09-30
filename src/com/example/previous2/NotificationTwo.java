package com.example.previous2;

import java.io.File;
import java.util.ArrayList;

import com.example.previous2.NotificationTwo.ImageAdapter;
//import com.javacodegeeks.android.broadcastreceiverstest.NotificationOne.ViewHolder;








import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class NotificationTwo extends Activity {
	
	
	
		private int count;
		int ipl2;
		MainActivity ma;
		GridView imagegrid;
		private Bitmap[] thumbnails;
		private boolean[] thumbnailsselection;
		private String[] arrPath;
	String[] imagepath;
		private ImageAdapter imageAdapter;
		@Override
		   public void onCreate(Bundle savedInstanceState)
		   {
		      super.onCreate(savedInstanceState);
		      setContentView(R.layout.notification_two);
		    // ipl2 = ma.ipl;
		      //imagepath = new String[ipl2];
		      //for(int h=0;h<ipl2;h++)
		      //{
		      //imagepath[h] = ma.selectedimages[h];
		      //}
		      	      
	/*			int ideal=0;
			      
			      Bundle extras = getIntent().getExtras();
					if (extras == null) {
//						s = "error";
					}
					else {
						ideal = extras.getInt("notificationId");
					}
					NotificationManager myNotificationManager = 
							(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
					
					// remove the notification with the specific id
					myNotificationManager.cancel(ideal);
	*/
		    /*  final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };
					final String orderBy = MediaStore.Images.Media._ID;
					Cursor imagecursor = managedQuery(
							MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
							null, orderBy);
					int image_column_index = imagecursor.getColumnIndex(MediaStore.Images.Media._ID);
					this.count = imagecursor.getCount();
					this.thumbnails = new Bitmap[this.count];
					this.arrPath = new String[this.count];
					this.thumbnailsselection = new boolean[this.count];
					for (int i = 0; i < this.count; i++) {
						imagecursor.moveToPosition(i);
						int id = imagecursor.getInt(image_column_index);
						int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
						thumbnails[i] = MediaStore.Images.Thumbnails.getThumbnail(
								getApplicationContext().getContentResolver(), id,
								MediaStore.Images.Thumbnails.MICRO_KIND, null);
						arrPath[i]= imagecursor.getString(dataColumnIndex);
					}
					*/
					imagegrid = (GridView) findViewById(R.id.PhoneImageGrid2);
					 imagegrid.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
				        imagegrid.setMultiChoiceModeListener(new MultiChoiceModeListener());
					imageAdapter = new ImageAdapter(this);
					imagegrid.setAdapter(imageAdapter);
					
					String ExternalStorageDirectoryPath = Environment
			        		.getExternalStorageDirectory()
			        		.getAbsolutePath();
			        
			        String targetPath = ExternalStorageDirectoryPath + "/DCIM/Camera/";
			        Bundle bundle = getIntent().getExtras();
			        int ipl2 = bundle.getInt("ipl");
				      Toast.makeText(getApplicationContext(), "Value " + ipl2 , Toast.LENGTH_LONG).show();

			        Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_LONG).show();
			        File targetDirector = new File(targetPath);
			        
			        File[] files = targetDirector.listFiles();
			        //int ip=0;
	/*	        for (File file : files)
			        	{
			        	imageAdapter.add(file.getAbsolutePath());
			        }
	*/
			        imagepath=bundle.getStringArray("path");
			        for(int ip=0;ip<imagepath.length;ip++)
			        {
			        	imageAdapter.add(imagepath[ip]);
			        }
					
				
					/*final Button selectBtn = (Button) findViewById(R.id.selectBtn);
					selectBtn.setOnClickListener(new OnClickListener() {
						
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final int len = thumbnailsselection.length;
							int cnt = 0;
							String selectImages = "";
							for (int i =0; i<len; i++)
							{
								if (thumbnailsselection[i]){
									cnt++;
									selectImages = selectImages + arrPath[i] + "|";
								}
							}
							if (cnt == 0){
								Toast.makeText(getApplicationContext(),
										"Please select at least one image",
										Toast.LENGTH_LONG).show();
							} else {
								Toast.makeText(getApplicationContext(),
										"You've selected Total " + cnt + " image(s).",
										Toast.LENGTH_LONG).show();
								Log.d("SelectedImages", selectImages);
							}
						}
					});*/
				}
		public class ImageAdapter extends BaseAdapter {
			private LayoutInflater mInflater;
			private Context mContext;
	    	ArrayList<String> itemList = new ArrayList<String>();
	    	
	    	public ImageAdapter(Context c) {
	    		mContext = c;	
	    	}
	    	
	    	void add(String path){
	    		itemList.add(path);	
	    	}

			@Override
			public int getCount() {
				return itemList.size();
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
	    	


			public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {
				
				Bitmap bm = null;
				// First decode with inJustDecodeBounds=true to check dimensions
				final BitmapFactory.Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(path, options);
			     
				// Calculate inSampleSize
				options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
			     
				// Decode bitmap with inSampleSize set
				options.inJustDecodeBounds = false;
				bm = BitmapFactory.decodeFile(path, options); 
			     
				return bm;  	
			}
			
			public int calculateInSampleSize(
					
				BitmapFactory.Options options, int reqWidth, int reqHeight) {
				// Raw height and width of image
				final int height = options.outHeight;
				final int width = options.outWidth;
				int inSampleSize = 1;
				
				if (height > reqHeight || width > reqWidth) {
					if (width > height) {
						inSampleSize = Math.round((float)height / (float)reqHeight);   	
					} else {
						inSampleSize = Math.round((float)width / (float)reqWidth);   	
					}   
				}
				
				return inSampleSize;   	
			}

			
			/*public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder holder;
				if (convertView == null) {
					holder = new ViewHolder();
					convertView = mInflater.inflate(
							R.layout.galleryitem, null);
					holder.imageview = (ImageView) convertView.findViewById(R.id.thumbImage);
					holder.checkbox = (CheckBox) convertView.findViewById(R.id.itemCheckBox);
					
					convertView.setTag(holder);
				}
				else {
					holder = (ViewHolder) convertView.getTag();
				}
				holder.checkbox.setId(position);
				holder.imageview.setId(position);
				holder.checkbox.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						CheckBox cb = (CheckBox) v;
						int id = cb.getId();
						if (thumbnailsselection[id]){
							cb.setChecked(false);
							thumbnailsselection[id] = false;
						} else {
							cb.setChecked(true);
							thumbnailsselection[id] = true;
						}
					}
				});
				holder.imageview.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int id = v.getId();
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_VIEW);
						intent.setDataAndType(Uri.parse("file://" + arrPath[id]), "image/*");
						startActivity(intent);
					}
				});
				holder.imageview.setImageBitmap(thumbnails[position]);
				holder.checkbox.setChecked(thumbnailsselection[position]);
				holder.id = position;
				return convertView;
			}
		}
		class ViewHolder {
			ImageView imageview;
			CheckBox checkbox;
			int id;
		}
	}*/
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageView;
		        if (convertView == null) {  // if it's not recycled, initialize some attributes
		            imageView = new ImageView(mContext);
		            imageView.setLayoutParams(new GridView.LayoutParams(220, 220));
		            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		            imageView.setPadding(8, 8, 8, 8);
		        } else {
		            imageView = (ImageView) convertView;
		        }

		        Bitmap bm = decodeSampledBitmapFromUri(itemList.get(position), 220, 220);

		        imageView.setImageBitmap(bm);
		        return imageView;
			}
		}
		
		public class MultiChoiceModeListener implements GridView.MultiChoiceModeListener {
	        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	            mode.setTitle("Select Items");
	            mode.setSubtitle("One item selected");
	            return true;
	        }

	        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	            return true;
	        }

	      
	        public void onDestroyActionMode(ActionMode mode) {
	        }

	        public void onItemCheckedStateChanged(ActionMode mode, int position, long id,
	                boolean checked) {
	            int selectCount = imagegrid.getCheckedItemCount();
	            switch (selectCount) {
	            case 1:
	                mode.setSubtitle("One item selected");
	                break;
	            default:
	                mode.setSubtitle("" + selectCount + " items selected");
	                break;
	            }
	        }

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				// TODO Auto-generated method stub
				return false;
			}

	    }
	}

