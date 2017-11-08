package code;

import java.util.Random;

public class Main {
    private static final int databaseSize = 15;        //数据库的数据个数
    private static final int transactionSize = 10;      //事务的个数
    private static final int transactionOperationSize = 9;      //每个事务的操作个数 随机值

    public static void main(String[] args) {
        System.out.println("Begin!");

        //随机化变量展示  -- 以防出现很多0 1 2 这种没参考意义的数据
        //每个事务有多少组操作
        //int transactionActionSizeRandom=new Random().nextInt(transactionOperationSize);
        //System.out.println("");

        //准备数据库数据信息     总共有15个数据
        DataBase dataBase = new DataBase(databaseSize);
        dataBase.initData();
        dataBase.showData();

        //准备事务信息
        //定义事务的操作信息  每个事务的操作数个数随机 操作对象随机
        Transaction[] transactionArray = new Transaction[transactionSize];
        for (int i = 0; i < transactionSize; i++) {       //生成10个事务
            //事务生成 创建名称 T1 T2...
            transactionArray[i] = new Transaction("T" + i);

            // 该事务有多少个操作 1-
            int actionSize=new Random().nextInt(transactionOperationSize)%(transactionSize-1+1)+1;
            int[] actionArray=new int[actionSize];
            for (int j = 0; j < actionSize; j++) {
                //对哪些数据操作随机生成
                actionArray[j]=new Random().nextInt(databaseSize);
            }
            transactionArray[i].dataObject=actionArray;
            transactionArray[i].setDataObjectValue();
            transactionArray[i].showOperationInfo();
            System.out.println("action: "+actionSize);

            System.out.println();
        }

        System.out.println("Transaction prepare");


    }
}
