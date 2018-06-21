package com.me.hatem.familytube.activities.tab_fregments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.me.hatem.familytube.R;
import com.me.hatem.familytube.models.Video;
import com.me.hatem.familytube.services.RVA;
import com.me.hatem.familytube.services.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

  View view;
  RecyclerView rvVideos;
  VolleyRequest volleyRequest;
  List<Video> vList;
  RVA RV;
  SwipeRefreshLayout swipeRefreshLayout;
  //String dataURL = "http://10.0.2.2/API/ANDROID/data.txt";

  String baseURL = "http://192.168.0.101";
  String dataURL = baseURL+"/API/ANDROID/data.txt";
  /* **************************************************** */
  public HomeFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    view                = inflater.inflate(R.layout.fragment_home, container, false);
    volleyRequest       = new VolleyRequest(getContext());
    rvVideos            = (RecyclerView) view.findViewById(R.id.rvVideos);
    swipeRefreshLayout  = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
    swipeRefreshLayout.setColorSchemeResources(R.color.colorFinishRefresh);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        videoLoader();
      }
    });
    videoLoader();
    setHasOptionsMenu(true);
    return view;
  }

  private void videoLoader() {
    vList  = new ArrayList<>();
    volleyRequest.GET(new VolleyRequest.VolleyCallback() {
      @Override
      public void onSuccess(String result) {
        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        JSONObject jsonObject = null;
        JSONArray jsonResult = null;
        try {
          jsonResult = new JSONArray(result);
        } catch (JSONException e) {
          e.printStackTrace();
        }

        for (int i=0; i<result.length(); i++) {
          try {
            jsonObject = jsonResult.getJSONObject(i);
            Video video = new Video();
            video.setTitle(jsonObject.getString("title"));
            video.setTag(jsonObject.getString("tag"));
            video.setDescription(jsonObject.getString("description"));
            video.setThumbnailURL(baseURL+jsonObject.getString("thumbnailURL"));
            video.setVideoURL(baseURL+jsonObject.getString("videoURL"));
            video.setSubtitleURI(baseURL+jsonObject.getString("subtitleURI"));
            vList.add(video);
          } catch (JSONException e) {
            e.printStackTrace();
          }
          setRVadapter(vList);
          swipeRefreshLayout.setRefreshing(false); // Stop the refresh
        }
      }
    }, dataURL,null);
  }

  public void setRVadapter(List<Video> vList) {
    RV = new RVA(getContext(),vList);
    rvVideos.setHasFixedSize(true);
    rvVideos.setLayoutManager(new LinearLayoutManager(getContext()));
    rvVideos.setAdapter(RV);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.main_menu, menu);
    MenuItem searchItem = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) searchItem.getActionView();
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        RV.filter(query);
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        RV.filter(newText);
        return true;
      }
    });
    searchView.setQueryHint("Search");
    super.onCreateOptionsMenu(menu, inflater);
  }

 /* public void filter(String newText) {
    List<Video> searchedList = null;
    if (!newText.isEmpty()) {
      for (Video video: vList) {
        if (video.getTitle().toLowerCase().contains(newText)){
          searchedList.add(video);
          Toast.makeText(getContext(), video.getTitle(), Toast.LENGTH_SHORT).show();
        }

      }

      //Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT).show();
    }
    //if (searchedList.size() > 0)
    RVA RV = new RVA(getContext(),searchedList);
    RV.notifyDataSetChanged();
  }*/
}
