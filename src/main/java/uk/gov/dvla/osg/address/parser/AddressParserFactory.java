package uk.gov.dvla.osg.address.parser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import uk.gov.dvla.osg.address.config.ParserConfig;
import uk.gov.dvla.osg.address.model.FileType;

/**
 * A factory for creating AddressParser objects.
 */
public class AddressParserFactory {

    private static final Logger LOGGER = LogManager.getLogger();
    
    /**
     * Gets the address parser.
     *
     * @param configFile the application configuration file
     * @param type the type of file/application to be parsed
     * @param inputfile the inputfile containing the addresses
     * @return the parser
     */
    public static IAddressParser getParser(String configFile, FileType type, String inputfile) {
        
        try (Reader reader = new FileReader(configFile)) {
            ParserConfig config = new Gson().fromJson(reader, ParserConfig.class);
            switch (type) {
            case DPF:
                return new DpfParser(config, inputfile);
            case TSV:
                return new TabParser(config, inputfile);
            default:
                return new DelimitedFileParser(config, inputfile, type);
            }
        } catch (IOException ex) {
            LOGGER.fatal("Unable to load configFile {}", configFile);
        } catch (JsonIOException ex) {
            LOGGER.fatal("Unable to read from configFile {}", configFile);
        } catch (JsonSyntaxException ex) {
            LOGGER.fatal("Config file [{}] is not valid JSON", configFile);
        }
        System.exit(1);
        return null;          
    }
}
