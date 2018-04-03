package learningrule;

import org.neuroph.core.*;
import org.neuroph.core.input.And;
import org.neuroph.core.transfer.Linear;
import org.neuroph.nnet.comp.neuron.BiasNeuron;
import org.neuroph.nnet.comp.neuron.InputNeuron;
import org.neuroph.nnet.learning.LMS;
import org.neuroph.util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/30 23:11
 */
public class MIPerceptronAndOutputNoLearn extends NeuralNetwork{
    public MIPerceptronAndOutputNoLearn(TransferFunctionType transferFunctionType,int...neronsLayers)
    {
        //super(transferFunctionType,neronsLayers);
        //createNetwork(neronsLayers,neuronProperties);
        NeuronProperties neuronProperties = new NeuronProperties();
        neuronProperties.setProperty("transforFunction",transferFunctionType);
        List<Integer> neronsLayersList = new ArrayList();
        for(int i=0;i<neronsLayers.length;i++){
            neronsLayersList.add(neronsLayers[i]);
        }
        createNetwork(neronsLayersList,neuronProperties);
    }
    private void createNetwork(List<Integer> neronsLayers,NeuronProperties neuronProperties){
        //设置网络类别为多层感知机
        this.setNetworkType(NeuralNetworkType.MULTI_LAYER_PERCEPTRON);
        //建立输入层
        NeuronProperties inputNeuronProperties = new NeuronProperties(InputNeuron.class,Linear.class);
        Layer layer = LayerFactory.createLayer(neronsLayers.get(0),inputNeuronProperties);
        layer.addNeuron(new BiasNeuron());
        this.addLayer(layer);
        //创建中间隐藏层
        Layer preLayer = layer;
        int i=1;
        for( i=1;i<neronsLayers.size()-1;i++){
            layer = LayerFactory.createLayer(neronsLayers.get(i),neuronProperties);
            layer.addNeuron(new BiasNeuron());
            this.addLayer(layer);
            if(preLayer!=null){
                ConnectionFactory.fullConnect(preLayer,layer);
            }
            preLayer = layer;
        }
        //设置初始权值
        Neuron n1 = layer.getNeuronAt(0);
        Connection[]c1 = n1.getInputConnections();
        c1[0].setWeight(new Weight(2));
        c1[1].setWeight(new Weight(2));
        c1[2].setWeight(new Weight(-1));

        Neuron n2 = layer.getNeuronAt(1);
        Connection[]c2 = n2.getInputConnections();
        c2[0].setWeight(new Weight(-2));
        c2[1].setWeight(new Weight(-2));
        c2[2].setWeight(new Weight(3));
        //建立输出神经元，输入函数为逻辑与
        NeuronProperties outputProperties = new NeuronProperties();
        outputProperties.setProperty("inputFunction", And.class);

        //创建输出层
        layer = LayerFactory.createLayer(neronsLayers.get(i),outputProperties);
        this.addLayer(layer);
        ConnectionFactory.fullConnect(preLayer,layer);

        NeuralNetworkFactory.setDefaultIO(this);
    }
}
