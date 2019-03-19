package uk.gov.dvla.osg.address.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.uk.dvla.osg.address.formatter.Formatter;
import uk.gov.dvla.osg.address.model.InputArgs;
import uk.gov.dvla.osg.address.parser.AddressParserFactory;
import uk.gov.dvla.osg.address.parser.IAddressParser;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            LOGGER.trace("--------------- STARTING APPLICATION ---------------");
    
            InputArgs inputArgs = new InputArgs(args);
            
            IAddressParser parser = AddressParserFactory.getParser(inputArgs.getConfigfile(), inputArgs.getType(), inputArgs.getInputfile());
    
            if (parser.getAddresses().size() != 0) {
                Formatter.formatAll(parser.getAddresses());
                parser.save(inputArgs.getOutputfile());
            } else {
                LOGGER.info("No addresses to process!");
            }
    
            LOGGER.trace("--------------- FINISHED ---------------");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            System.exit(1);
        }
    }
}
