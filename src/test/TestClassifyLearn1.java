package test;

import learningrule.PerceptronClassifyLearn2;
import learningrule.PerceptronLearningRule;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.IterativeLearning;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/30 23:27
 */
class TestClassifyLearn2 implements LearningEventListener {
    private PerceptronClassifyLearn2 perceptron;
    private static Random r = new Random();
    public void run(){
        perceptron = new PerceptronClassifyLearn2(2);
        PerceptronLearningRule learningRule = (PerceptronLearningRule)perceptron.getLearningRule();
        learningRule.setMaxError(0.01);
        learningRule.addListener(this);
        //开始训练
        learn();
        //开始测试
        test();
    }
    public static void main(String[] args) {
        new TestClassifyLearn2().run();
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

    @Override
    public void handleLearningEvent(LearningEvent event) {
        /*
        IterativeLearning bp = (IterativeLearning)event.getSource();
        System.out.println("iterate:"+bp.getCurrentIteration());
        System.out.println("weights:"+Arrays.toString(bp.getNeuralNetwork().getWeights()));*/
    }
    private void learn(){
        System.out.println("begin traning...");
        //训练集
        DataSet trainingSet = new DataSet(2,2);
        for(int i=0;i<10000;i++){
            //第一象限
            trainingSet.addRow(new DataSetRow(new double[]{1*nextDouble(),1*nextDouble()},new double[]{1,1}));
            //第二象限
            trainingSet.addRow(new DataSetRow(new double[]{-1*nextDouble(),1*nextDouble()},new double[]{0,1}));
            //第三象限
            trainingSet.addRow(new DataSetRow(new double[]{-1*nextDouble(),-1*nextDouble()},new double[]{0,0}));
            //第四象限
            trainingSet.addRow(new DataSetRow(new double[]{1*nextDouble(),-1*nextDouble()},new double[]{1,0}));
        }
        perceptron.learn(trainingSet);
        System.out.println("end traning...");
    }
    private void test(){
        System.out.println("begin test...");
        DataSet testDataSet = new DataSet(2,2);
        for(int i=0;i<10000;i++){
            //第一象限
            testDataSet.addRow(new DataSetRow(new double[]{1*nextDouble(),1*nextDouble()},new double[]{1,1}));
            //第二象限
            testDataSet.addRow(new DataSetRow(new double[]{-1*nextDouble(),1*nextDouble()},new double[]{0,1}));
            //第三象限
            testDataSet.addRow(new DataSetRow(new double[]{-1*nextDouble(),-1*nextDouble()},new double[]{0,0}));
            //第四象限
            testDataSet.addRow(new DataSetRow(new double[]{1*nextDouble(),-1*nextDouble()},new double[]{1,0}));
        }
        int correctCount = 0;
        int incorrectCount = 0;
        for(DataSetRow dataSetRow:testDataSet.getRows()){
            perceptron.setInput(dataSetRow.getInput());
            perceptron.calculate();
            double[] output = perceptron.getOutput();
            if(Arrays.equals(output,dataSetRow.getDesiredOutput())){
                correctCount++;
            }else{
                incorrectCount++;
            }
        }
        System.out.println("正确率："+correctCount*1.0/(correctCount+incorrectCount));
        System.out.println("end test...");
    }
    public static double nextDouble() {
        double re = 0;
        while ((re = r.nextDouble()) != 0) {
            return re;
        }
        return r.nextDouble();
    }
}
