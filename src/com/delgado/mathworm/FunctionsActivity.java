package com.delgado.mathworm;

import java.util.Locale;

import com.delgado.mathworm.fragments.*;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FunctionsActivity extends Activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this
     * becomes too memory intensive, it may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_functions);

	// Create the adapter that will return a fragment for each of the three
	// primary sections of the activity.
	mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

	// Set up the ViewPager with the sections adapter.
	mViewPager = (ViewPager) findViewById(R.id.pager);
	mViewPager.setAdapter(mSectionsPagerAdapter);

	// Set up tabs in the ActionBar
	final ActionBar actionBar = getActionBar();
	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	// Create a tab listener that is called when the user changes tabs.
	ActionBar.TabListener tabListener = new ActionBar.TabListener() {
	    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
		// show the given tab
		mViewPager.setCurrentItem(tab.getPosition(), true);
	    }

	    public void onTabUnselected(ActionBar.Tab tab,
		    FragmentTransaction ft) {
		// hide the given tab
	    }

	    public void onTabReselected(ActionBar.Tab tab,
		    FragmentTransaction ft) {
		// probably ignore this event
	    }
	};

	// Add tabs
	actionBar.addTab(actionBar.newTab().setText(R.string.title_misc)
		.setTabListener(tabListener));
	actionBar.addTab(actionBar.newTab().setText(R.string.title_primes)
		.setTabListener(tabListener));
	actionBar.addTab(actionBar.newTab().setText(R.string.title_gcd)
		.setTabListener(tabListener));

	// Sync tabs and pages
	mViewPager
		.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
		    @Override
		    public void onPageSelected(int position) {
			// When swiping between pages, select the
			// corresponding tab.
			actionBar.setSelectedNavigationItem(position);
		    }
		});

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

	public SectionsPagerAdapter(FragmentManager fm) {
	    super(fm);
	}

	@Override
	public Fragment getItem(int position) {
	    switch (position) {
	    case 0:
		return new MiscFragment();
	    case 1:
		return new PrimesFragment();
	    case 2:
		return new GcdFragment();
	    }
	    return null;
	}

	@Override
	public int getCount() {
	    return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
	    Locale l = Locale.getDefault();
	    switch (position) {
	    case 0:
		return getString(R.string.title_misc).toUpperCase(l);
	    case 1:
		return getString(R.string.title_primes).toUpperCase(l);
	    case 2:
		return getString(R.string.title_gcd).toUpperCase(l);
	    }
	    return null;
	}
    }

}
