package com.me.hatem.familytube.activities.navigation_fregments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView; // important : only for searchview in fragment
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.me.hatem.familytube.R;
import com.me.hatem.familytube.activities.ViewPagerAdapter;
import com.me.hatem.familytube.activities.tab_fregments.HomeFragment;
import com.me.hatem.familytube.activities.tab_fregments.RecentlyFragment;
import com.me.hatem.familytube.activities.tab_fregments.TopFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowsFragment extends Fragment {

  View view;

  public ShowsFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    view =  inflater.inflate(R.layout.fragment_shows, container, false);
    // For View Pager and Tab Layout
    ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
    TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
    viewPagerAdapter.addFrament(new HomeFragment(),"Home");
    viewPagerAdapter.addFrament(new RecentlyFragment(),"Recently");
    viewPagerAdapter.addFrament(new TopFragment(),"Top");
    viewPager.setAdapter(viewPagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
    setHasOptionsMenu(true);
    return view;
  }

 /* @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.main_menu, menu);
    MenuItem searchItem = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) searchItem.getActionView();
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        filter(newText);
        return false;
      }
    });
    searchView.setQueryHint("Search");
    super.onCreateOptionsMenu(menu, inflater);
  }

  public void filter(String newText) {
    Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT).show();
  }*/


}
