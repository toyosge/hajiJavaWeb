import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by masahirayamamoto on 2016/08/11.
 */
public class TcpClient {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 8001);
             FileInputStream fis = new FileInputStream("client_send.txt");
             FileOutputStream fos = new FileOutputStream("client_recv.txt")) {

            int ch;
            // client_send.txtの内容をサーバーに登録
            OutputStream output = socket.getOutputStream();
            while (((ch = fis.read()) != -1)){
                output.write(ch);
            }

            output.write(0);
            InputStream input = socket.getInputStream();
            while (((ch = input.read()) != -1)){
                fos.write(ch);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
