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
import it.meet.localdb.DatabaseAdministrator;

import java.util.Locale;

import it.meet.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import com.google.android.gms.maps.MapFragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    private static View rootView;
    private SearchFragment searchFragment;
    private LoginFragment loginFragment;
    private RegistrationFragment registrationFragment;
    private PlanetFragment planetFragment;
    private int lastPosition = -1;
    private DatabaseAdministrator dbAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
        this.deleteDatabase("meet.db");
        setDbAdmin(new DatabaseAdministrator(this));
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
        // If the nav drawer is open, hide action items related to the content view
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
        switch(item.getItemId()) {
	        case R.id.action_websearch:
	            // create intent to perform web search for this planet
	            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
	            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
	            // catch event that there's no activity to handle intent
	            if (intent.resolveActivity(getPackageManager()) != null) {
	                startActivity(intent);
	            } else {
	                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
	            }
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
        }
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
    	Log.w("Position","position="+position);
    	if(position == 0 && lastPosition !=0){
    		lastPosition = 0;
    		if(loginFragment == null){
    			loginFragment = new LoginFragment();
    		}
    		loginFragment.setRootView(rootView);
    		Bundle args = new Bundle();
	        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
	        loginFragment.setArguments(args);
	        
    		FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, loginFragment).commit();
    	}
    	else if(position == 2 && lastPosition !=2){
    		lastPosition = 2;
    		if(searchFragment == null){
    			searchFragment = new SearchFragment();
    		}
    		Bundle args = new Bundle();
	        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
	        searchFragment.setArguments(args);
    		FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, searchFragment).commit();
    	}
    	else if(position == 3 && lastPosition != 3){
    		lastPosition = 3;
    		//provo con username di prova 
    		//l'username � utilizzato per cercare i messaggi inviati e ricevuti da quel contatto
    		String username = "luigivorraro";//questo valore sara letto dall'utente selezionato
    		ChatFragment chatFragment = new ChatFragment();
    		chatFragment.setRemoteUsername(username);
    		chatFragment.setLocalUsername("tommasoalbano");//questo valore sara letto dall'utente corrente
    		Bundle args = new Bundle();
	        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
	        chatFragment.setArguments(args);
    		FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, chatFragment).commit();
    	}
    	else if(position == 8){
    		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
    		        this);

    		// Setting Dialog Title
    		alertDialog2.setTitle("Conferma uscita");

    		// Setting Dialog Message
    		alertDialog2.setMessage("Sei sicuro di voler uscire dall'applicazione?");


    		// Setting Positive "Yes" Btn
    		alertDialog2.setPositiveButton("SI",
    		        new DialogInterface.OnClickListener() {
    		            public void onClick(DialogInterface dialog, int which) {
    		                // Write your code here to execute after dialog
    		                Toast.makeText(getApplicationContext(),
    		                        "Hai scelto SI", Toast.LENGTH_SHORT)
    		                        .show();
    		            }
    		        });
    		// Setting Negative "NO" Btn
    		alertDialog2.setNegativeButton("NO",
    		        new DialogInterface.OnClickListener() {
    		            public void onClick(DialogInterface dialog, int which) {
    		                // Write your code here to execute after dialog
    		                Toast.makeText(getApplicationContext(),
    		                        "Hai scelto NO", Toast.LENGTH_SHORT)
    		                        .show();
    		                dialog.cancel();
    		            }
    		        });
    		alertDialog2.show();

    	}
    	else{/*
    		if(planetFragment == null){
    			planetFragment = new PlanetFragment();
    		}
	        Bundle args = new Bundle();
	        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
	        planetFragment.setArguments(args);
	
	        FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, planetFragment).commit();
	        */
    	}
        //Aggiorno e chiudo il drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
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

    /**
     * Fragment di prova dovra essere eliminato
     */
    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.planet_fragment, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.planets_array)[i];

            /*int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                            "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
            getActivity().setTitle(planet);*/
            return rootView;
        }
    }
    

	public DatabaseAdministrator getDbAdmin() {
		return dbAdmin;
	}

	public void setDbAdmin(DatabaseAdministrator dbAdmin) {
		this.dbAdmin = dbAdmin;
	}

    
    
    
}