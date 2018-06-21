package com.me.hatem.familytube.activities.tab_fregments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.hatem.familytube.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecentlyFragment extends Fragment {


  public RecentlyFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_recently, container, false);
  }

}
