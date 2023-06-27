package com.stock.deeplearning;

/**
 * @Description
 * @Author makuo
 * @Date 2023/3/28 15:42
 **/
public class FireworkRecognition {
    public static final int nChannels = 1; //黑白图像通道数量
    public static final int outputNum = 2; //输出类别数(烟花、非烟花)
    public static final int batchSize = 64; //每个批次数据大小
    public static final int nEpochs = 10; //训练轮数
    public static final int seed = 123; //随机种子

    /*public static void main(String[] args) throws Exception {

        DataSetIterator trainIter = new MnistDataSetIterator(batchSize, true, 12345);
        DataSetIterator testIter = new MnistDataSetIterator(batchSize, false, 12345);

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .l2(0.0005)
                .updater(new Nesterovs(0.006, 0.9))
                .list()
                .layer(0, new ConvolutionLayer.Builder(5, 5)
                        .nIn(nChannels)
                        .stride(1, 1)
                        .nOut(32)
                        .activation(Activation.RELU)
                        .convolutionMode(ConvolutionMode.Same)
                        .build())
                .layer(1, new SubsamplingLayer.Builder(PoolingType.MAX)
                        .kernelSize(2, 2)
                        .stride(2, 2)
                        .build())
                .layer(2, new ConvolutionLayer.Builder(5, 5)
                        .stride(1,1)
                        .nOut(32)
                        .activation(Activation.RELU)
                        .convolutionMode(ConvolutionMode.Same)
                        .build())
                .layer(3, new SubsamplingLayer.Builder(PoolingType.MAX)
                        .kernelSize(2,2)
                        .stride(2,2)
                        .build())
                .layer(4, new DenseLayer.Builder().activation(Activation.RELU)
                        .nOut(256).build())
                .layer(5, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nOut(outputNum)
                        .activation(Activation.SOFTMAX)
                        .build())
                .setInputType(InputType.convolutionalFlat(28, 28, 1))
                .backpropType(BackpropType.Standard)
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(100));

        for (int i = 0; i < nEpochs; i++) {
            model.fit(trainIter);
            Evaluation eval = model.evaluate(testIter);
            System.out.println("Epoch " + i + " accuracy: " + eval.accuracy());
            trainIter.reset();
            testIter.reset();
        }
    }*/
}

