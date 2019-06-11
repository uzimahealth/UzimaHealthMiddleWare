package ke.co.creswave.uzimahealth.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

import ke.co.creswave.uzimahealth.model.ApiResponse;
import ke.co.creswave.uzimahealth.model.CommunityHealthWorker;
import ke.co.creswave.uzimahealth.model.TranslationRequest;
import ke.co.creswave.uzimahealth.model.TranslationResult;

@RestController
@RequestMapping(value = "/tensor")
public class TensorFlowController {
	public static final String tensorFlowApiUrl = "http://127.0.0.1:5000"; // python api, should be parameterized.

	@RequestMapping(value = "/language/translate", method = RequestMethod.POST)
	public HttpEntity<TranslationResult> translateLanguage(@Valid @RequestBody TranslationRequest uploadRequest,
			HttpServletRequest request) {

		Form form = new Form();
		form.add("language", uploadRequest.getLanguage());
		form.add("data", uploadRequest.getData());
		Client client = Client.create();
		WebResource webresource = client.resource(tensorFlowApiUrl);
		ClientResponse clientResponse = webresource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, form);

		String responseString = clientResponse.getEntity(String.class);
		TranslationResult apiResponse = new Gson().fromJson(responseString, TranslationResult.class);
		return new ResponseEntity<TranslationResult>(apiResponse, HttpStatus.OK);
	}
}
