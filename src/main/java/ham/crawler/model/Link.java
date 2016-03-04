package ham.crawler.model;

/**
 * representation of a link for reporting purposes
 *
 */
public class Link {

	private String url;
	private int responseCode;
	private boolean valid;

	public Link(String url) {
		this.url = url;
		this.valid = true;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid
	 *            the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof Link) && ((Link) o).getUrl().equals(this.getUrl());
	}

	@Override
	public int hashCode() {
		return url.hashCode();
	}
}
