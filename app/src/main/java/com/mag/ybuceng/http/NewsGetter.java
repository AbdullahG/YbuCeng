package com.mag.ybuceng.http;

import com.mag.ybuceng.Fragments.NewsFragment;
import com.mag.ybuceng.models.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NewsGetter implements Runnable{
    private static ArrayList<News> newsList = new ArrayList<>();

    @Override
    public void run() {
        if(newsList.size() > 0){
            NewsFragment.wait = false;
            return;
        }
        try {
            Document document = Jsoup.connect(URLs.NEWS.getUrl()).get();
            Elements elements = document.getElementsByClass("contentNews");
            Element newsHeader = elements.first();
            Elements news = newsHeader.getElementsByTag("a");
            News newsItem;
            for(int i=0;i<news.size()-1; i++){
                newsItem = new News();
                newsItem.setUrl(URLs.NEWS.getUrl() + "/" + news.get(i).attr("href"));
                newsItem.setIndex(i);
                newsItem.setHeader(news.get(i).text());
                newsList.add(newsItem);
            }
            NewsFragment.wait = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<News> getNews(){
        return newsList;
    }
}
