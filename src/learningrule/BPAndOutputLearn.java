package learningrule;

import org.neuroph.core.*;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.IterativeLearning;
import org.neuroph.core.learning.SupervisedLearning;
import org.neuroph.core.transfer.Linear;
import org.neuroph.core.transfer.Step;
import org.neuroph.nnet.comp.neuron.BiasNeuron;
import org.neuroph.nnet.comp.neuron.InputNeuron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.*;
import org.neuroph.util.random.NguyenWidrowRandomizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/30 23:11
 */
public class BPAndOutputLearn extends NeuralNetwork implements LearningEventListener {
    public BPAndOutputLearn(TransferFunctionType transferFunctionType, int...neronsLayers)
    {
        //super(transferFunctionType,neronsLayers);
        //createNetwork(neronsLayers,neuronProperties);
        NeuronProperties neuronProperties = new NeuronProperties();
        neuronProperties.setProperty("transferFunction",transferFunctionType);
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

        //创建输出神经元
        NeuronProperties outProperties = new NeuronProperties();
        outProperties.setProperty("transferFunction",Step.class);
        layer = LayerFactory.createLayer(neronsLayers.get(i),outProperties);
        this.addLayer(layer);
        ConnectionFactory.fullConnect(preLayer,layer);
        preLayer = layer;
        NeuralNetworkFactory.setDefaultIO(this);

        this.setLearningRule(new BackPropagation());
        //this.randomizeWeights(new NguyenWidrowRandomizer(-0.7,0.7));
        //((SupervisedLearning)this.getLearningRule()).setMaxError(0.0001d);
        //this.getLearningRule().addListener(this);
    }

    @Override
    public void handleLearningEvent(LearningEvent event) {
        IterativeLearning bp = (IterativeLearning)event.getSource();
        System.out.println("iterate:"+bp.getCurrentIteration());
        System.out.println("weights:"+ Arrays.toString(bp.getNeuralNetwork().getWeights()));
    }
}
