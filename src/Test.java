import util.Utils;

import java.util.Arrays;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/24 23:07
 */
public class Test {

    static {
        System.out.println("22222222222");
        int x = 5;
    }
    public Test(){
        System.out.println("111111111111");
    }
    private int x,y;

    public static void main(String[] args) {
        /*
        Test t = new Test1();
        (t.x)--;
        System.out.println(t.x);*/
        double[] test1 = new double[10];
        double[] test2 = new double[10];
        for(int i=0;i<test1.length;i++){
            if(i%3==0){
                test1[i]=1;
                test2[i]=1;
            }else{
                test1[i]=0;
                test2[i]=0;
            }
        }
        System.out.println(Arrays.toString(test1));
        System.out.println(Arrays.toString(test2));
        System.out.println(Utils.isEqual(test1,test2));

    }
}
