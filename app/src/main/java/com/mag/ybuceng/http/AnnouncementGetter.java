package com.mag.ybuceng.http;

import com.mag.ybuceng.Fragments.AnnouncementFragment;
import com.mag.ybuceng.models.Announcement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class AnnouncementGetter implements Runnable {
    private static ArrayList<Announcement> announcementList = new ArrayList<>();
    @Override
    public void run() {
        if(announcementList.size()>0){
            AnnouncementFragment.wait = false;
            return;
        }
        try {
            Document document = Jsoup.connect(URLs.ANNOUNCEMENTS.getUrl()).get();
            Elements elements = document.getElementsByClass("contentAnnouncements");
            Element announcementHeader = elements.first();
            Elements announcements = announcementHeader.getElementsByTag("a");
            Announcement announcement;
            for (int i=0; i<announcements.size()-1;i++){
                announcement = new Announcement();
                announcement.setHeader(announcements.get(i).text());
                announcement.setIndex(i);
                announcement.setUrl(URLs.ANNOUNCEMENTS.getUrl() + "/" + announcements.get(i).attr("href"));
                announcementList.add(announcement);
            }
            AnnouncementFragment.wait = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Announcement> getAnnouncements(){
        return announcementList;
    }
}
