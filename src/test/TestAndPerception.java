package test;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import perception.MyAndPerceptionNolearn;

import java.util.Arrays;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/19 12:24
 */
public class TestAndPerception {
    public static void main(String[]args){
        DataSet trainingSet = new DataSet(2,1);
        trainingSet.addRow(new DataSetRow(new double[]{0,0},new double[]{Double.NaN}));
        trainingSet.addRow(new DataSetRow(new double[]{1,0},new double[]{Double.NaN}));
        trainingSet.addRow(new DataSetRow(new double[]{0,1},new double[]{Double.NaN}));
        trainingSet.addRow(new DataSetRow(new double[]{1,1},new double[]{Double.NaN}));


        MyAndPerceptionNolearn perceptron = new MyAndPerceptionNolearn(2);

        for(DataSetRow row : trainingSet.getRows()){
            double[] networkInput = row.getInput();
            perceptron.setInput(networkInput);
            perceptron.calculate();
            double[] networkOutput = perceptron.getOutput();
            System.out.println(Arrays.toString(networkInput)+"="+Arrays.toString(networkOutput));
        }
    }
}
