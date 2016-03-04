package ham.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import ham.crawler.support.HAMConstant;
import ham.crawler.support.LonelyPhanet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by dtlinh on 3/2/2016.
 */
public class HAMCrawler extends WebCrawler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HAMCrawler.class);

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.startsWith(HAMConstant.URL_HOIAN);
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        int docid = page.getWebURL().getDocid();
        String url = page.getWebURL().getURL();
        String domain = page.getWebURL().getDomain();
        String path = page.getWebURL().getPath();
        String subDomain = page.getWebURL().getSubDomain();
        String parentUrl = page.getWebURL().getParentUrl();
        String anchor = page.getWebURL().getAnchor();

        logger.info("Docid: {}", docid);
        logger.info("URL: {}", url);
        logger.info("Domain: '{}'", domain);
        logger.info("Sub-domain: '{}'", subDomain);
        logger.info("Path: '{}'", path);
        logger.info("Parent page: {}", parentUrl);
        logger.info("Anchor text: {}", anchor);

        Pattern CLASS_PATTERN = Pattern.compile("<div class=\\\"card--page__content\\\">(.*?)</div>");
        logger.info("BEGIN =============>");
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            // String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
//            Set<WebURL> links = htmlParseData.getOutgoingUrls();

//            logger.info("Text length: {}", text.length());
//            logger.info("Html length: {}", html.length());
//            logger.info("Number of outgoing links: {}", links.size());
            LonelyPhanet.writeTextData(path, html);
//            LonelyPhanet.writeImgData(links);
        }
        logger.info("END <=============");
    }


}
