package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public final class App {
    public static void main(String[] args) throws IOException{
        
        String dirPath = "data2";

        File newDirectory = new File(dirPath);
        if(newDirectory.exists()) {
            System.out.println("Directory already exists");
        } else {
            newDirectory.mkdir();
        }

        Cookie cookie = new Cookie();
        cookie.readCookieFile();
        // cookie.showCookies();

        ServerSocket ss = new ServerSocket(12345);
        Socket s = ss.accept();

        try (InputStream is = s.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            String msgReceived = "";

            try(OutputStream os = s.getOutputStream()) {
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);

                while (!msgReceived.equals("close")) {
                    msgReceived = dis.readUTF();
    
                    if(msgReceived.equalsIgnoreCase("get-cookie")) {
                        String cookieValue = cookie.returnCookie();
                        System.out.println(cookieValue);

                        dos.writeUTF(cookieValue);
                        dos.flush();
                    }
                }
                bos.close();
                dos.close();
                os.close();
            }
            dis.close();
            bis.close();
        } catch (EOFException ex) {
            s.close();
            ss.close();
        }
    }
}
