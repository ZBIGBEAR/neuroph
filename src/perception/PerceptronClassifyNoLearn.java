package perception;

import org.neuroph.core.Connection;
import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.transfer.TransferFunction;
import org.neuroph.nnet.comp.neuron.InputNeuron;
import org.neuroph.util.*;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/30 23:11
 */
public class PerceptronClassifyNoLearn extends NeuralNetwork{
    public PerceptronClassifyNoLearn(int inputNeuronCount){
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

        //设置权值
        Neuron n = outputLayer.getNeuronAt(0);
        n.getInputConnections()[0].getWeight().setValue(1);
        n.getInputConnections()[1].getWeight().setValue(0);

        n = outputLayer.getNeuronAt(1);
        n.getInputConnections()[0].getWeight().setValue(0);
        n.getInputConnections()[1].getWeight().setValue(1);
    }
}
