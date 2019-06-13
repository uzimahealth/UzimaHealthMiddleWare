package ke.co.creswave.uzimahealth.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ke.co.creswave.uzimahealth.model.ApiResponse;
import ke.co.creswave.uzimahealth.model.Doctor;

@Controller
@RequestMapping(value = "/shortcode")
public class ShortCodeController {
	@GetMapping("/api")
	@ResponseBody
	public ApiResponse sayHello(@RequestParam(name = "from") String from) {
		return new ApiResponse("success", 200);
	}
}
