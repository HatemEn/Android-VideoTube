package com.me.hatem.familytube.models;

public class Video {
  private int id;
  private String title;
  private String description;
  private String date;
  private String tag;
  private String group;
  private String byWhom;
  private String thumbnailURL;
  private String videoURL;
  private String subtitleURI;

  public Video () {

  }

  public Video(int id, String title, String description, String date, String tag,
               String group, String byWhom, String thumbnailURL, String videoURL,String subtitleURI) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.date = date;
    this.tag = tag;
    this.group = group;
    this.byWhom = byWhom;
    this.thumbnailURL = thumbnailURL;
    this.videoURL = videoURL;
    this.subtitleURI = subtitleURI;
  }

  /* Getter */

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getDate() {
    return date;
  }

  public String getTag() {
    return tag;
  }

  public String getGroup() {
    return group;
  }

  public String getByWhom() {
    return byWhom;
  }

  public String getThumbnailURL() {
    return thumbnailURL;
  }

  public String getVideoURL() {
    return videoURL;
  }

  public String getSubtitleURI() {
    return subtitleURI;
  }
  /* Setter */

  public void setId(int id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public void setByWhom(String byWhom) {
    this.byWhom = byWhom;
  }

  public void setThumbnailURL(String thumbnailURL) {
    this.thumbnailURL = thumbnailURL;
  }

  public void setVideoURL(String videoURL) {
    this.videoURL = videoURL;
  }

  public void setSubtitleURI(String subtitleURI) {
    this.subtitleURI = subtitleURI;
  }
}
