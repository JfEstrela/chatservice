package controllers;

import play.mvc.Controller;
import play.mvc.WebSocket;
import controllers.service.ChatServer;

public class Application extends Controller {

	public WebSocket<String> index() {
		return new WebSocket<String> () {
			public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
				ChatServer.start (in, out);
			}
		};
    }
}
