package test;

import learningrule.MIPerceptronAndOutputNoLearn;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.util.TransferFunctionType;
import perception.MyAndPerceptionNolearn;

import java.util.Arrays;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/19 12:24
 */
public class TestMIPerceptronAndOutputNoLearn {
    public static void main(String[]args){
        DataSet trainingSet = new DataSet(2,1);
        trainingSet.addRow(new DataSetRow(new double[]{0,0},new double[]{Double.NaN}));
        trainingSet.addRow(new DataSetRow(new double[]{1,0},new double[]{Double.NaN}));
        trainingSet.addRow(new DataSetRow(new double[]{0,1},new double[]{Double.NaN}));
        trainingSet.addRow(new DataSetRow(new double[]{1,1},new double[]{Double.NaN}));

        //创建一个神经网络，step函数作为输入函数，两个输入，两个中间神经元（一个中间输出层），一个输出神经元
        MIPerceptronAndOutputNoLearn perceptron = new MIPerceptronAndOutputNoLearn(TransferFunctionType.STEP,2,2,1);

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
