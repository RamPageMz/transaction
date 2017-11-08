package code;

import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Transaction {

    public int randomSize = 100;  //随机数范围大小

    public String name;

    public Random random = new Random();

    public int[] dataObject;

    public HashMap<Integer,Integer> map=new HashMap<Integer, Integer>();

    public Transaction(String name) {
        this.name = name;
    }

    // 存储该事务要操作的数据编号
    public void setDataObjectArray(int[] data) {
        dataObject = data;
    }

    // 将每个要操作的数据编号以及随机值写入map['编号','随机值']
    public void setDataObjectValue() {
        for (int i = 0; i < this.dataObject.length; i++) {
            this.map.put(this.dataObject[i],new Random().nextInt(100));
        }
    }

    // 遍历map 输出操作信息
    public void showOperationInfo(){
        System.out.println("Transaction "+this.name+" operation :");
        for (Map.Entry<Integer,Integer> entry :this.map.entrySet()){
            System.out.print("data"+entry.getKey()+" -> "+entry.getValue()+" | ");
        }
        System.out.println();
    }

    public void read(Data data) {
        System.out.println(this.name + " read " + data.name);
    }

    public void write(Data data, int value) {
        System.out.println(this.name + " write " + data.name + " " + value);
        data.setValue(value);
    }
}
