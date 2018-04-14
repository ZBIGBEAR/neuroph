package test;

import learningrule.BPAndOutputLearn;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.util.TransferFunctionType;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/19 12:24
 */
public class TestJOByBPAndOutputLearn {
    public static double[] int2double(int i){
        double[] ret = new double[32];
        for(int j=0;j<32;j++){
            ret[j]=(double) ((i>>j)&1);
        }
        return ret;
    }
    public static double[] int2Prop(int i){
        //正偶數
        double[] pe = {0d,0d,0d,1d};
        //負偶數
        double[] ne= {0d,0d,1d,0d};
        //正奇數
        double[] po = {0d,1d,0d,0d};
        //負奇數
        double[] no = {1d,0d,0d,0d};
        if(i%2==0){
            if(i>0){
                return pe;
            }else if(i<0){
                return ne;
            }
        }else{
            if(i>0){
                return po;
            }else{
                return no;
            }
        }
        return pe;
    }
    public static void main(String[]args){

        //创建一个神经网络，step函数作为输入函数，两个输入，两个中间神经元（一个中间输出层），一个输出神经元
        BPAndOutputLearn perceptron = new BPAndOutputLearn(TransferFunctionType.SIGMOID,32,10,4);

        //训练数据,2000个
        DataSet trainingSet = new DataSet(32,4);
        for(int i=0;i<2000;i++){
            int in = new Random().nextInt();
            trainingSet.addRow(new DataSetRow(int2double(in),int2Prop(in)));
        }
        System.out.println("begin traning...");
        perceptron.learn(trainingSet);
        System.out.println("begin test...");
        testNeuralNetword(perceptron);
    }
    public static void testNeuralNetword(BPAndOutputLearn perception){
        int count=0;
        int NUM=500000;
        //对50000个随机点判断
        for(int i=0;i<NUM;i++){
            int in = new Random().nextInt();
            perception.setInput(int2double(in));
            perception.calculate();
            double []output = competition(perception.getOutput());
            double[]correct = int2Prop(in);
            //System.out.println(Arrays.toString(correct)+"\t"+ Arrays.toString(output));
            if(isEqual(correct,output)){
                count++;
            }
        }
        System.out.println(count*1.0/NUM);
    }
    public static boolean isEqual(double[] in,double[]out){
        for(int i=0;i<in.length;i++){
            if(in[i]!=out[i]){
                return false;
            }
        }
        return true;
    }
    public static double[] competition(double[]in){
        double[]out = new double[in.length];
        double max=Double.MIN_VALUE;
        int index=0;
        for(int i=0;i<in.length;i++){
            if(in[i]>max){
                max=in[i];
                index=i;
            }
        }
        for(int i=0;i<out.length;i++){
            if(i==index){
                out[i]=1d;
            }else{
                out[i]=0d;
            }
        }
        return out;
    }
}
