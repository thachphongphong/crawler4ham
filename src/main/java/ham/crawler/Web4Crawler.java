package ham.crawler;

import ham.crawler.model.Link;
import ham.crawler.support.CrawlHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Execute web crawl from a base url and report on the results.
 * <p/>
 * Breadth first crawl
 */
public class Web4Crawler {
    // HTTTP connection timeout (seconds)
    private static final int DEFAULT_TIMEOUT = 10;
    // the maximum links to follow while crawling
    private static final int MAX_LINKS = 50;

    private static final String REPORT_STATUS_INVALID = "INVALID";
    private static final String REPORT_LINE = "%s - %s";

    private HttpClient client;
    private CrawlHelper helper;

    // unique links crawled
    private LinkedHashSet<Link> knownLinks = new LinkedHashSet<Link>(50);
    // newly discovered links through page crawls
    private Queue<String> newLinks = new LinkedList<String>();

    /**
     * Create a web crawler with default Http Client configurations
     */
    public Web4Crawler() {
        this(null, 0);
    }

    /**
     * Create a web crawler with Http Client configurations which include proxy
     * informaions
     *
     * @param proxyHost
     * @param proxyPort
     */
    public Web4Crawler(final String proxyHost, final int proxyPort) {
        this.helper = new CrawlHelper();
        Builder configBuilder = RequestConfig.custom().setSocketTimeout(DEFAULT_TIMEOUT * 1000)
                .setConnectTimeout(DEFAULT_TIMEOUT * 1000).setConnectionRequestTimeout(DEFAULT_TIMEOUT * 1000);

        if (StringUtils.isNotEmpty(proxyHost)) {
            configBuilder.setProxy(new HttpHost(proxyHost, proxyPort));
        }
        RequestConfig config = configBuilder.build();
        client = HttpClients.custom().setDefaultRequestConfig(config).build();

        client = HttpClients.custom().setDefaultRequestConfig(config).build();
    }

    /**
     * Constructor for testing purposes
     *
     * @param helper
     * @param client
     */
    protected Web4Crawler(final CrawlHelper helper, final HttpClient client) {
        this.helper = helper;
        this.client = client;
    }

    /**
     * Execute the crawler on the url provided
     *
     * @param rootUrl
     */
    public void crawl(String rootUrl) {
        newLinks.add(rootUrl);
        while (knownLinks.size() < MAX_LINKS && !newLinks.isEmpty()) {
            Link link = helper.createLink(newLinks.remove());
            if (!knownLinks.contains(link)) {
                knownLinks.add(link);
                if (link.isValid()) {
                    followLink(link);
                }
            }

        }
    }

    /**
     * Make an HTTP GET all to the link provided and discover the links
     * available
     *
     * @param link
     */
    private void followLink(Link link) {
        HttpResponse response;
        try {
            HttpGet httpGet = new HttpGet(link.getUrl());
            response = client.execute(httpGet);
            link.setResponseCode(response.getStatusLine().getStatusCode());

            InputStream bodyInputStream = response.getEntity().getContent();

            try {
                BufferedInputStream bufferedInput = new BufferedInputStream(bodyInputStream);
                StringBuffer bodyContents = helper.getPageContents(bufferedInput);
                newLinks.addAll(helper.getLinks(bodyContents.toString(), link.getUrl()));
            } catch (IOException e) {
                // do nothing
            } finally {
                bodyInputStream.close();
            }
        } catch (ClientProtocolException e) {
            link.setValid(false);
        } catch (ConnectionPoolTimeoutException e) {
            link.setResponseCode(404);
        } catch (IllegalStateException e) {
            link.setValid(false);
        } catch (IOException e) {
            link.setValid(false);
        } catch (Exception e) {
            link.setValid(false);
        }
    }

    /**
     * Output the results of the crawl
     */
    public void report() {
        for (Link link : knownLinks) {
            String response = Integer.toString(link.getResponseCode());
            if (!link.isValid()) {
                response = REPORT_STATUS_INVALID;
            }
            System.out.println(String.format(REPORT_LINE, response, link.getUrl()));
        }
    }
}
