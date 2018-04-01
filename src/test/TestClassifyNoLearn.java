package test;

import perception.PerceptronClassifyNoLearn;

import java.util.Arrays;
import java.util.Scanner;
/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/30 23:27
 */
public class TestClassifyNoLearn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = null;
        double[] input = new double[2];
        PerceptronClassifyNoLearn perception = new PerceptronClassifyNoLearn(2);
        try{
            while((line = scanner.nextLine())!=null){
                String[]numbers = line.split(" ");
                input[0] = Double.parseDouble(numbers[0]);
                input[1] = Double.parseDouble(numbers[1]);

                perception.setInput(input);
                perception.calculate();
                double [] networkOutput = perception.getOutput();
                System.out.println(Arrays.toString(input)+"="+posToString(networkOutput));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static String posToString(double[]input){
        if(input[0]==1){
            if(input[1]==1) {
                return "第一象限";
            }else if(input[1]==0){
                return "第四象限";
            }
        }else if(input[0]==0){
            if(input[1]==1){
                return "第二象限";
            }else if(input[1]==0){
                return "第三象限";
            }
        }
        return null;
    }
}
