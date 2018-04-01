package learningrule;

import org.neuroph.core.Connection;
import org.neuroph.core.Neuron;
import org.neuroph.core.Weight;
import org.neuroph.core.learning.SupervisedLearning;

import java.io.Serializable;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/31 23:17
 */
public class PerceptronLearningRule extends SupervisedLearning implements Serializable {
    public PerceptronLearningRule(){}


    protected void updateNetworkWeights(double[] outputError) {
        int i=0;
        for(Neuron neuron:neuralNetwork.getOutputNeurons()){
            neuron.setError(outputError[i]);
            double neuronError = neuron.getError();
            //根据所有的神经元输入迭代学习
            for (Connection connection: neuron.getInputConnections()){
                //神经元的一个输入
                double input = connection.getInput();
                //计算权值的变更
                double weightChange = neuronError*input;
                //更新权值
                Weight weight = connection.getWeight();
                weight.weightChange = weightChange;
                weight.value +=weightChange;
                //System.out.println("error:"+neuronError+"weight:"+weight.value);
            }
            //System.out.println("i="+i);
            i++;
        }
    }
}
