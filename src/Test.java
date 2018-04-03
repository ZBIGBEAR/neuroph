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
        Test t = new Test1();
        (t.x)--;
        System.out.println(t.x);

    }
}
