package ham.crawler.support;

import ham.crawler.model.Link;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Support functional for the crawler
 *
 */
public class CrawlHelper {

	private static final int MAX_BODY_LENGTH = 50000;
	private static final Pattern LINK_PATTERN = Pattern.compile("<a [^>]*href=\"(.*?)\"");

	/**
	 * Return a list of link urls found in the page content
	 * 
	 * @param page
	 * @param parentUrl
	 * @return
	 */
	public List<String> getLinks(final String page, final String parentUrl) {
		List<String> result = new ArrayList<String>();

		Matcher linkMatcher = LINK_PATTERN.matcher(page.toLowerCase());
		while (linkMatcher.find()) {
			String anchorTag = linkMatcher.group();
			String link = StringUtils.substringAfter(anchorTag, "href=\"");
			link = StringUtils.removeEnd(link, "\"");
			if (!link.startsWith("#") && !link.equals("/")) {
				if (link.startsWith("/")) {
					link = getBaseUrl(parentUrl) + link;
				}
				result.add(link);
			}
		}
		return result;
	}

	/**
	 * Parse a url to discover base of the domain path for relative links
	 * 
	 * @param parentUrl
	 * @return
	 */
	public String getBaseUrl(final String parentUrl) {
		String protocol = StringUtils.substringBefore(parentUrl, "//") + "//";
		String result = StringUtils.substringAfter(parentUrl, "//");
		result = StringUtils.substringBeforeLast(result, "/");

		return protocol + result;
	}

	/**
	 * Read the contents of the page input stream
	 * 
	 * @param bodyInputStream
	 * @return
	 * @throws IOException
	 */
	public StringBuffer getPageContents(BufferedInputStream bodyInputStream) throws IOException {
		byte[] contents = new byte[1024];

		int bytesRead = 0;
		StringBuffer bodyContents = new StringBuffer();
		while ((bytesRead = bodyInputStream.read(contents)) != -1 && bodyContents.length() <= MAX_BODY_LENGTH) {
			bodyContents.append(new String(contents, 0, bytesRead));
		}
		return bodyContents;
	}

	/**
	 * Create a link model object from a provided URL and validate the url
	 * 
	 * @param url
	 * @return
	 */
	public Link createLink(String url) {
		Link link = new Link(url);
		try {
			URL formattedUrl = new URL(link.getUrl());
			link.setValid(true);
			link.setUrl(formattedUrl.toString());
		} catch (MalformedURLException e) {
			link.setValid(false);
		}
		return link;
	}
}
