package ru.job4j.sqlruparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            Document doc = Jsoup.connect("http://www.sql.ru/forum/actualsearch.aspx?search=java&sin=1&bid=66&a=&ma=0&dt=356&s=4&so=1").get();
            String title = doc.title();
            Elements body = doc.select("a[href*=java-]");
            Elements body1 = doc.select("a[href]");

            Element element = body.get(0);
            String name = element.text();
            String url = element.absUrl("href");
            url = url.substring(0, url.length() - 8);
            Document doc1 = Jsoup.connect(url).get();
            Elements elements = doc1.getElementsByClass("msgBody");
            Element e1 = elements.get(1);
            String text = e1.text();
            System.out.println(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
