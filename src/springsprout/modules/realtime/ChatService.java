package springsprout.modules.realtime;

import org.jboss.netty.buffer.ChannelBuffer;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.app.VertxApp;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.net.NetServer;
import org.vertx.java.core.shared.SharedData;
import org.vertx.java.core.sockjs.AppConfig;
import org.vertx.java.core.sockjs.SockJSServer;
import org.vertx.java.core.sockjs.SockJSSocket;

import java.util.Set;

/**
 * @author Keesun Baik
 */
public class ChatService implements VertxApp {

	HttpServer server;

	@Override
	public void start() throws Exception {
		server = new HttpServer();

		SockJSServer sockServer = new SockJSServer(server);
		
		final Set<SockJSSocket> conns = SharedData.getSet("conns"); 

		sockServer.installApp(new AppConfig().setPrefix("/chat"), new Handler<SockJSSocket>() {
			public void handle(final SockJSSocket sock) {
				conns.add(sock);
				sock.writeBuffer(Buffer.create("봄싹 채팅에 입장합니다."));
				System.out.println(sock + " connected");

				sock.dataHandler(new Handler<Buffer>() {
					public void handle(Buffer data) {
						if(data.toString().equals("CLOSE")) {
							conns.remove(sock);
						}

						System.out.println(data + " received");
						for(SockJSSocket sockJSSocket : conns) {
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
	}
}
