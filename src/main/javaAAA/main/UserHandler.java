package main;

import dronesimulation.DroneManager;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * Created by mark on 25/03/17.
 */

@WebSocket
public class UserHandler {

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
//        String username = "User" + Chat.nextUserNumber++;
//        Chat.userUsernameMap.put(user, username);
//        Chat.broadcastMessage(sender = "Server", msg = (username + " joined the chat"));
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
//        String username = Chat.userUsernameMap.get(user);
//        Chat.userUsernameMap.remove(user);
//        Chat.broadcastMessage(sender = "Server", msg = (username + " left the chat"));
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
        if (message.equalsIgnoreCase("init")){
            DroneProject.initPacket(user);
        }else{
            DroneManager.move(Integer.parseInt(message));
            DroneProject.send(user, Integer.parseInt(message));
        }
    }


}
