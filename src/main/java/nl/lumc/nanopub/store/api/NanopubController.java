package nl.lumc.nanopub.store.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import nl.lumc.nanopub.store.api.json.ResponseWrapper;

@Controller
@RequestMapping("/nanopubs")
public class NanopubController {
	private static final Logger logger = LoggerFactory.getLogger(NanopubController.class);
	
	/**
	 * 
	 * @param seed
	 * @return
	 */
	@RequestMapping(value = "/mint-uri", method = RequestMethod.POST)
	@ApiOperation("mints a new uri")
	public @ResponseBody URI mintUri(
			@ApiParam(required = true, value = "seed for the uri")
			@RequestParam final String seed) {
		
		// TODO create cool implementation
		
		return URI.create(seed);
	}
	

	/**
	 * 
	 * @param contentType
	 * @param nanopub
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation("Stores a nanopublication")
	public @ResponseBody ResponseWrapper storeNanopub(
			@RequestHeader(value = "Content-Type") String contentType, // needs to be removed from Swagger api
			// Swagger always sends "application/json", so from the interface the string needs quotes, no quotes needed from another REST client
			@ApiParam(required = true, value = "3The RDF content of the nanopublication to be published")
			@RequestBody(required = true) String nanopub) {
		
		// TODO create cool implementation
		System.out.println(nanopub);
		
		ResponseWrapper response = new ResponseWrapper();
        response.setValue("Thanks for " + nanopub + " of type " + contentType);
        
        return response;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation("Retrieves a list of all nanopub URIs in the store.")
	public @ResponseBody List<URI> listNanopubs() {
		
		// TODO create cool implementation
		
		List<URI> response = Collections.emptyList();
		
		try {
			response = Collections.singletonList(new URI("http://mydomain.com/nanopubs/1"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return response;
	}
		

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation("Retrieves a single nanopub")
	public @ResponseBody Object retrieveNanopub(
			@ApiParam(required = true, value = "The identifier of the required nanopublication")
			@PathVariable final String id) {
		logger.debug("retrieving nanopublication with id '{}'", id);
		
		// TODO create cool implementation
		
		return "This is a nanopub with the id " + id;
	}  
      
        
    
}
