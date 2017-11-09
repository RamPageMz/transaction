package code;

import code.*;

public class DataBase {
    /*
    *   信息包括
    *   数据库数据个数 数据大小 （随机生成） boolean 是否上锁
    *
    *   对数据的更改 记录更改的事务名称
    *   锁：某个事务对某数据上锁
    *       read 可以访问  不可以write
    * */

    public int dataBaseSize;

    public Data[] dataArray;


    /*
    *   constructor
    *   params:
    *       dataBaseSize: 数据库大小
    * */
    public DataBase(int dataBaseSize) {
        this.dataBaseSize = dataBaseSize;
    }

    public void initData() {
        dataArray = new Data[this.dataBaseSize];

        for (int i = 0; i < dataBaseSize; i++) {
            dataArray[i] = new Data("data" + i, false, 0);
        }

        System.out.println("数据库准备完毕");
    }

    /*
    *   输出所有的数据
    * */
    public void showData() {
        System.out.println("\n数据信息:");
        for (int i = 0; i < dataBaseSize; i++) {
            System.out.print(dataArray[i].name + ":" + dataArray[i].value + "  ");
        }
        System.out.println();
    }


    /*
    *   getter setter
    * */
    public int getDataBaseSize() {
        return dataBaseSize;
    }

    public void setDataBaseSize(int dataBaseSize) {
        this.dataBaseSize = dataBaseSize;
    }

    public Data[] getDataArray() {
        return dataArray;
    }

    public void setDataArray(Data[] dataArray) {
        this.dataArray = dataArray;
    }
}
