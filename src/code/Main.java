package code;
import java.util.Random;

public class Main {
    private static final int databaseSize  = 20;        //数据库的数据个数
    private static final int transactionSize = 10;      //事务的个数
    private static final int transactionOperationSize = 8;      //每个事务的操作个数 随机值

    public static void main(String[] args) {
        System.out.println("Begin!");

        //准备数据库数据信息
        DataBase dataBase=new DataBase(new Random().nextInt(databaseSize));
        dataBase.initData();
        dataBase.showData();

        //准备事务信息
        //定义事务的操作信息  每个事务的操作数个数随机
        Transaction[] transactionArray=new Transaction[transactionSize+1];
        for (int i=1;i<=transactionSize;i++){
            transactionArray[i]=new Transaction("T"+i);


        }






        System.out.println("Transaction prepare");



    }
}
