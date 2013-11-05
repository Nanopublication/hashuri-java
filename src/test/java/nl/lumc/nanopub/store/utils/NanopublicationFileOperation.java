package nl.lumc.nanopub.store.utils;

import static com.google.common.io.Files.readFirstLine;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 * @author Rajaram, Eelke, Mark, Reinout
 * @serial 05-11-2013
 * @version 0.1
 */
public class NanopublicationFileOperation {
    
    private static final org.slf4j.Logger LOGGER
            = getLogger(NanopublicationFileOperation.class);
    
    /**
     * <P>
     * To get the content of the file stored in the test resources package.
     * </P>
     * @param fileLocation  Location of the file. (E.g /validNanopub/file.trig)
     * @return  File content as a string object. 
     */
    public  String getNanopub(String fileLocation)  {
        URL fileURL = this.getClass().getResource(fileLocation);
        File npFile;
        String content = null;
        try {
            npFile = new File(fileURL.toURI());
            content = readFirstLine(npFile, StandardCharsets.UTF_8);            
        } catch (IOException | URISyntaxException ex) {
            LOGGER.warn("NanopublicationFileOperation failed ",ex);
        
        }        
        return content;
    }
    
}
