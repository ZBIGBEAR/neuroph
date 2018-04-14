package test;

import learningrule.BPAndOutputLearn;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.util.TransferFunctionType;

import java.util.Arrays;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/19 12:24
 */
public class TestBPAndOutputLearn {
    public static void main(String[]args){

        DataSet trainingSet = new DataSet(2,1);
        trainingSet.addRow(new DataSetRow(new double[]{0,0},new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{1,0},new double[]{1}));
        trainingSet.addRow(new DataSetRow(new double[]{0,1},new double[]{1}));
        trainingSet.addRow(new DataSetRow(new double[]{1,1},new double[]{0}));

        //创建一个神经网络，step函数作为输入函数，两个输入，两个中间神经元（一个中间输出层），一个输出神经元
        BPAndOutputLearn perceptron = new BPAndOutputLearn(TransferFunctionType.SIGMOID,2,4,1);
        //perceptron.getLearningRule().addListener(this);
        perceptron.learn(trainingSet);
        //测试异或神经网络
        for(DataSetRow row : trainingSet.getRows()){
            double[] networkInput = row.getInput();
            perceptron.setInput(networkInput);
            perceptron.calculate();
            double[] networkOutput = perceptron.getOutput();
            System.out.println(Arrays.toString(networkInput)+"="+Arrays.toString(networkOutput));
        }
    }

}
