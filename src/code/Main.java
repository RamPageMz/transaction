package code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Main {
    private static final int databaseSize = 10;        //数据库的数据个数
    private static final int transactionSize = 10;      //事务的个数
    private static final int transactionOperationSize = 10;      //每个事务的操作个数 随机值
    private static final int dataValueSize = 100;       //对数据操作的value随机值上界

    private static DataBase dataBase = new DataBase(databaseSize);
    private static Transaction[] transactionArray = new Transaction[transactionSize];

    private static final Transaction[] transactionsThread = new Transaction[transactionSize];

    public static void main(String[] args) {

        initInfo();

        transactionInOrder();

        //System.out.println(Arrays.toString(randomSet(10,100)));

        transactionConcurrent();

    }

    /**********************      初始化对象     ********************** /

     /**
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

            // 该事务有多少个操作
            int actionSize = new Random().nextInt(transactionOperationSize) % (transactionSize - 1 + 1) + 1;

            //对哪些数据操作 编号--value   actionSize 操作个数   databaseSize 数据编号下标
            int[] actionArray = randomSet(actionSize, databaseSize);

            transactionArray[i].dataObject = actionArray;
            transactionArray[i].setDataObjectValue();
            transactionArray[i].showOperationInfo();
            System.out.println("操作数: " + actionSize);

            System.out.println();
        }

        System.out.println("事务准备完毕\n");
    }

    /**********************      顺序调用事务 展示结果     ********************** /
     *
     */
    /**
     * Name        :   transactionInOrder
     * Description :   顺序执行所有的事务 记录下数据库状态
     * Parameter   :
     * Return      :
     */
    public static void transactionInOrder() {
        System.out.println("\n--- --- --- 顺序执行事务 --- --- ---");

        dataBase.showData();

        //顺序执行每个事务
        for (int i = 0; i < transactionSize; i++) {
            System.out.println("事务" + i + " :");
            //直接使得每个事务对数据直接修改 不考虑锁的问题
            for (int j = 0; j < transactionArray[i].dataObject.length; j++) {
                int dataNo = transactionArray[i].dataObject[j];
                int value = transactionArray[i].map.get(dataNo);
                transactionWrite(transactionArray[i], dataBase.dataArray[dataNo], value);
            }
            System.out.println("\n");
        }

        System.out.println("#######################\n顺序执行后的结果：");
        dataBase.showData();
        System.out.println("#######################\n");

    }

    /**********************      不加锁 并发执行所有的事务 展示结果     ********************** /
     *
     */
    /**
     * Name        :   -transactionConcurrent
     * Description :   -并发执行所有事务 记录数据库状态
     * Parameter   :   -
     * Return      :   -
     */
    public static void transactionConcurrent() {
        // 将数据库清零
        dataBase.initData();

        //初始化final transactionsThread
        initFinalTransaction();

        //线程并发
        for (int i = 0; i < transactionSize; i++) {
            final int index=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //直接使得每个事务对数据直接修改 不考虑锁的问题
                    for (int j = 0; j < transactionsThread[index].dataObject.length; j++) {
                        int dataNo = transactionsThread[index].dataObject[j];
                        int value = transactionsThread[index].map.get(dataNo);
                        transactionWrite(transactionsThread[index], dataBase.dataArray[dataNo], value);
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n#######################\n不加锁 并发执行后的结果：");
        dataBase.showData();
        System.out.println("#######################\n");
    }


    /**********************      使用两段锁并发控制 展示结果     ********************** /
     *
     */
    /**
     *  Name        :   -transactionConcurrentWithLock
     *  Description :   -事务并发 使用锁控制
     *  Parameter   :   -
     *  Return      :   -
     */
    public static void transactionConcurrentWithLock(){
        dataBase.initData();
        initFinalTransaction();

        for (int i = 0; i < transactionSize; i++) {
            final int index=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //直接使得每个事务对数据直接修改 不考虑锁的问题
                    for (int j = 0; j < transactionsThread[index].dataObject.length; j++) {
                        int dataNo = transactionsThread[index].dataObject[j];
                        int value = transactionsThread[index].map.get(dataNo);
                        transactionWrite(transactionsThread[index], dataBase.dataArray[dataNo], value);
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n#######################\n加锁 并发执行后的结果：");
        dataBase.showData();
        System.out.println("#######################\n");

    }

    /**********************      以下为调用的函数     ********************** /

     /**
     * Method Name :   randomSet
     * Description :   随机生成不重复的数组
     * Parameter   :   num-数组大小    randomSize-随机数的上限
     * Return      :   int[] - 数组
     * Date        : 2017/11/9 17:27
     */
    public static int[] randomSet(int num, int randomSize) {
        int[] a = new int[num];
        HashSet<Integer> set = new HashSet<Integer>();

        // set 不能存储重复元素 当个数不满足时 不断add
        while (set.size() < num) {
            set.add(new Random().nextInt(randomSize));
        }

        int index_i = 0;
        for (Integer one : set) {
            a[index_i] = one;
            index_i++;
        }

        return a;
    }

    /**
     * Name        :   -transactionRead
     * Description :   -事务对数据的 读操作
     * Parameter   :   -t 事务名称     data 数据名称
     * Return      :   -
     */
    public static void transactionRead() {

    }

    /**
     * Name        :   -transactionWrite
     * Description :   -事务的 写操作
     * Parameter   :   -t 事务名称     data 数据名称       value 要写入的值
     * Return      :   -
     */
    public static void transactionWrite(Transaction t, Data data, int value) {
        System.out.print("事务" + t.name + " : " + data.name + " " + data.value + " -> " + value + "\t|| ");
        if (!data.lock) {   //lock == flase 没有上锁 则对数据修改
            data.value = value;
            System.out.println("success!");
        } else {
            System.out.println("fail! locked");
        }
    }

    /**
     *  Name        :   -transactionWriteWithLock
     *  Description :   -事务写操作 带锁
     *  Parameter   :   -t 事务名称     data 数据名称       value 要写入的值
     *  Return      :   -
     */
    public static void transactionWriteWithLock(Transaction t, Data data, int value) {

    }

    /**
     * Name        :   -initFinalTransaction
     * Description :   -初始化final类型的数组
     * Parameter   :   -
     * Return      :   -
     */
    public static void initFinalTransaction() {
        for (int i = 0; i < transactionSize; i++) {
            transactionsThread[i] = transactionArray[i];
        }

    }

}
