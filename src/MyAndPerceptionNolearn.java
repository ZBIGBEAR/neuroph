import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.transfer.TransferFunction;
import org.neuroph.nnet.comp.neuron.BiasNeuron;
import org.neuroph.nnet.comp.neuron.InputNeuron;
import org.neuroph.util.*;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/18 12:09
 */
public class MyAndPerceptionNolearn extends NeuralNetwork {

    public MyAndPerceptionNolearn(int inputNeuronsCount){
        createNetwork(inputNeuronsCount);
    }

    private void createNetwork(int inputNeuronsCount){
        //设置网络类型为感知机
        this.setNetworkType(NeuralNetworkType.PERCEPTRON);

        //建立输入神经元，表示输入的刺激
        NeuronProperties inputNeuronProperties = new NeuronProperties();
        inputNeuronProperties.setProperty("neuronType",InputNeuron.class);

        //由输入神经元构成的输入层
        Layer inputLayer = LayerFactory.createLayer(inputNeuronsCount,inputNeuronProperties);
        this.addLayer(inputLayer);

        //在输入层增加BiasNeuron,表示神经元偏执
        inputLayer.addNeuron(new BiasNeuron());

        //建立输出神经元，设置传输函数为step()函数
        NeuronProperties outputNeuronPerperties = new NeuronProperties();
        outputNeuronPerperties.setProperty("transferFunction", TransferFunctionType.STEP);

        //由输出神经元构成的输出层
        Layer outputLayer = LayerFactory.createLayer(1,outputNeuronPerperties);
        this.addLayer(outputLayer);

        //将输入层和输出层进行全连接
        ConnectionFactory.fullConnect(inputLayer,outputLayer);
        NeuralNetworkFactory.setDefaultIO(this);

        //设置输入神经元和感知机之间的连接权重
        Neuron n = outputLayer.getNeuronAt(0);
        n.getInputConnections().get(0).getWeight().setValue(1);
        n.getInputConnections().get(1).getWeight().setValue(1);
        n.getInputConnections().get(2).getWeight().setValue(-1.5);
    }
}
