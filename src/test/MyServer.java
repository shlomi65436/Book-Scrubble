package test;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer{
    public int port;
    public ClientHandler ch;
    public volatile boolean stop;
    public boolean isRun;
    public MyServer(int port, ClientHandler ch) {
        this.port = port;
        this.ch = ch;
        stop = false;
        isRun = false;
    }
    public void start(){
       new Thread(()-> {
           try {
               runServer();
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       }).start();

    }
    public void close() {
        stop = true;
    }
    public void runServer() throws Exception{
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(1000);
        while(!stop){
            try{
                Socket aClient = server.accept();
                try{
                    ch.handleClient(aClient.getInputStream(),aClient.getOutputStream());

                    aClient.getInputStream().close();
                    aClient.getOutputStream().close();
                    aClient.close();
                    ch.close();
                }catch (IOException e){
                    e.printStackTrace();}
            }catch (SocketTimeoutException v){/**/};
        }server.close();
    }
}
