package code;

import java.util.Random;

public class Main {
    private static final int databaseSize = 10;        //数据库的数据个数
    private static final int transactionSize = 10;      //事务的个数
    private static final int transactionOperationSize = 10;      //每个事务的操作个数 随机值

    private static DataBase dataBase = new DataBase(databaseSize);
    private static Transaction[] transactionArray = new Transaction[transactionSize];


    public static void main(String[] args) {

        initInfo();


    }

    /*
     *  Name        :   initInfo
     *  Description :   初始化数据库信息和事务信息
     *  Parameter   :
     *  Return      :
     */
    public static void initInfo() {
        //准备数据库数据信息     总共有15个数据
        dataBase.initData();
        dataBase.showData();

        System.out.println("\n---  ---  事务信息  ---  ---\n");

        //准备事务信息
        //定义事务的操作信息  每个事务的操作数个数随机 操作对象随机
        for (int i = 0; i < transactionSize; i++) {       //生成10个事务
            //事务生成 创建名称 T1 T2...
            transactionArray[i] = new Transaction("T" + i);

            // 该事务有多少个操作 1-
            int actionSize = new Random().nextInt(transactionOperationSize) % (transactionSize - 1 + 1) + 1;
            int[] actionArray = new int[actionSize];
            for (int j = 0; j < actionSize; j++) {
                //对哪些数据操作随机生成
                actionArray[j] = new Random().nextInt(databaseSize);
            }
            transactionArray[i].dataObject = actionArray;
            transactionArray[i].setDataObjectValue();
            transactionArray[i].showOperationInfo();
            System.out.println("操作数: " + actionSize);

            System.out.println();
        }

        System.out.println("事务准备完毕\n");
    }

    /*
     *  Name        :   transactionInOrder
     *  Description :   顺序执行所有的事务 记录下数据库状态
     *  Parameter   :
     *  Return      :
     */
    public static void transactionInOrder() {
        System.out.println("\n--- --- --- 顺序执行事务 --- --- ---");

        dataBase.showData();

        //顺序执行每个事务
        for (int i = 0; i < transactionSize; i++) {

        }

    }

}
