package test;

import learningrule.BPAndOutputLearn;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;
import util.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/4/15 14:20
 */
public class TestYYSBByBPPerceptionLearn {
    public static void main(String[]args){
        long begin = System.currentTimeMillis();
        String trainFile = "resources"+ File.separator+"isolet1+2+3+4.data";
        DataSet ds = null;
        try {
            ds = Utils.getTrainingData(trainFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(ds==null){
            System.out.println("读取训练数据失败，filepath:"+trainFile);
            return;
        }
        BPAndOutputLearn perception = new BPAndOutputLearn(TransferFunctionType.SIGMOID,617,100,26);
        //设置可接受的误差
        BackPropagation learningRule = (BackPropagation)perception.getLearningRule();
        learningRule.setLearningRate(0.005);
        learningRule.setMaxError(0.005d);
        learningRule.setMaxIterations(150);
        long afterReadTrainData = System.currentTimeMillis();
        System.out.println("读完训练数据，"+(System.currentTimeMillis()-begin)/1000+"s");
        //开始学习
        System.out.println("begin learning...");
        perception.learn(ds);
        System.out.println("训练完毕，"+(System.currentTimeMillis()-begin)/1000+"s");
        System.out.println("begin test...");
        testPerception(perception);
        System.out.println("测试完毕，"+(System.currentTimeMillis()-begin)/1000+"s");
    }
    public static void testPerception(NeuralNetwork perception){
        String testFile = "resources"+File.separator+"isolet5.data";
        DataSet ds = null;
        try {
            ds = Utils.getTrainingData(testFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(ds==null){
            System.out.println("读取测试数据失败，filepath:"+testFile);
            return ;
        }
        int count=0;
        int total = ds.size();
        for(int i=0;i<ds.size();i++){
            perception.setInput(ds.getRowAt(i).getInput());
            perception.calculate();
            double[]output = Utils.competition(perception.getOutput());
            if(Utils.isEqual(output,ds.getRowAt(i).getDesiredOutput())){
                count++;
                System.out.println("========i="+i+",count="+count+",===========================================");
                System.out.println(Arrays.toString(output));
                System.out.println(Arrays.toString(ds.getRowAt(i).getDesiredOutput()));
                System.out.println("===================================================");
            }
            if(i%1000==0){
                System.out.println("正确率："+count*1.0/(i+1));
            }
        }
        System.out.println("正确率："+count*1.0/total);
    }
}
