package controllers.service;

import java.util.ArrayList;
import java.util.List;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.WebSocket;


public class ChatServer {

	private static List<WebSocket.Out<String>> connections = new ArrayList<WebSocket.Out<String>> ();

	public static void start(WebSocket.In<String> in, final WebSocket.Out<String> out) {

		connections.add (out);

		in.onMessage (new Callback<String> () {

			public void invoke(String event) {
				ChatServer.notifyAll (event);
			}
		});

		in.onClose (new Callback0 () {

			public void invoke() {
				out.write ("A connection closed");
			}
		});
	}

	public static void notifyAll(String message) {
		for (WebSocket.Out<String> out:connections) {
			out.write (message);
		}
	}
}
