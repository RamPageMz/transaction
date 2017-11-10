package code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestTran {
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
