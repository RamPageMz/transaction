package code;

import java.util.Random;

public class Transaction {

    public int randomSize=100;  //随机数范围大小

    public String name;

    public Random random=new Random();

    public Transaction(String name) {
        this.name = name;
    }

    public void read(){
        System.out.println(name+" read");
    }

    public void write(){
        System.out.println(name+" write");

    }
}
