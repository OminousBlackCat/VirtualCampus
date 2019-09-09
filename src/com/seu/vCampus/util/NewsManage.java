package com.seu.vCampus.util;

import java.util.ArrayList;
import java.util.List;

public class NewsManage extends News {
    private List<News> news=new ArrayList<News>();

    public NewsManage(){this.Type = MESSAGE_TYPE.TYPE_NULL;}

    public List<News> getNews() { return news; }
    public void addNews(News n){ news.add(n); }
}
