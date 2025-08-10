import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client1 {
    public static void main(String[] args){
        Client1 client1=new Client1();
        for(int i=0;i<100;i++){
            try{
                Thread thread=new Thread(()->{
                    int port=8010;
                    try{
                        InetAddress address=InetAddress.getByName("localhost");
                        Socket socket=new Socket(address,port);
                        try{
                            PrintWriter toSocket=new PrintWriter(socket.getOutputStream());
                            BufferedReader fromSocket=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            toSocket.println("Hello from client "+socket.getLocalSocketAddress());
                            String line=fromSocket.readLine();
                            System.out.println("Response from server "+line);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
