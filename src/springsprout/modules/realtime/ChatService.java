package springsprout.modules.realtime;

import org.springframework.web.client.RestTemplate;
import org.vertx.java.core.Handler;
import org.vertx.java.core.app.VertxApp;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientResponse;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.shared.SharedData;
import org.vertx.java.core.sockjs.AppConfig;
import org.vertx.java.core.sockjs.SockJSServer;
import org.vertx.java.core.sockjs.SockJSSocket;

import java.util.Set;

/**
 * @author Keesun Baik
 */
public class ChatService implements VertxApp {

	private final static String CHAT_OUT_URL = "http://springsprout.org/chat/out?sock=";

	HttpServer server;
	RestTemplate restTemplate;

	@Override
	public void start() throws Exception {
		restTemplate = new RestTemplate();

		server = new HttpServer();
		SockJSServer sockServer = new SockJSServer(server);

		final Set<SockJSSocket> conns = SharedData.getSet("conns");

		sockServer.installApp(new AppConfig().setPrefix("/chat"), new Handler<SockJSSocket>() {
			public void handle(final SockJSSocket sock) {
				conns.add(sock);
				sock.writeBuffer(Buffer.create("봄싹 채팅에 입장합니다."));
				System.out.println(sock + " connected");

				// open session
				String sockId = sock.toString();
				sock.writeBuffer(Buffer.create("sockId:" + sockId));

				sock.dataHandler(new Handler<Buffer>() {
					public void handle(Buffer data) {
						System.out.println(data + " received");

						// close session
						if (data.toString().startsWith("CLOSE")) {
							conns.remove(sock);
							String result = restTemplate.getForObject(CHAT_OUT_URL + sock, null, String.class);
							System.out.println(result);
							return;
						}

						// chat
						for (SockJSSocket sockJSSocket : conns) {
							System.out.println("Send to " + sockJSSocket);
							sockJSSocket.writeBuffer(data);
						}
					}
				});
			}
		});

		server.listen(8888);
	}

	@Override
	public void stop() throws Exception {
		server.close();
	}
}
