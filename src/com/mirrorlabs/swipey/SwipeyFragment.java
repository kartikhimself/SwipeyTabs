/*
 * Copyright (C) 2012 Jared Rummler
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.mirrorlabs.swipey;





import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SwipeyFragment extends Fragment {
     
	private static ListActivity sBusyboxInstaller;
	private static MultiListActivity sBusyboxInstaller2;
    
	public static Fragment newInstance(int position) {
		SwipeyFragment f = new SwipeyFragment();
		Bundle args = new Bundle();
		args.putInt("position", position);
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = null;
		switch (getArguments().getInt("position")) {
		case SwipeyActivity.TAB_1:
			root = (ViewGroup) inflater.inflate(R.layout.about_us_dialog, null);
			break;
		case SwipeyActivity.TAB_2:
			root = (ViewGroup) inflater.inflate(R.layout.listview, null);
			sBusyboxInstaller = new ListActivity(getActivity(), root);
			break;
		case SwipeyActivity.TAB_3:
			root = (ViewGroup) inflater.inflate(R.layout.listview, null);
			sBusyboxInstaller = new ListActivity(getActivity(), root);
		case SwipeyActivity.TAB_4:
			root = (ViewGroup) inflater.inflate(R.layout.listview, null);
			sBusyboxInstaller = new ListActivity(getActivity(), root);
		case SwipeyActivity.TAB_5:
			root = (ViewGroup) inflater.inflate(R.layout.listview, null);
			sBusyboxInstaller = new ListActivity(getActivity(), root);
			
		case SwipeyActivity.TAB_6:
			root = (ViewGroup) inflater.inflate(R.layout.listview, null);
			sBusyboxInstaller = new ListActivity(getActivity(), root);
			
		case SwipeyActivity.TAB_7:
			root = (ViewGroup) inflater.inflate(R.layout.listview, null);
			sBusyboxInstaller = new ListActivity(getActivity(), root);
						
				
				
			
			break;
			
		}
		return root;
	}
	public static Object getData(int position) {
		switch (position) {
		case SwipeyActivity.TAB_1:
			return null;
		case SwipeyActivity.TAB_2:			
			return null;
		case SwipeyActivity.TAB_3:			
			return null;
		case SwipeyActivity.TAB_4:
			return null;
		case SwipeyActivity.TAB_5:			
			return null;
		case SwipeyActivity.TAB_6:			
			return null;
		case SwipeyActivity.TAB_7:			
			return null;	
		default:
			return null;
		}
	}
	
	
}
