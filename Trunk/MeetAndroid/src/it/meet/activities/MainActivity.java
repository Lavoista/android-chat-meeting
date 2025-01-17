/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.meet.activities;

import it.meet.fragments.*;

import it.meet.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	public ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] framentsTitles;
	private SearchFragment searchFragment;
	
	static SharedPreferences storedInfo;
	public Fragment currentFragment;
	Editor preferencesEditor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		mTitle = mDrawerTitle = getTitle();
		framentsTitles = getResources().getStringArray(R.array.menu_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, framentsTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			@Override
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.action_websearch:
			// create intent to perform web search for this planet
			Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
			intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
			// catch event that there's no activity to handle intent
			if (intent.resolveActivity(getPackageManager()) != null) {
				startActivity(intent);
			} else {
				Toast.makeText(this, R.string.app_not_available,
						Toast.LENGTH_LONG).show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* The click listener for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	public void selectItem(int position) {
		// update the main content by replacing fragments
		Log.w("Position", "position=" + position);
		
		
		if (position == 0 && !(currentFragment instanceof  SearchFragment)) {
			if (searchFragment == null) {
				searchFragment = new SearchFragment();
			}
			currentFragment = searchFragment;
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, searchFragment).commit();
		} else if (position == 1 && !(currentFragment instanceof  ConversationsListFragment)) {
			ConversationsListFragment conversationsFragment = new ConversationsListFragment();
			currentFragment = conversationsFragment;
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, conversationsFragment)
					.commit();
		} else if (position == 2 && !(currentFragment instanceof FriendsListFragment)) {
			FriendsListFragment friendsFragment = new FriendsListFragment();
			currentFragment = friendsFragment;
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, friendsFragment).commit();
		} else if (position == 3 && !(currentFragment instanceof FriendRequestsFragment)) {
			FriendRequestsFragment friendRequestFragment = new FriendRequestsFragment();
			currentFragment = friendRequestFragment;
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, friendRequestFragment)
					.commit();
		} else if (position == 4 && !(currentFragment instanceof BlackListFragment)) {
			BlackListFragment blackListFragment = new BlackListFragment();
			currentFragment = blackListFragment;
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, blackListFragment).commit();
		} else if (position == 5 && !(currentFragment instanceof FavoritePlacesFragment)) {
			FavoritePlacesFragment favoritePlacesFragment = new FavoritePlacesFragment();
			currentFragment = favoritePlacesFragment;
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, favoritePlacesFragment)
					.commit();
		} else if (position == 6 && !(currentFragment instanceof MyProfileFragment)) {
			MyProfileFragment profileFragment = new MyProfileFragment();
			currentFragment = profileFragment;
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, profileFragment).commit();
		} else if (position == 7 && !(currentFragment instanceof SettingsFragment)) {
			SettingsFragment settingsFragment = new SettingsFragment();
			currentFragment = settingsFragment;
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, settingsFragment).commit();
		} else if(position == 8){
			//return to loginFragment
			this.askLogOut(this);
		}else if (position == 9) {
			askExit();
		} 
		mDrawerList.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(mDrawerList);

	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */
	
	@Override
	public void onBackPressed(){
		if(currentFragment instanceof ChatFragment){
			
			if(((ChatFragment)currentFragment).getPopupWindow().isShowing()){
				((ChatFragment)currentFragment).getPopupWindow().dismiss();
				FrameLayout.LayoutParams  baseChatLayoutParams = new FrameLayout.LayoutParams(
		                android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
				RelativeLayout baseChatLayout = (RelativeLayout)findViewById(R.id.base_chat_layout2);
				baseChatLayout.setLayoutParams(baseChatLayoutParams);
			}
			else{
				this.selectItem(1);
			}			
		}
		else if(currentFragment instanceof FriendProfileFragment){
			this.selectItem(2);
		}
		else if(currentFragment instanceof ConversationsListFragment ||
				currentFragment instanceof FriendsListFragment ||
				currentFragment instanceof BlackListFragment ||
				currentFragment instanceof FavoritePlacesFragment ||
				currentFragment instanceof FriendRequestsFragment ||
				currentFragment instanceof MyProfileFragment ||
				currentFragment instanceof SettingsFragment ||
				currentFragment instanceof SearchFragment){
			askExit();
		}
		else{
			finish();
		}
	    
	}
	
	public void setCurrentFragment(Fragment currentFragment){
		this.currentFragment=currentFragment;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	public ListView getDrawerList(){
		return this.mDrawerList;
	}

	
	public void askExit(){
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
		// Setting Dialog Title
		alertDialog2.setTitle("Conferma uscita");

		// Setting Dialog Message
		alertDialog2
				.setMessage("Sei sicuro di voler uscire dall'applicazione?");
		// Setting Positive "Yes" Btn
		alertDialog2.setPositiveButton("SI",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to execute after dialog
							//preferencesEditor.remove("loggedUser");
							//preferencesEditor.commit();
						finish();
					}
				});
		// Setting Negative "NO" Btn
		alertDialog2.setNegativeButton("NO",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to execute after dialog
						dialog.cancel();
					}
				});
		alertDialog2.show();
	}
	
	public void askLogOut(Activity gg){
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
		final Activity gg1 = gg;
		String PREFS_NAME = "MeetPreferFile";		
		storedInfo = this.getSharedPreferences(PREFS_NAME, 0);
		preferencesEditor = storedInfo.edit();
		// Setting Dialog Title
		alertDialog2.setTitle("Conferma Cambio Utente");

		// Setting Dialog Message
		alertDialog2
				.setMessage("Sei sicuro di voler disconnettere "+storedInfo.getString("loggedUser", ""));
		// Setting Positive "Yes" Btn
		alertDialog2.setPositiveButton("SI",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to execute after dialog
						preferencesEditor.remove("loggedUser");
						preferencesEditor.commit();
						
						//bisogna eseguire questo codice per spostarsi sulla LoginActivity
						gg1.startActivity(new Intent(gg1, LoginActivity.class));
						//this.finish();
						finish();
					}
				});
		// Setting Negative "NO" Btn
		alertDialog2.setNegativeButton("NO",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to execute after dialog
						dialog.cancel();
					}
				});
		alertDialog2.show();
	}
	


}