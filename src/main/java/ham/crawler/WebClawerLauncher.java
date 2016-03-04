package ham.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import ham.crawler.support.HAMConstant;

/**
 * Created by dtlinh on 3/2/2016.
 */
public class WebClawerLauncher {
    /**
     * Launch the web crawler
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
//        if (args.length == 1 || args.length == 3) {
//            Web4Crawler crawler;
//            if (args.length == 3) {
//                crawler = new Web4Crawler(args[1], Integer.parseInt(args[2]));
//            } else {
//                crawler = new Web4Crawler();
//            }
//            crawler.crawl(args[0]);
//            crawler.report();
//        } else {
//            System.out.println("Invalid Arguments - A single url is required.");
//        }

        /* crawlStorageFolder is a folder where intermediate crawl data is
                * stored.
                */
        String crawlStorageFolder = HAMConstant.CRAWLSTORAGEFOLDER;

    /*
     * numberOfCrawlers shows the number of concurrent threads that should
     * be initiated for crawling.
     */
        int numberOfCrawlers = HAMConstant.NUMBEROFCRAWLERS;

        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorageFolder);

    /*
     * Be polite: Make sure that we don't send more than 1 request per
     * second (1000 milliseconds between requests).
     */
        config.setPolitenessDelay(1000);

    /*
     * You can set the maximum crawl depth here. The default value is -1 for
     * unlimited depth
     */
        config.setMaxDepthOfCrawling(10);

    /*
     * You can set the maximum number of pages to crawl. The default value
     * is -1 for unlimited number of pages
     */
        config.setMaxPagesToFetch(100);

        /**
         * Do you want crawler4j to crawl also binary data ?
         * example: the contents of pdf, or the metadata of images etc
         */
        config.setIncludeBinaryContentInCrawling(false);

    /*
     * Do you need to set a proxy? If so, you can use:
     * config.setProxyHost("proxyserver.example.com");
     * config.setProxyPort(8080);
     *
     * If your proxy also needs authentication:
     * config.setProxyUsername(username); config.getProxyPassword(password);
     */

    /*
     * This config parameter can be used to set your crawl to be resumable
     * (meaning that you can resume the crawl from a previously
     * interrupted/crashed crawl). Note: if you enable resuming feature and
     * want to start a fresh crawl, you need to delete the contents of
     * rootFolder manually.
     */
        config.setResumableCrawling(false);

    /*
     * Instantiate the controller for this crawl.
     */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

    /*
     * For each crawl, you need to add some seed urls. These are the first
     * URLs that are fetched and then the crawler starts following links
     * which are found in these pages
     */
        //   controller.addSeed(HAMConstant.URL);
        controller.addSeed(HAMConstant.URL_SIGHTS);
        //  controller.addSeed("http://www.ics.uci.edu/~welling/");

    /*
     * Start the crawl. This is a blocking operation, meaning that your code
     * will reach the line after this only when crawling is finished.
     */
        controller.start(HAMCrawler.class, numberOfCrawlers);
    }
}
