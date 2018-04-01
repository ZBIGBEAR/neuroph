package test;

import learningrule.PerceptronClassifyLearn;
import learningrule.PerceptronLearningRule;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.IterativeLearning;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/30 23:27
 */
public class TestClassifyLearn implements LearningEventListener {
    public void run(){
        DataSet trainingSet = new DataSet(2,1);
        trainingSet.addRow(new DataSetRow(new double[]{0,0},new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{1,0},new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{0,1},new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{1,1},new double[]{1}));

        PerceptronClassifyLearn perception = new PerceptronClassifyLearn(2);
        PerceptronLearningRule learningRule = (PerceptronLearningRule)perception.getLearningRule();
        learningRule.addListener(this);
        System.out.println("begin learning...");
        perception.learn(trainingSet);
        System.out.println("end learning...");

        Scanner scanner = new Scanner(System.in);
        String line = null;
        double[] input = new double[2];
        try{
            while((line = scanner.nextLine())!=null){
                String[]numbers = line.split(" ");
                input[0] = Double.parseDouble(numbers[0]);
                input[1] = Double.parseDouble(numbers[1]);

                perception.setInput(input);
                perception.calculate();
                double [] networkOutput = perception.getOutput();
                System.out.println(Arrays.toString(input)+"="+Arrays.toString(networkOutput));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new TestClassifyLearn().run();
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
        IterativeLearning bp = (IterativeLearning)event.getSource();
        System.out.println("iterate:"+bp.getCurrentIteration());
        System.out.println("weights:"+Arrays.toString(bp.getNeuralNetwork().getWeights()));
    }
}
