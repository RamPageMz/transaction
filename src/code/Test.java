package code;

import java.util.Arrays;

public class Test {
    public static void main(String[] args){
        Transaction transaction=new Transaction("tran1");
        int[] aa=new int[3];
        for (int i=0;i<3;i++){
            aa[i]=i;
        }
        transaction.dataObject=aa;

        System.out.println(Arrays.toString(aa));
    }

}
