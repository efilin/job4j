package ru.job4j.sqlruparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class PageParser {
    private static final String URL_YEAR =
            "http://www.sql.ru/forum/actualsearch.aspx?search=java&sin=1&bid=66&a=&ma=0&dt=1000&s&pg=";
    private static final String URL_DAY =
            "http://www.sql.ru/forum/actualsearch.aspx?search=%22java%22&sin=1&bid=66&a=&ma=0&dt=1&s=4&so=1";
    private static boolean stopParseFlag = false;
    private List<Vacancy> vacancyList = new LinkedList<>();
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());


    public void firstStart() {
        stopParseFlag = false;
        int i = 0;
        while (!stopParseFlag) {
            i++;
            parserPages(URL_YEAR + i);
        }
        LOG.info("add one-year vacancies  to vacancyList");
    }

    public void nextStart() {
        stopParseFlag = false;
        parserPages(URL_DAY);
        LOG.info("add one-day vacancies  to vacancyList");
    }

    public void parserPages(String parseUrl) {
        try {
            Document doc = Jsoup.connect(parseUrl).get();
            Elements body = doc.select("a[href*=java]");
            for (Element element : body) {
                if (checkContainsJava(element.text())) {
                    String name = element.text();
                    String url = element.absUrl("href");
                    url = url.substring(0, url.length() - 8);
                    Element e1 = Jsoup.connect(url).get().getElementsByClass("msgBody").get(1);
                    String text = e1.text();
                    Element e2 = Jsoup.connect(url).get().getElementsByClass("msgFooter").get(0);
                    String stringDate = e2.text().split("....\\[")[0];
                    LocalDateTime createDate = parseTime(stringDate);
                    if (createDate.isBefore(LocalDateTime.now().minusYears(1))) {
                        stopParseFlag = true;
                        break;
                    }
                    vacancyList.add(new Vacancy(name, text, url, createDate));
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public LocalDateTime parseTime(String date) {
        LocalDateTime result;
        if (date.contains("сегодня") || date.contains("вчера")) {
            String[] dates = date.split(",");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" HH:mm");
            //сегодня:"сегодня, 20:41"
            if (dates[0].equalsIgnoreCase("сегодня")) {
                result = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.parse(dates[1], formatter));
                return result;
            }
            //вчера:"вчера, 15:36"
            result = LocalDateTime.of(LocalDateTime.now().toLocalDate().minusDays(1), LocalTime.parse(dates[1], formatter));
            return result;
        }
        //обычное время тип: "4 дек 18, 17:39" 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy, HH:mm");
        if (date.contains("май")) {
            date = date.replace("май", "мая");
        }
        result = LocalDateTime.parse(date, formatter);
        return result;
    }

    public void clearVacancyList() {
        this.vacancyList.clear();
    }

    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }

    public boolean checkContainsJava(String text) {
        return text.toLowerCase().contains("java") && !text.toLowerCase().contains("script");
    }
}
