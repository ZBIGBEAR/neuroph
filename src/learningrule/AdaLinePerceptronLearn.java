package learningrule;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.comp.neuron.BiasNeuron;
import org.neuroph.nnet.comp.neuron.InputNeuron;
import org.neuroph.nnet.learning.LMS;
import org.neuroph.util.*;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/30 23:11
 */
public class AdaLinePerceptronLearn extends NeuralNetwork{
    public AdaLinePerceptronLearn(int inputNeuronCount,int outputNeuronCount)
    {
        createNetwork(inputNeuronCount,outputNeuronCount);
    }
    private void createNetwork(int inputNeuronsCount,int outputNeuronCount){
        //设置网络类别为感知机
        this.setNetworkType(NeuralNetworkType.ADALINE);
        //建立输入神经元
        NeuronProperties inputNeuronProperties = new NeuronProperties();
        inputNeuronProperties.setProperty("transferFunction", TransferFunctionType.LINEAR);
        //创建输入层
        Layer inputLayer = LayerFactory.createLayer(inputNeuronsCount,inputNeuronProperties);
        inputLayer.addNeuron(new BiasNeuron());
        this.addLayer(inputLayer);

        //建立输出神经元
        NeuronProperties outputProperties = new NeuronProperties();
        outputProperties.setProperty("transferFunction", TransferFunctionType.LINEAR);

        //创建输出层
        Layer outputLayer = LayerFactory.createLayer(outputNeuronCount,outputProperties);
        this.addLayer(outputLayer);

        //全连接
        ConnectionFactory.fullConnect(inputLayer,outputLayer);
        NeuralNetworkFactory.setDefaultIO(this);

        //定义一个学习规则
        LMS lms = new LMS();
        lms.setLearningRate(0.05);
        lms.setMaxError(0.05);
        this.setLearningRule(lms);
    }
}
