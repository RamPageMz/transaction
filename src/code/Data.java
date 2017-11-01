package code;

public class Data {
    /*
    *   数据库数据的属性
    *   name lock value
    * */
    public String name="";
    public Boolean lock;
    public int value;

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
    public void doLock(){
        this.lock=true;
        System.out.println(this.name+" lock");
    }

    /*
    *   解锁
    * */
    public void unlock(){
        this.lock=false;
        System.out.println(this.name+" unlock");
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
