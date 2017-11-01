package code;

import java.util.Random;

public class Transaction {

    public int randomSize=100;  //随机数范围大小

    public String name;

    public Random random=new Random();

    public Transaction(String name) {
        this.name = name;
    }

    public void read(Data data){
        System.out.println(this.name+" read "+data.name);
    }

    public void write(Data data,int value){
        System.out.println(this.name+" write "+data.name+" "+value);
        data.setValue(value);
    }
}
