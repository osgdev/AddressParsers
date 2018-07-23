package uk.gov.dvla.osg.address.model;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputArgs {
    
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int EXPECTED_NUMBER_OF_ARGS = 3;
    private final String configfile;
    private FileType type;
    private final String inputfile;
    
    public InputArgs(String[] cmdArgs) {
        // validate args length
        if (cmdArgs.length != EXPECTED_NUMBER_OF_ARGS) {
            LOGGER.fatal("Incorrect number of arguments supplied.");
            LOGGER.fatal("Usage: progName.jar configFile, fileType, inputFile");
            System.exit(1);
        }
        // Extract args
        configfile = cmdArgs[0];
        boolean configFileExists = new File(configfile).exists();
        if (!configFileExists) {
            LOGGER.fatal("Config File '{}' doesn't exist", configfile);
            System.exit(1);
        }

        try {
            type = FileType.valueOf(cmdArgs[1].toUpperCase());
        } catch (Exception ex) {
            LOGGER.fatal("FileType [{}] is not a valid type!", cmdArgs[1]);
            LOGGER.fatal("Please check against the list of permitted values in the FileType enum.");
            System.exit(1);
        }

        inputfile = cmdArgs[2];
        boolean inputFileExists = new File(inputfile).exists();
        if (!inputFileExists) {
            LOGGER.fatal("Input File '{}' doesn't exist on the filepath.", inputfile);
            System.exit(1);
        }
    }

    /**
     * Gets the config file path.
     *
     * @return the config filepath
     */
    public String getConfigfile() {
        return configfile;
    }

    /**
     * Gets the file type.
     *
     * @return the file type
     */
    public FileType getType() {
        return type;
    }

    /**
     * Gets the input filepath.
     *
     * @return the input filepath
     */
    public String getInputfile() {
        return inputfile;
    }
}
