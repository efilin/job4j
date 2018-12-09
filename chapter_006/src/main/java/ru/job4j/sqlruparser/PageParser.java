package ru.job4j.sqlruparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PageParser {
    private List<Vacancy> vacancyList = new ArrayList<>();
    private LocalDateTime lastStartDate;
    ArrayList<String> sdate;

    //todo: QUARTZ for timing
    //todo: Jsoup for parsing
    //todo: Filtering(Java not JavaScript or Java Script)
    //todo: Check duplicates(same name)
    //todo: Check last start time of parsing(12h or 1y)

    public PageParser() {
        this.lastStartDate = LocalDateTime.now();
    }


    //url = "http://www.sql.ru/forum/job-offers/$page$"
    //1y url = "http://www.sql.ru/forum/actualsearch.aspx?search=java&sin=1&bid=66&a=&ma=0&dt=356&s=4&so=1"
    //24h url = "http://www.sql.ru/forum/actualsearch.aspx?search=%22java%22&sin=1&bid=66&a=&ma=0&dt=1&s=4&so=1"

    public void parserPages() {
        try {
            Document doc = Jsoup.connect("http://www.sql.ru/forum/actualsearch.aspx?search=java&sin=1&bid=66&a=&ma=0&dt=356&s=4&so=1").get();
            String title = doc.title();
            Elements body = doc.select("a[href*=java]");
            int i =0;
            for (Element element : body) {
                if (checkContainsJava(element.text())) {
                    i++;
                    String name = element.text();
                    String url = element.absUrl("href");
                    url = url.substring(0, url.length() - 8);
                    Element e1 = Jsoup.connect(url).get().getElementsByClass("msgBody").get(1);
                    String text = e1.text();
                    Element e2 = Jsoup.connect(url).get().getElementsByClass("msgFooter").get(0);
                    String date = e2.text();
                    String date1 = date.split("\\[")[0];
                    String d = null;



                    //LocalDateTime date = parseTime(stringDate);
                    //vacancyList.add(new Vacancy(i,name,text,url,date));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LocalDateTime parseTime(String date) {
        LocalDateTime result=null;
        if(date.contains("сегодня") || date.contains("вчера")) {
            String dates[] = date.split(",");
            DateTimeFormatter formatter =DateTimeFormatter.ofPattern(" HH:mm    ");
            //сегодня:"сегодня, 20:41    "
            if (dates[0].equalsIgnoreCase("сегодня")) {
                result = LocalDateTime.of(LocalDateTime.now().toLocalDate(),LocalTime.parse(dates[1], formatter));
                return result;
            }
            //вчера:"вчера, 15:36    "
            result = LocalDateTime.of(LocalDateTime.now().toLocalDate().minusDays(1),LocalTime.parse(dates[1], formatter));
            return result;
        }
        //обычное время тип: "4 дек 18, 17:39    " 
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("d MMM yy, HH:mm    ");
        result = LocalDateTime.parse(date, formatter);
        return result;
    }

    public boolean checkContainsJava(String text) {
        return text.toLowerCase().contains("java");
    }
}
