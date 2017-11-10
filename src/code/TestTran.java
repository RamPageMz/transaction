package code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestTran {
    public static void main(String[] args) {
        int i;
        for (i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("Thread ");
                    for (int j=0;j<5;j++){
                        System.out.print(j+" | ");
                    }
                    System.out.println();
                }
            }).start();
        }
    }

}
