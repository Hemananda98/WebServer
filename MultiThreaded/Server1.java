import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server1 {

    public Consumer<Socket> getConsumer(){
        return (clientSocket)->{
            try{
                PrintWriter toClient=new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Hello from server");
                toClient.close();
                clientSocket.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        };
    }

    public static void main(String[] args){
        int port=8010;
        Server1 server1=new Server1();
        try{
            ServerSocket serverSocket=new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is listening on port "+port);
            while (true){
                Socket acceptedSocket=serverSocket.accept();
                Thread thread=new Thread(()->server1.getConsumer().accept(acceptedSocket));
                thread.start();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
