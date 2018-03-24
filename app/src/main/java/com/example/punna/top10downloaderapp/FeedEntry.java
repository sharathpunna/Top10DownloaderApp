package com.example.punna.top10downloaderapp;

/**
 * Created by punna on 2/11/2018.
 */

public class FeedEntry {
    private String name;
    private String artist;
    private String summary;
    private String releasedate;
    private String imageurl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "name'" + name + '\n' +
                ", artist='" + artist + '\n' +
                ", releasedate='" + releasedate + '\n' +
                ", imageurl='" + imageurl + '\n';
    }
}
