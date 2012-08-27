package com.mirrorlabs.swipey;


import java.util.ArrayList;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListActivity {

	private static final String TAG = "ListActivity";

	private Activity mActivity;
	private ViewGroup mRootView;
	final private ArrayAdapter<String> mAdapter;
	private ListView mylist;
	final private ArrayList<String> mdata;

	
	public ListActivity(Activity act, ViewGroup root) {
		mActivity               = act;
		mRootView               = root;
		mylist =(ListView)root.findViewById(R.id.myList);
		mdata= new ArrayList<String>();
		for(int i=0;i<27;i++){
			mdata.add("My Apps "+i);
		}

		mAdapter = new ArrayAdapter<String>(act,android.R.layout.simple_list_item_1,mdata);
		
		mylist.setAdapter(mAdapter);
		
			
	}
		

}

