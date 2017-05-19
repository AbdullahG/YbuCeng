package com.mag.ybuceng.http;

import android.widget.TextView;

import com.mag.ybuceng.Fragments.FoodMenuFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FoodListGetter implements Runnable{
    private static List<String> foodList = new ArrayList<>();
    @Override
    public void run() {
        try {
            Document document = Jsoup.connect(URLs.FOOD_LIST.getUrl()).get();
            Elements elements = document.select("tbody");
            Element foodTBody = elements.get(1);
            Elements foodItemList = foodTBody.getElementsByTag("h5");
            for (Element e:
                 foodItemList) {
                foodList.add(e.text());
            }
            FoodMenuFragment.wait = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getFoodList(){
        return foodList;
    }
}
