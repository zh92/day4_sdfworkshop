package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {
    
    String dirPath = "data2";
    String fileName = "cookie.txt";

    List<String> cookieItems = null;

    public void readCookieFile() throws IOException {
        cookieItems = new ArrayList<String>();

        File file = new File(dirPath + File.separator + fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String readString;

        while((readString = br.readLine())!= null) {
            cookieItems.add(readString);
        }
    }

    public String returnCookie() {
        Random rand = new Random();
        if (cookieItems != null) {
            return cookieItems.get(rand.nextInt(cookieItems.size()));
        } else {
            return "There is no cookie found.";
        }
    }

    public void showCookies() {
        if (cookieItems != null) {
            cookieItems.forEach(c1 -> System.out.println(c1));
        }
    }
}
