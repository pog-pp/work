package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by paul on 1/2/16.
 */
public class Client
{

    protected Connection connection;
    private volatile boolean clientConnected = false;
    public class SocketThread extends Thread{
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + "  connected to chat");
        }
        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + "  have leave the chat");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected=clientConnected;
            synchronized (Client.this){
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException,ClassNotFoundException{
            while (true){
               Message mes =  connection.receive();
                if (mes.getType() == MessageType.NAME_REQUEST)
                {
                    String name = getUserName();
                    Message messageUserName = new Message(MessageType.USER_NAME, name);
                    connection.send(messageUserName);

                }else if (mes.getType() == MessageType.NAME_ACCEPTED)
                {
                    notifyConnectionStatusChanged(true);
                    return;
                }else
                {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }
        protected void clientMainLoop() throws IOException,ClassNotFoundException{
            while (true)
            {
                Message mes = connection.receive();
                if (mes.getType() == MessageType.TEXT)
                {
                    processIncomingMessage(mes.getData());
                } else if (mes.getType() == MessageType.USER_ADDED)
                {
                    informAboutAddingNewUser(mes.getData());
                } else if (mes.getType() == MessageType.USER_REMOVED)
                {
                    informAboutDeletingNewUser(mes.getData());
                } else
                {
                    throw new IOException("Unexpected MessageType");

                }
            }
        }

        public void run(){
            String serverAdress = getServerAddress();
            int id = getServerPort();
            try
            {
                Socket socket = new Socket(serverAdress,id);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }
            catch (IOException e)
            {
                notifyConnectionStatusChanged(false);
            }
            catch (ClassNotFoundException e)
            {
                notifyConnectionStatusChanged(false);
            }
        }
    }
    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Please write IP");
        return ConsoleHelper.readString();
    }

    protected int getServerPort(){
        ConsoleHelper.writeMessage("Please write port");
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        ConsoleHelper.writeMessage("Please write your name");
        return ConsoleHelper.readString();
    }
    protected boolean shouldSentTextFromConsole(){
        return true;
    }
    protected SocketThread getSocketThread(){
        return new SocketThread();
    }
    protected void sendTextMessage(String text){
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Connection error");
            clientConnected = false;
        }
    }

    public void run()
    {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try
        {
            synchronized (this)
            {
                this.wait();
            }
        }
        catch (InterruptedException e)
        {
            ConsoleHelper.writeMessage("Wait error");
            return;
        }

        if (clientConnected){
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        }else{
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }

        String message;
        while (clientConnected)
        {
            if (!(message = ConsoleHelper.readString()).equalsIgnoreCase("exit"))
            {
                if (shouldSentTextFromConsole())
                {
                    sendTextMessage(message);
                }
            } else
            {

                break;
            }
        }
    }

    public static void main(String[] args) {//14.2. Добавь метод main()
        Client client = new Client();
        client.run();
    }

}
