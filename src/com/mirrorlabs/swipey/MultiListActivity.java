package com.mirrorlabs.swipey;





import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;









import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;




public class MultiListActivity {

	private static final String TAG = "MultiListActivity";
	
	private static final int SET_PROGRESS = 0x00;
	private static final int FINISH_PROGRESS = 0x01;
	private static final int FLAG_UPDATED_SYS_APP = 0x80;

	 private static final String CHECK_STATES = "mylist:check_states";
	 private boolean[] mCheckStates=null;
	 private static String[] Content=null;

	private Button mSelected,mSelectAll,mUnselect;
	private Activity mActivity;
	private ViewGroup mRootView;
	private ArrayAdapter<String> mAdapter;
	private ListView mylist;
	final private ArrayList<String> mdata;
	private static ArrayList<ApplicationInfo> mAppList=null;
	private static PackageManager mPackMag=null;
	private static PackageInfo  appinfo=null;
	private static ApplicationInfo  apkinfo=null;

	
	
	public MultiListActivity(Activity act, ViewGroup root) {
		
		mActivity               = act;
		mRootView               = root;
		mylist =(ListView)root.findViewById(R.id.multiList);
		mSelected=(Button)root.findViewById(R.id.showSelectedButton);
		mSelectAll=(Button)root.findViewById(R.id.selectAllButton);
		mUnselect=(Button)root.findViewById(R.id.unselectButton);
		mAppList = new ArrayList<ApplicationInfo>();
        mPackMag = mActivity.getPackageManager();
		mdata= new ArrayList<String>();
		
		
		
	
        /*
		for(int i=0;i<9;i++){
			mdata.add("Item "+i);
		}

		mAdapter = new ArrayAdapter<String>(act,android.R.layout.simple_list_item_1,mdata);
		
*/		loadlist();
		
		
		
		
		}
	
	public void loadlist(){
		
		Content = new String[28]; 
        for(int i =0 ;i<=27 ; i++){
        	Content[i]="List Text " + i ;
        	
        }
        initializeDrawbale();
        get_downloaded_apps();
        mCheckStates = new boolean[mAppList.size()];
		mylist.setAdapter(new CustomAdapter());
		mylist.setOnItemClickListener(mListListner);

		
		mSelected.setOnClickListener(mButtonListener);
		mSelectAll.setOnClickListener(mButtonListener);
		mUnselect.setOnClickListener(mButtonListener);
			
		
		
	}
	
	private void get_downloaded_apps() {
		List<ApplicationInfo> all_apps = mPackMag.getInstalledApplications(
											PackageManager.GET_UNINSTALLED_PACKAGES);
		
		for(ApplicationInfo appInfo : all_apps) {
			if((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0 && 
			   (appInfo.flags & FLAG_UPDATED_SYS_APP) == 0 && 
			   appInfo.flags != 0)
				
				mAppList.add(appInfo);
		}
		
		//mAppLabel.setText("You have " +mAppList.size() + " downloaded apps");
	}
	
	
	private OnClickListener mButtonListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.selectAllButton:
				for(int i = 0 ; i<mAppList.size() ; i++){
					mCheckStates[i]=true;
				}
		        mylist.setAdapter(new CustomAdapter());

				
				break;
			case R.id.unselectButton:
				for(int i = 0 ; i<mAppList.size() ; i++){
					mCheckStates[i]=false;
				}
		        mylist.setAdapter(new CustomAdapter());

				break;

			case R.id.showSelectedButton:
				int len = mAppList.size();
		    	String check="";
		    	for(int i=0;i<len;i++){
		    		if(mCheckStates[i]==true){
		    			ApplicationInfo info = mAppList.get(i);
		    			String appname = info.packageName;
		    		check = check +"\n"+appname ;
		    		}
		    	}
		        Toast.makeText(mActivity,check, Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	
	private OnItemClickListener mListListner = new OnItemClickListener() {
		
		public void onItemClick(AdapterView<?> av, View v, int position,
				long id) {
			// TODO Auto-generated method stub
			mCheckStates[position]= !mCheckStates[position];
			
		}
	};
	
	
	
	 private static class ViewHolder {
	        public CheckBox select=null;
	        public TextView label=null;
	        public ImageView icon=null;
	    }

	   
	    private class CustomAdapter extends ArrayAdapter<ApplicationInfo>{

	    	private CustomAdapter() {
				super(mActivity, R.layout.listitem, mAppList);
			}
	    	
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {

	            ViewHolder holder;
	            ApplicationInfo info = mAppList.get(position);
				String appname = info.loadLabel(mPackMag).toString();

	            if (convertView == null) {
	                convertView = mActivity.getLayoutInflater().inflate(R.layout.listitem, null);

	                holder = new ViewHolder();
	                holder.select= (CheckBox) convertView.findViewById(R.id.select_icon);
	                holder.label = (TextView) convertView.findViewById(R.id.label);
	                holder.icon =(ImageView)convertView.findViewById(R.id.icon);
	                


	                convertView.setTag(holder);
	            } else {
	                holder = (ViewHolder) convertView.getTag();
	            }

	            /*
	             * The Android API provides the OnCheckedChangeListener interface
	             * and its onCheckedChanged(CompoundButton buttonView, boolean
	             * isChecked) method. Unfortunately, this implementation suffers
	             * from a big problem: you can't determine whether the checking
	             * state changed from code or because of a user action. As a result
	             * the only way we have is to prevent the CheckBox from callbacking
	             * our listener by temporary removing the listener.
	             */
	            holder.select.setOnCheckedChangeListener(null);
	            holder.select.setChecked(mCheckStates[position]);
	            holder.select.setOnCheckedChangeListener(mCheckBoxChangeListener);

	            holder.label.setText(appname);
	            holder.icon.setImageDrawable(getAppIcon(info.packageName));
	            return convertView;
	        }

			
	    }
	    
	    private OnCheckedChangeListener mCheckBoxChangeListener = new OnCheckedChangeListener() {
	        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	            final int position = mylist.getPositionForView(buttonView);
	            if (position != ListView.INVALID_POSITION) {
	                mCheckStates[position] = isChecked;
	            }
	        }
	    };
	    
	    public static class AppIconManager{
	        private static ConcurrentHashMap<String, Drawable> cache;
	        }
	        
	        	
	    	private void initializeDrawbale(){
	      	  AppIconManager.cache = new ConcurrentHashMap<String, Drawable>();  
	      	  }

	        public Drawable getDrawableFromCache(String url) {  
	            if (AppIconManager.cache.containsKey(url)) {  
	                return AppIconManager.cache.get(url); 
	            }  
	      
	            return null;  
	        }  
	        
	        public Drawable getAppIcon(String packagename){
	        	Drawable drawable;
	        	drawable = getDrawableFromCache(packagename);
	        	if(drawable!=null){
	        		return drawable;
	        	}
	        	else{
				try {
					
					drawable = mPackMag.getApplicationIcon(packagename);
					AppIconManager.cache.put(packagename, drawable);
				    
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					return mActivity.getResources().getDrawable(R.drawable.apk_file);
					
				}
	        	return drawable;
	        	}
	        }

	
		

}

