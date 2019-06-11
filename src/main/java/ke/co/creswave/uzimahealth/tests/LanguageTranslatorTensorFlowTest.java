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

public class LanguageTranslatorTensorFlowTest {
	public static void main(String[] args) throws Exception {
		SavedModelBundle savedModelBundle = SavedModelBundle
				.load("C:/Users/oried/PycharmProjects/UzimaHealthTensorFlowTrainer/uzimatransator/exported_model_two", "serve");
		Graph graph = savedModelBundle.graph();

		float[][] inputData = { { 103,12,0,0,0,0,0,0,0} };
		// We have to create tensor to feed it to session,
		// unlike in Python where you just pass Numpy array
		Tensor inputTensor = Tensor.create(inputData, Float.class);

		Tensor result = savedModelBundle.session().runner().feed("embedding_1_input:0", inputTensor)
				.fetch("time_distributed_1/Reshape_1:0").run().get(0);
		
		
		System.err.println(result.toString());
	   /* float[][][] outputBuffer = new float[1][1][1];
	    result.copyTo(outputBuffer);
	    for (int i = 0; i < outputBuffer[0].length; i++) {
            System.out.println(outputBuffer[0][i]);//should be 41. 51.5 62.
        }*/
	}
}
