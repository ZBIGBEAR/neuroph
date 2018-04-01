package test;

import learningrule.AdaLinePerceptronLearn;
import learningrule.PerceptronClassifyLearn2;
import learningrule.PerceptronLearningRule;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;

/**
 * @Author: 李钰萍
 * @Description:
 * @Date: Created in 2018/3/30 23:27
 */
class TestAdaLinePerceptronLearn implements LearningEventListener {
    private AdaLinePerceptronLearn adaLinePerceptionLearn;
    public final static int CHAR_WIDTH = 5;
    public final static int CHAR_HEIGHT = 7;
    public static String[][] DIGITS = {
        {
            " OOO ",
            "O   O",
            "O   O",
            "O   O",
            "O   O",
            "O   O",
            " OOO "  },
        {   "  O  ",
            " OO  ",
            "O O  ",
            "  O  ",
            "  O  ",
            "  O  ",
            "  O  "  },
        {   " OOO ",
            "O   O",
            "    O",
            "   O ",
            "  O  ",
            " O   ",
            "OOOOO"  },

        {   " OOO ",
            "O   O",
            "    O",
            " OOO ",
            "    O",
            "O   O",
            " OOO "  },
        {   "   O ",
            "  OO ",
            " O O ",
            "O  O ",
            "OOOOO",
            "   O ",
            "   O "  },
        {   "OOOOO",
            "O    ",
            "O    ",
            "OOOO ",
            "    O",
            "O   O",
            " OOO "  },
        {   " OOO ",
            "O   O",
            "O    ",
            "OOOO ",
            "O   O",
            "O   O",
            " OOO "  },
        {   "OOOOO",
            "    O",
            "    O",
            "   O ",
            "  O  ",
            " O   ",
            "O    "  },
        {   " OOO ",
            "O   O",
            "O   O",
            " OOO ",
            "O   O",
            "O   O",
            " OOO "  },
        {   " OOO ",
            "O   O",
            "O   O",
            " OOOO",
            "    O",
            "O   O",
            " OOO "  } };
    public static String[][] DIGITS_TEST = {
            {
                    " OOO ",
                    "O   O",
                    "O   O",
                    "O   O",
                    "O   O",
                    "O   O",
                    " OOO "  },
            {   "  O  ",
                    " OO  ",
                    "O O  ",
                    "  O  ",
                    "     ",
                    "  O  ",
                    "  O  "  },
            {   " OOO ",
                    "    O",
                    "    O",
                    "   O ",
                    "  O  ",
                    " O   ",
                    "OOOOO"  },

            {   " OOO ",
                    "O   O",
                    "    O",
                    " O O ",
                    "    O",
                    "O   O",
                    " OOO "  },
            {   "   O ",
                    "  OO ",
                    " O O ",
                    "O0 O ",
                    "OOOOO",
                    "   O ",
                    "   O "  },
            {   "OOOOO",
                    "O    ",
                    "O    ",
                    "OOOO ",
                    "0   O",
                    "O   O",
                    " OOO "  },
            {   " OOO ",
                    "O   O",
                    "O    ",
                    "OOOO ",
                    "O   O",
                    "O   O",
                    " OOO "  },
            {   "OOOOO",
                    "    O",
                    "    O",
                    "   O ",
                    "  O  ",
                    " O   ",
                    "O    "  },
            {   " OOO ",
                    "O   O",
                    "O   O",
                    " OOO ",
                    "O   O",
                    "O   O",
                    " OOO "  },
            {   " OOO ",
                    "O   O",
                    "O   O",
                    " OOOO",
                    "    O",
                    "O   O",
                    " OOO "  } };
    public static DataSet ds = new DataSet(CHAR_HEIGHT*CHAR_WIDTH,DIGITS.length);
    public static DataSet ds_Test = new DataSet(CHAR_HEIGHT*CHAR_WIDTH,DIGITS.length);
    public void run(){
        adaLinePerceptionLearn = new AdaLinePerceptronLearn(CHAR_WIDTH*CHAR_HEIGHT,DIGITS.length);
        //初始化数据集
        createTrainDataSet();
        createTrainDataSetTest();
        //开始训练
        learn();
        //开始测试
        test();
    }
    public static void main(String[] args) {
        new TestAdaLinePerceptronLearn().run();
    }

    @Override
    public void handleLearningEvent(LearningEvent event) {
        /*
        IterativeLearning bp = (IterativeLearning)event.getSource();
        System.out.println("iterate:"+bp.getCurrentIteration());
        System.out.println("weights:"+Arrays.toString(bp.getNeuralNetwork().getWeights()));*/
    }
    private void learn(){
        System.out.println("begin traning...");
        //训练集
        adaLinePerceptionLearn.learn(ds);
        System.out.println("end traning...");
    }
    private void createTrainDataSet(){
        for(int i=0;i<DIGITS.length;i++){
            double[] input = new double[CHAR_HEIGHT*CHAR_WIDTH];
            int index = 0;
            for(int row = 0;row<CHAR_HEIGHT;row++){
                for(int col = 0;col<CHAR_WIDTH;col++){
                    input[index++] = DIGITS[i][row].charAt(col)==79?1:-1;
                }
            }
            double[] output = new double[DIGITS.length];
            for(int j=0;j<DIGITS.length;j++){
                output[j]=0;
            }
            output[i]=1;
            DataSetRow dsr = new DataSetRow(input,output);
            ds.addRow(dsr);
        }
    }
    private void createTrainDataSetTest(){
        for(int i=0;i<DIGITS_TEST.length;i++){
            double[] input = new double[CHAR_HEIGHT*CHAR_WIDTH];
            int index = 0;
            for(int row = 0;row<CHAR_HEIGHT;row++){
                for(int col = 0;col<CHAR_WIDTH;col++){
                    input[index++] = DIGITS_TEST[i][row].charAt(col)==79?1:-1;
                }
            }
            double[] output = new double[DIGITS_TEST.length];
            for(int j=0;j<DIGITS_TEST.length;j++){
                output[j]=0;
            }
            output[i]=1;
            DataSetRow dsr = new DataSetRow(input,output);
            ds_Test.addRow(dsr);
        }
    }
    private void test(){
        System.out.println("begin test...");
        for(DataSetRow dsr:ds_Test.getRows()){
            adaLinePerceptionLearn.setInput(dsr.getInput());
            adaLinePerceptionLearn.calculate();
            double[] output = dsr.getDesiredOutput();
            int index = 0;
            for(int i=0;i<DIGITS_TEST.length;i++){
                if(output[i]==1){
                    index = i;
                    break;
                }
            }
            //原始数字
            printDigit(DIGITS_TEST[index]);
            //结果
            System.out.println("检测结果：");
            System.out.println(maxIndex(adaLinePerceptionLearn.getOutput()));
        }
        System.out.println("end test...");
    }
    private void printDigit(String[] datas){
        System.out.println("原始数据：");
        for(String str:datas){
            System.out.println(str);
        }
        System.out.println();
    }
    private int maxIndex(double[]result){
        double max=0;
        int index=0;
        for(int i=0;i<result.length;i++){
            if(result[i]>max){
                max=result[i];
                index=i;
            }
        }
        return index;
    }
}
