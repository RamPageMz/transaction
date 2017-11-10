package code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Main {
    private static final int databaseSize = 10;        //数据库的数据个数
    private static final int transactionSize = 10;      //事务的个数
    private static final int transactionOperationSize = 10;      //每个事务的操作个数 随机值

    private static DataBase dataBase = new DataBase(databaseSize);
    private static Transaction[] transactionArray = new Transaction[transactionSize];


    public static void main(String[] args) {

        initInfo();

        //transactionInOrder();

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
        for (int i = 0; i < 1; i++) {       //生成10个事务
            //事务生成 创建名称 T1 T2...
            transactionArray[i] = new Transaction("T" + i);

            // 该事务有多少个操作 1-
            int actionSize = new Random().nextInt(transactionOperationSize) % (transactionSize - 1 + 1) + 1;
            System.out.println("action:"+actionSize);
            int[] actionArray = new int[actionSize];
            for (int j = 0; j < actionSize; j++) {
                //对哪些数据操作随机生成
                actionArray[j] = new Random().nextInt(databaseSize);
            }
            System.out.println(Arrays.toString(actionArray));
            transactionArray[i].dataObject = actionArray;
            System.out.println(Arrays.toString(transactionArray[i].dataObject));
            transactionArray[i].setDataObjectValue();
            transactionArray[i].showOperationInfo();
            System.out.println("操作数: " + actionSize);

            System.out.println();
        }

        System.out.println("事务准备完毕\n");
    }

    /**
     *  Method Name :   randomSet
     *  Description :   随机生成不重复的数组
     *  Parameter   :   num-数组大小    randomSize-随机数的上限
     *  Return      :   int[] - 数组
     *  Date        : 2017/11/9 17:27
     */
    public static int[] randomSet(int num,int randomSize){
        int[] a=new int[num];
        HashSet<Integer> set=new HashSet<Integer>();

        for (int i=0;i<num;i++){
            set.add(new Random().nextInt(randomSize));
        }

        if (set.size()<num){
            //递归
        }

        return a;
    }

    /*
     *  Name        :   -transactionRead
     *  Description :   -事务对数据的 读操作
     *  Parameter   :   -t 事务名称     data 数据名称
     *  Return      :   -
     */
    public static void transactionRead() {

    }

    /*
     *  Name        :   -transactionWrite
     *  Description :   -事务的 写操作
     *  Parameter   :   -t 事务名称     data 数据名称       value 要写入的值
     *  Return      :   -
     */
    public static void transactionWrite(Transaction t, Data data, int value) {
        System.out.print("事务"+t.name+" : "+data.name+" "+data.value+" -> "+value+"\t|| ");
        if (!data.lock){   //lock == flase 没有上锁 则对数据修改
            data.value=value;
            System.out.println("success!");
        }else{
            System.out.println("fail! locked");
        }
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
            System.out.println("事务"+i+" :");
            //直接使得每个事务对数据直接修改 不考虑锁的问题
            for (int j=0;j<transactionArray[i].dataObject.length;j++){
                int dataNo=transactionArray[i].dataObject[j];
                int value=transactionArray[i].map.get(dataNo);
                transactionWrite(transactionArray[i],dataBase.dataArray[dataNo],value);
            }
            System.out.println("\n");
        }

    }

}
