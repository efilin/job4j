package ru.job4j.sqlruparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import java.util.Date;
import java.util.List;

public class PageParser {
    private List<Vacancy> vacancyList;
    private Date lastStartDate = null;

    //todo: QUARTZ for timing
    //todo: Jsoup for parsing
    //todo: Filtering(Java not JavaScript or Java Script)
    //todo: Check duplicates(same name)
    //todo: Check last start time of parsing(12h or 1y)


    //url = "http://www.sql.ru/forum/job-offers/$page$"
    //1y url = "http://www.sql.ru/forum/actualsearch.aspx?search=java&sin=1&bid=66&a=&ma=0&dt=356&s=4&so=1"
    //24h url = "http://www.sql.ru/forum/actualsearch.aspx?search=%22java%22&sin=1&bid=66&a=&ma=0&dt=-1&s=4&so=1"

}
