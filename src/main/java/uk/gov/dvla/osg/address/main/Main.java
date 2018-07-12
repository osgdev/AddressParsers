package uk.gov.dvla.osg.address.main;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.uk.dvla.osg.address.AddressFormatter;
import gov.uk.dvla.osg.address.PostcodeFormatter;
import uk.gov.dvla.osg.address.model.Address;
import uk.gov.dvla.osg.address.model.FileType;
import uk.gov.dvla.osg.address.parser.AddressParser;
import uk.gov.dvla.osg.address.parser.AddressParserFactory;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int EXPECTED_NUMBER_OF_ARGS = 3;
    private static String configfile;
    private static FileType type;
    private static String inputfile;

    /**
     * The main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LOGGER.trace("--------------- STARTING APPLICATION ---------------");

        validateArgs(args);
        extractArgs(args);
        
        AddressParser parser = AddressParserFactory.getParser(configfile, type, inputfile);

        if (parser.getAddresses().size() != 0) {
            formatAddresses(parser);
            parser.save();
        } else {
            LOGGER.info("No addresses to process!");
        }

        LOGGER.trace("--------------- FINISHED ---------------");
    }

    /**
     * Loop through each address and format all address lines.
     * Postcodes might not always be in the postcode field, for some file types, so we 
     * loop through each field to see if it contains a valid postcode.
     *
     * @param parser the parser object containing the addresses to process
     */
    private static void formatAddresses(AddressParser parser) {
        for (Address address : parser.getAddresses()) {
            LOGGER.debug(address.toString());
            address.setAddress1(AddressFormatter.formatLine(address.getAddress1()));
            address.setAddress2(AddressFormatter.formatLine(address.getAddress2()));
            address.setAddress3(AddressFormatter.formatLine(address.getAddress3()));
            address.setAddress4(AddressFormatter.formatLine(address.getAddress4()));
            address.setAddress5(AddressFormatter.formatLine(address.getAddress5()));

            if (PostcodeFormatter.validate(address.getPostcode())) {
                address.setPostcode(PostcodeFormatter.Format(address.getPostcode()));
            } else if (PostcodeFormatter.validate(address.getAddress5())) {
                address.setAddress5(PostcodeFormatter.Format(address.getAddress5()));
            } else if (PostcodeFormatter.validate(address.getAddress4())) {
                address.setAddress4(PostcodeFormatter.Format(address.getAddress4()));
            } else if (PostcodeFormatter.validate(address.getAddress3())) {
                address.setAddress3(PostcodeFormatter.Format(address.getAddress3()));
            } else if (PostcodeFormatter.validate(address.getAddress2())) {
                address.setAddress2(PostcodeFormatter.Format(address.getAddress2()));
            } else if (PostcodeFormatter.validate(address.getAddress1())) {
                address.setAddress1(PostcodeFormatter.Format(address.getAddress1()));
            }
            LOGGER.debug(address.toString());
            LOGGER.debug("");
        }
    }

    /**
     * Checks the number of supplied arguments and terminates the program if an
     * invalid number of arguments was provided.
     *
     * @param args the args
     */
    private static void validateArgs(String[] args) {
        // validate args length
        if (args.length != EXPECTED_NUMBER_OF_ARGS) {
            LOGGER.fatal("Incorrect number of arguments supplied.");
            LOGGER.fatal("Usage: progName.jar configFile, fileType, inputFile");
            System.exit(1);
        }

    }

    /**
     * Assigns args to variables and validates each argument. Program is terminated
     * if invalid arguments were provided.
     *
     * @param args the args
     */
    private static void extractArgs(String[] args) {
        configfile = args[0];
        boolean configFileExists = new File(configfile).exists();
        if (!configFileExists) {
            LOGGER.fatal("Config File '{}' doesn't exist", configfile);
            System.exit(1);
        }

        try {
            type = FileType.valueOf(args[1].toUpperCase());
        } catch (Exception ex) {
            LOGGER.fatal("FileType [{}] is not a valid type!", args[1]);
            LOGGER.fatal("Please check against the list of permitted values in the FileType enum.");
            System.exit(1);
        }

        inputfile = args[2];
        boolean inputFileExists = new File(inputfile).exists();
        if (!inputFileExists) {
            LOGGER.fatal("Input File '{}' doesn't exist on the filepath.", inputfile);
            System.exit(1);
        }
    }
}
