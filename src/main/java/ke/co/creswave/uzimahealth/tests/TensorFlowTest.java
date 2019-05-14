package ke.co.creswave.uzimahealth.tests;

import java.util.Iterator;

import org.tensorflow.Graph;
import org.tensorflow.Operation;
import org.tensorflow.Output;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import org.tensorflow.op.core.TensorArray;

public class TensorFlowTest {
	public static void main(String[] args) throws Exception {
		SavedModelBundle savedModelBundle = SavedModelBundle
				.load("C:/Users/oried/PycharmProjects/UzimaHealthTensorFlowTrainer/exported_model/", "serve");
		Graph graph = savedModelBundle.graph();

		float[][] inputData = { { 0.4999f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f} };
		// We have to create tensor to feed it to session,
		// unlike in Python where you just pass Numpy array
		Tensor inputTensor = Tensor.create(inputData, Float.class);

		Tensor result = savedModelBundle.session().runner().feed("layer_1_input:0", inputTensor)
				.fetch("output_layer/BiasAdd:0").run().get(0);
	    float[][] outputBuffer = new float[1][1];
	    result.copyTo(outputBuffer);
	    for (int i = 0; i < outputBuffer[0].length; i++) {
            System.out.println(outputBuffer[0][i]);//should be 41. 51.5 62.
        }
	}
}
