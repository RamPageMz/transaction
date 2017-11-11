package code;

public class Concurrent {

    public int length;      //transaction的操作数大小

    public int nowIndex;    //当前已经完成的数量

    public Transaction transaction;

    public Concurrent(int length, int nowIndex, Transaction transaction) {
        this.length = length;
        this.nowIndex = nowIndex;
        this.transaction = transaction;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getNowIndex() {
        return nowIndex;
    }

    public void setNowIndex(int nowIndex) {
        this.nowIndex = nowIndex;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
