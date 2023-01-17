package sg.edu.nus.iss;

import java.io.File;

public final class App {
    private App() {
    }
    public static void main(String[] args) {
        
        String dirPath = "data2";

        File newDirectory = new File(dirPath);
        if(newDirectory.exists()) {
            System.out.println("Directory already exists");
        } else {
            newDirectory.mkdir();
        }

    }
}
