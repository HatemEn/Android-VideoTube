/* RVA : Recycler View Adapter*/
package com.me.hatem.familytube.services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.me.hatem.familytube.R;
import com.me.hatem.familytube.activities.VideoPlayerActivity;
import com.me.hatem.familytube.models.Video;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class RVA extends android.support.v7.widget.RecyclerView.Adapter<RVA.MyViewHolder> {
  private Context context;
  private List<Video> vList;
  private List<Video> tempList;

  public RVA(Context context, List<Video> vList) {
    this.context = context;
    this.vList   = vList;
    tempList = vList;
  }

  @Override
  public RVA.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view;
    LayoutInflater inflater = LayoutInflater.from(context);
    view                    = inflater.inflate(R.layout.video_item,parent,false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RVA.MyViewHolder holder, final int position) {
    holder.tvTitle.setText(vList.get(position).getTitle());
    holder.tvCategories.setText(vList.get(position).getTag());
    holder.tvDescription.setText(vList.get(position).getDescription());
    Picasso.with(context).load(vList.get(position).getThumbnailURL()).into(holder.ivThumbnail);
    holder.video_item_selector.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(context, vList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        Intent play = new Intent(context, VideoPlayerActivity.class);
        play.putExtra("videoURL",vList.get(position).getVideoURL());
        play.putExtra("subtitleURI",vList.get(position).getSubtitleURI());
        context.startActivity(play);
      }
    });
  }

  @Override
  public int getItemCount() {
    return vList.size();
  }

  public void filter(String text) {
   // Important function
    vList = new ArrayList<>();
    if (text.isEmpty()) {
      vList.addAll(tempList);
    } else {
      for(Video video: tempList) {
        text = text.toLowerCase();
        if (video.getTitle().toLowerCase().contains(text) ||
          video.getTag().toLowerCase().contains(text) ||
          video.getTag().toLowerCase().contains(text)) {
          vList.add(video);
        }
      }
    }
    notifyDataSetChanged();
  }

  public class MyViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder{
    TextView tvTitle,tvCategories,tvDescription;
    LinearLayout video_item_selector;
    ImageView ivThumbnail;
    public MyViewHolder(View itemView) {
      super(itemView);
      ivThumbnail         = (ImageView) itemView.findViewById(R.id.ivThumbnail);
      tvTitle             = (TextView) itemView.findViewById(R.id.tvTitle);
      tvCategories        = (TextView) itemView.findViewById(R.id.tvCategories);
      tvDescription       = (TextView) itemView.findViewById(R.id.tvDescription);
      video_item_selector = (LinearLayout) itemView.findViewById(R.id.video_item_selector);

    }
  }

}
