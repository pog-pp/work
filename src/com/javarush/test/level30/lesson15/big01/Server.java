package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by paul on 1/1/16.
 */
public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message){
        for(Map.Entry<String,Connection> connectionEntry: connectionMap.entrySet()){
            try {
                connectionEntry.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Don't send messaege to " + connectionEntry.getKey());
            }
        }
    }
    private static class Handler extends Thread{
        private Socket socket;
        public Handler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run()
        {
            String retname = null;
            try(Connection connection = new Connection(socket))  {//11.2.Создавать Connection, используя поле Socket
                ConsoleHelper.writeMessage("Connecting to port " + connection.getRemoteSocketAddress());//11.1. Выводить сообщение, что установлено новое соединение с удаленным адресом, который можно получить с помощью метода getRemoteSocketAddress
                String name = serverHandshake(connection);
                retname = name;
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, name));
                sendListOfUsers(connection,name);
                serverMainLoop(connection,name);



            }
            catch (IOException e)
            {
                ConsoleHelper.writeMessage("Error when happend exchange data");
            }
            catch (ClassNotFoundException e)
            {
                ConsoleHelper.writeMessage("Error when happend exchange data");
            }
            connectionMap.remove(retname);
            sendBroadcastMessage(new Message(MessageType.USER_REMOVED, retname));
            ConsoleHelper.writeMessage("Connection with server is closed");
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            Message answer = null;
            String name = null;

            while (true) {

                connection.send(new Message(MessageType.NAME_REQUEST));
                answer = connection.receive();
                if (answer.getType()==MessageType.USER_NAME) {
                    name = answer.getData();
                    if (name != "") {
                        if (!connectionMap.containsKey(name)) break;
                    }
                }
            }
            connectionMap.put(name,connection);
            connection.send(new Message(MessageType.NAME_ACCEPTED));
            return name;
        }
        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for(Map.Entry<String,Connection> connectionEntry: connectionMap.entrySet()){
                if (!userName.equals(connectionEntry.getKey())) {
                    connection.send(new Message(MessageType.USER_ADDED, connectionEntry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true)
            {
                Message mes = connection.receive();
                if (mes.getType() == MessageType.TEXT)
                {
                    String str = userName + ": " + mes.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, str));
                } else
                {
                    ConsoleHelper.writeMessage("This message don't TEXT");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        ConsoleHelper.writeMessage("Введите порт сервера");
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port);)
        {
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true)
            {

                    Socket socket = serverSocket.accept();
                    Handler handler = new Handler(socket);
                    handler.start();

            }
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Ошибка сокета");
        }

    }
}
