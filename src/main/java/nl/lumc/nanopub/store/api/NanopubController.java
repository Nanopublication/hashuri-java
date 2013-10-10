package nl.lumc.nanopub.store.api;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/nanopub")
public class NanopubController {
	private static final Logger logger = LoggerFactory.getLogger(NanopubController.class);
	
	@RequestMapping(value = "/mint-uri", method = RequestMethod.POST)
	@ApiOperation("mints a new uri")
	public @ResponseBody URI mintUri(
			@ApiParam(required = true, value = "seed for the uri")
			@RequestParam final String seed) {
		
		// TODO create cool implementation
		
		return URI.create(seed);
	}
	
	@RequestMapping(value = "/store", method = RequestMethod.PUT)
	@ApiOperation("stores a nanopub")
	public @ResponseBody Object storeNanopub(
			@ApiParam(required = true, value = "The nanopub document")
			@RequestParam final String nanopub) {
		
		// TODO create cool implementation
		
		return "Thanks!";
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ApiOperation("retrieves a single nanopub")
	public @ResponseBody Object getNanopublication(
			@ApiParam(required = true, value = "The identifier of the required nanopublication")
			@RequestParam final String id) {
		logger.debug("retrieving nanopublication with id '{}'", id);
		
		// TODO create cool implementation
		
		return "This is a nanopub with the id " + id;
	}
}
