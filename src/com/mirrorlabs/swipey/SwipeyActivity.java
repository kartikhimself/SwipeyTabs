package com.mirrorlabs.swipey;





import com.mirrorlabs.swipeytabs.SwipeyTabs;
import com.mirrorlabs.swipeytabs.SwipeyTabsAdapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SwipeyActivity extends FragmentActivity{
    /** Called when the activity is first created. */
	
	private String[] mTitles;
	private SwipeyTabs mTabs;
	private ViewPager mViewPager;
	
	static final int TAB_1= 0;
	static final int TAB_2 =1;
	static final int TAB_3 =2;
	static final int TAB_4 =3;

	static final int TAB_5=4;

	static final int TAB_6 =5;

	static final int TAB_7 =6;


	
	
	
	private static SwipeyActivity sActivity;

	public static SwipeyActivity getActivity() {
		return sActivity;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mTabs = (SwipeyTabs) findViewById(R.id.swipeytabs);
		
		mTitles = getResources().getStringArray(R.array.my_apps);
		SwipeyTabsPagerAdapter adapter = new SwipeyTabsPagerAdapter(getApplicationContext(), getSupportFragmentManager());
		mViewPager.setAdapter(adapter);
		mTabs.setAdapter(adapter);
		mTabs.setHandler(mOnTabChangedHandler);
		mViewPager.setOnPageChangeListener(mTabs);
		mViewPager.setOffscreenPageLimit(2);
		mViewPager.setCurrentItem(TAB_2);

    }
    
    private Handler mOnTabChangedHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (mViewPager.getCurrentItem()) {
			case TAB_1:
				//Toast.makeText(getApplicationContext(),mTitles[0], Toast.LENGTH_SHORT).show();
				break;
			case TAB_2:
				//Toast.makeText(getApplicationContext(), mTitles[1], Toast.LENGTH_SHORT).show();
                break;
			case TAB_3:
				//Toast.makeText(getApplicationContext(), mTitles[2], Toast.LENGTH_SHORT).show();
			case TAB_4:
				//Toast.makeText(getApplicationContext(), mTitles[3], Toast.LENGTH_SHORT).show();
			case TAB_5:
				//Toast.makeText(getApplicationContext(), mTitles[4], Toast.LENGTH_SHORT).show();
			case TAB_6:
				//Toast.makeText(getApplicationContext(), mTitles[5], Toast.LENGTH_SHORT).show();
			case TAB_7:
				//Toast.makeText(getApplicationContext(), mTitles[6], Toast.LENGTH_SHORT).show();
	
	
  
				

				break;
			}
		}
	};
    
    class SwipeyTabsPagerAdapter extends FragmentPagerAdapter implements SwipeyTabsAdapter {

    	private final Context mContext;

    	public SwipeyTabsPagerAdapter(Context context, FragmentManager fm) {
    		super(fm);
    		 mContext = getApplicationContext();
    	}

    	@Override
    	public Fragment getItem(int position) {
			return SwipeyFragment.newInstance(position);

    		
    	}

    	@Override
    	public int getCount() {
    		return mTitles.length;
    	}

    	public TextView getTab(final int position, SwipeyTabs root) {
    		TextView view = (TextView) LayoutInflater.from(mContext).inflate(
    				R.layout.swipey_tab_indicator, root, false);
    		view.setText(mTitles[position]);
    	
    		view.setTextSize(20);
    		view.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				mViewPager.setCurrentItem(position);
    			}
    		});

    		return view;
    	}
    	
    	
     }


}
