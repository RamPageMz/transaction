package code;

public class Data {
    /*
    *   数据库数据的属性
    *   name lock value
    * */
    public String name = "";
    public Boolean lock;
    public int value;
    public Transaction lockOfTransaction;   //记录上锁的事务名称

    /*
    *   constructor
    *   name 名称
    *   lock false  未上锁
    * */
    public Data(String name, Boolean lock, int value) {
        this.name = name;
        this.lock = lock;
        this.value = value;
    }

    /*
    *   上锁
    * */
    public void doLock(Transaction transaction) {
        this.lock = true;
        this.lockOfTransaction = transaction;
        //System.out.println(this.name + " lock");
    }

    public void writeWithLock(Transaction transaction, int value) {
        if (transaction == this.lockOfTransaction && this.lock) {
            this.value = value;
        }
    }

    /*
    *   解锁
    * */
    public void unlock(Transaction transaction) {
        if (transaction == this.lockOfTransaction && this.lock) {
            this.lock = false;
            this.lockOfTransaction = null;
            //System.out.println(this.name + " unlock");
        }
    }


    /*
    *
    * */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
