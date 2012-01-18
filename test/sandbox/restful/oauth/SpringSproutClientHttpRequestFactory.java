/**
 * Created by IntelliJ IDEA.
 * User: isyoon
 * Date: 2010. 7. 9
 * Time: 오전 4:52:44
 * enjoy springsprout ! development!
 */
package sandbox.restful.oauth;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;

public class SpringSproutClientHttpRequestFactory implements ClientHttpRequestFactory {

    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
		URLConnection urlConnection = uri.toURL().openConnection();
		Assert.isInstanceOf(HttpURLConnection.class, urlConnection);
		HttpURLConnection connection = (HttpURLConnection) urlConnection;
		prepareConnection(connection, httpMethod.name());
		return new SpringSproutClientHttpRequest(connection);
	}

	/**
	 * Template method for preparing the given {@link HttpURLConnection}. <p>The default implementation prepares the
	 * connection for input and output, and sets the HTTP method.
	 *
	 * @param connection the connection to prepare
	 * @param httpMethod the HTTP request method ({@code GET}, {@code POST}, etc.)
	 * @throws IOException in case of I/O errors
	 */
	protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
		connection.setDoInput(true);
		if ("GET".equals(httpMethod)) {
			connection.setInstanceFollowRedirects(true);
		}
		else {
			connection.setInstanceFollowRedirects(false);
		}
		if ("PUT".equals(httpMethod) || "POST".equals(httpMethod)) {
			connection.setDoOutput(true);
		}
		else {
			connection.setDoOutput(false);
		}
		connection.setRequestMethod(httpMethod);
	}
}
