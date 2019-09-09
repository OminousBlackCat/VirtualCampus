package com.seu.vCampus.util;

import java.util.Date;

public class News extends Message {
    private String URL;
    private String newsTitle;
    private Date newsDate;

    public News(){this.Type = MESSAGE_TYPE.TYPE_NULL;}

    public String getURL() { return URL; }

    public String getNewsTitle() { return newsTitle; }

    public Date getNewsData() { return newsDate; }

    public void setURL(String URL) { this.URL = URL; }

    public void setNewsTitle(String newsTitle) { this.newsTitle = newsTitle; }

    public void setNewsData(Date newsData) { this.newsDate = newsData; }
}
