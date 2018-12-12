package ru.job4j.sqlruparser;


public class Main {

    public static void main(String[] args) {
        PageParser pageParser = new PageParser();
        StoreSQL storeSQL = new StoreSQL();
        pageParser.firstStart();
        //pageParser.nextStart();
        storeSQL.setVacancyList(pageParser.getVacancyList());
        storeSQL.addVacancyList(storeSQL.getVacancyList());



        //pageParser.parserPages();


        /*try {
            Document doc = Jsoup.connect("http://www.sql.ru/forum/actualsearch.aspx?search=java&sin=1&bid=66&a=&ma=0&dt=356&s=4&so=1").get();
            String title = doc.title();
            Elements body = doc.select("a[href*=-java-]");
            Elements body1 = doc.select("a[href*=java]");
            Element element = body.get(0);
            String name = element.text();
            String url = element.absUrl("href");
            url = url.substring(0, url.length() - 8);
            Document doc1 = Jsoup.connect(url).get();
            Elements elements = doc1.getElementsByClass("msgBody");
            Element e1 = elements.get(1);
            Elements elements2 = doc1.getElementsByClass("msgFooter");
            Element e2 = elements2.get(0);
            String date = e2.text();
            date = date.split("\\[")[0];
            String text = e1.text();
            LocalDateTime dateTime = LocalDateTime.now();

            System.out.println(title);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
