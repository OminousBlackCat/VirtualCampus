package com.seu.vCampus.util;

import java.util.Date;

public class News extends Message {
    private String URL;
    private String newsTitle;
    private Date newsDate;

    public News(){this.Type = MESSAGE_TYPE.TYPE_NULL;}
    public News(String URL,String title,Date date){
        this.URL = URL;
        this.newsTitle = title;
        this.newsDate = date;
    }

    public String getURL() { return URL; }

    public String getNewsTitle() { return newsTitle; }

    public Date getNewsDate() { return newsDate; }

    public void setURL(String URL) { this.URL = URL; }

    public void setNewsTitle(String newsTitle) { this.newsTitle = newsTitle; }

    public void setNewsDate(Date newsData) { this.newsDate = newsData; }
}
