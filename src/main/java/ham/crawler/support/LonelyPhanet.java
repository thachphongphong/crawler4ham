package ham.crawler.support;

import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by dtlinh on 3/3/2016.
 */
public class LonelyPhanet {
    public static void writeTextData(String path, String html) {
        Document doc = Jsoup.parse(html);

        try {
            String paths[] = path.split("/");
            String fileName = paths[paths.length - 1];

            String directory = HAMConstant.FOLDER + File.separator + fileName;
            if (!new File(directory).exists()) {
                new File(directory).mkdir();
            }
            FileWriter writer = new FileWriter(directory + File.separator + fileName + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            Connection c = SQLiteJDBC.connectiondb();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            //Name and category
//            Elements elements = doc.select(".card--page__header.card--page__header--headline.ttd__header");
            Elements elements = doc.select(".card--page__header.card--page__header--headline.ttd__header");
            //writeToFile(bufferedWriter, elements);
            //bufferedWriter.newLine();
            if (elements != null && elements.first() != null && elements.first().getElementsByTag("h1") != null) {
                String name = (elements.first().getElementsByTag("h1").hasText()) ? elements.first().getElementsByTag("h1").text() : "";
                String cat = (elements.first().getElementsByTag("a").hasText()) ? elements.first().getElementsByTag("a").text() : "";
                System.out.println(name);
                System.out.println(cat);


                //loaction
                elements = doc.select(".poi-map__toggle.js-poi-map-placeholder");
//            writeLocation(bufferedWriter, elements);
//            bufferedWriter.newLine();
                Element ele = elements.first();
                String lat = "";
                String lng = "";
                if (ele != null) {
                    String href = ele.attr("href");
                    String hrefs[] = StringUtils.split(href, "/");
                    for (String str : hrefs) {
                        if (str.contains(",")) {
                            String loc[] = StringUtils.split(str, ",");
                            lat = loc[0];
                            lng = loc[1];
                        }
                    }
                }
                System.out.println(lat);
                System.out.println(lng);

                //infomation
//            elements = doc.select(".ttd__aside.ttd__aside--atlas.js-ttd__aside");
//            writeToFile(bufferedWriter, elements);
                elements = doc.select(".ttd__aside.ttd__aside--atlas.js-ttd__aside");
                elements = elements.tagName("dl");
                Map<String, String> map = new HashMap<String, String>();
                for (Element element : elements) {
                    Elements elementTypeInfos = element.getElementsByTag("dt").tagName("dl");
                    Elements elementInfos = element.getElementsByTag("dd").tagName("dl");
                    for (int i = 0; i < elementTypeInfos.size(); i++) {
                        String k = elementTypeInfos.get(i).getElementsByTag("dl").text();
                        map.put(StringUtils.lowerCase(k), elementInfos.get(i).getElementsByTag("dl").text());
                    }
                }

                //description
                elements = doc.select(".ttd__section.ttd__section--description");
//            writeToFile(bufferedWriter, elements);
                String desc = elements.text();
                System.out.println(desc);

                bufferedWriter.close();
//
                PreparedStatement statement = c.prepareStatement(
                        "INSERT INTO hampoi VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
                statement.setString(2, name);
                statement.setString(3, cat);
                statement.setDouble(4, Double.valueOf(lat));
                statement.setDouble(5, Double.valueOf(lng));
                statement.setString(6, map.get(HAMConstant.HAM_LOCATION));
                statement.setString(7, map.get(HAMConstant.HAM_TELEPHONE));
                statement.setString(8, map.get(HAMConstant.HAM_ADDRESS));
                statement.setString(9, map.get(HAMConstant.HAM_PRICE));
                statement.setString(10, map.get(HAMConstant.HAM_OPENING));
                statement.setString(11, map.get(HAMConstant.HAM_NOTE));
                statement.setString(12, desc);

                statement.executeUpdate();
                c.commit();

                //Photo
                elements = doc.select("#js-tab-photos");
//            writeToImg(elements, directory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeLocation(BufferedWriter bufferedWriter, Elements elements) throws IOException {
        Element element = elements.first();
        if (element != null) {
            String href = element.attr("href");
            String hrefs[] = StringUtils.split(href, "/");
            for (String str : hrefs) {
                if (str.contains(",")) {
                    String loc[] = StringUtils.split(str, ",");
                    bufferedWriter.write("Map location");
                    bufferedWriter.newLine();
                    bufferedWriter.write(loc[0]);
                    bufferedWriter.newLine();
                    bufferedWriter.write(loc[1]);
                    bufferedWriter.newLine();
                }
            }
        }
    }

    private static void writeToFile(BufferedWriter bufferedWriter, Elements elements) throws IOException {
        for (Element element : elements) {
            if (element.children().size() > 0) {
                writeToFile(bufferedWriter, element.children());
            } else {
                bufferedWriter.write(element.text());
                bufferedWriter.newLine();
            }
        }
    }

    private static void writeToImg(Elements elements, String directory) throws IOException {
        for (Element element : elements) {
            String url = element.getElementsByTag("img").first().attr("src");
            String urlSrc[] = url.split("http:");
            if (urlSrc != null && urlSrc.length > 0) {
                url = "http:" + urlSrc[1];
                writeImgFile(url, directory);
            }
        }
    }

    public static void writeImgFile(String url, String directory) {
        Pattern IMAGE_PATTERN = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)");
        if (IMAGE_PATTERN.matcher(url).matches()) {
            try {
                HttpDownloadUtility.downloadFile(url, directory);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void writeImgData(Set<WebURL> links) {
        Pattern IMAGE_PATTERN = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)");
        for (WebURL link : links) {
            if (IMAGE_PATTERN.matcher(link.getURL()).matches()) {
                try {
                    HttpDownloadUtility.downloadFile(link.getURL(), HAMConstant.IMG_FOLDER);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static String getElementText(Elements elements) {
        String txt = "";
        for (Element element : elements) {
            txt = element.text();
        }
        return txt;
    }

}
