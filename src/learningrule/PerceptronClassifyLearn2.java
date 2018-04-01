package learningrule;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.comp.neuron.BiasNeuron;
import org.neuroph.nnet.comp.neuron.InputNeuron;
import org.neuroph.util.*;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/30 23:11
 */
public class PerceptronClassifyLearn2 extends NeuralNetwork{
    public PerceptronClassifyLearn2(int inputNeuronCount){
        createNetwork(inputNeuronCount);
    }
    private void createNetwork(int inputNeuronsCount){
        //设置网络类别为感知机
        this.setNetworkType(NeuralNetworkType.PERCEPTRON);
        //建立输入神经元
        NeuronProperties inputNeuronProperties = new NeuronProperties();
        inputNeuronProperties.setProperty("neuronType", InputNeuron.class);
        //创建输入层
        Layer inputLayer = LayerFactory.createLayer(inputNeuronsCount,inputNeuronProperties);
        inputLayer.addNeuron(new BiasNeuron());
        this.addLayer(inputLayer);

        //建立输出神经元
        NeuronProperties outputProperties = new NeuronProperties();
        outputProperties.setProperty("transferFunction", TransferFunctionType.STEP);

        //创建输出层
        Layer outputLayer = LayerFactory.createLayer(2,outputProperties);
        this.addLayer(outputLayer);

        //全连接
        ConnectionFactory.fullConnect(inputLayer,outputLayer);
        NeuralNetworkFactory.setDefaultIO(this);

        //定义一个学习规则
        PerceptronLearningRule myPerceptronLearningRule = new PerceptronLearningRule();
        //myPerceptronLearningRule.setMaxError(0.1);
        this.setLearningRule(myPerceptronLearningRule);
    }
}
