package util;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.io.*;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/4/15 14:09
 */
public class Utils {
    public static DataSet getTrainingData(String trainFile) throws FileNotFoundException,IOException{
        DataSet ds = new DataSet(617,26);
        File trainF = new File(trainFile);
        BufferedReader br = new BufferedReader(new FileReader(trainF));
        String line = null;
        while( (line = br.readLine()) !=null){
            String[] numbers = line.substring(0,line.length()-1).split(",");
            double[] input = new double[numbers.length-1];
            double[] output = new double[26];
            for(int i=0;i<output.length;i++){
                output[i]=0;
            }
            for(int i=0;i<input.length;i++){
                input[i] = Double.parseDouble(numbers[i].trim());
            }
            output[Integer.parseInt(numbers[numbers.length-1].trim())-1] = 1;
            DataSetRow dsr = new DataSetRow(input,output);
            ds.addRow(dsr);
        }
        return ds;
    }
    public static double[]competition(double[]in){
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
                out[i]=1;
            }else{
                out[i]=0;
            }
        }
        return out;
    }
    public static boolean isEqual(double[] in,double[]out){
        for(int i=0;i<in.length;i++){
            if(in[i]!=out[i]){
                return false;
            }
        }
        return true;
    }
}
