package ke.co.creswave.uzimahealth.tests;
import java.io.IOException;

import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.io.ClassPathResource;

public class Dl4jClient {

	public static void main(String[] args) {
		// load the model
		String simpleMlp;
		try {
			simpleMlp = new ClassPathResource(
			                          "trained_model.h5").getFile().getPath();
			MultiLayerNetwork model = KerasModelImport.
                    importKerasSequentialModelAndWeights(simpleMlp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKerasConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedKerasConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
