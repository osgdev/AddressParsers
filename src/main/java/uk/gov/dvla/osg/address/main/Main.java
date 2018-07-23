package uk.gov.dvla.osg.address.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.uk.dvla.osg.address.formatter.Formatter;
import uk.gov.dvla.osg.address.model.InputArgs;
import uk.gov.dvla.osg.address.parser.AddressParser;
import uk.gov.dvla.osg.address.parser.AddressParserFactory;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LOGGER.trace("--------------- STARTING APPLICATION ---------------");

        InputArgs inputArgs = new InputArgs(args);
        AddressParser parser = AddressParserFactory.getParser(inputArgs.getConfigfile(), 
                inputArgs.getType(), inputArgs.getInputfile());

        if (parser.getAddresses().size() != 0) {
            Formatter.formatAll(parser.getAddresses());
            parser.save();
        } else {
            LOGGER.info("No addresses to process!");
        }

        LOGGER.trace("--------------- FINISHED ---------------");
    }
}
