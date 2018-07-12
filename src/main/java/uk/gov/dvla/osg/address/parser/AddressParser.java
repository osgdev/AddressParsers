package uk.gov.dvla.osg.address.parser;

import java.util.ArrayList;
import java.util.List;

import uk.gov.dvla.osg.address.config.ParserConfig;
import uk.gov.dvla.osg.address.model.Address;
import uk.gov.dvla.osg.address.model.FileType;

/**
 * The Class AddressParser.
 */
public abstract class AddressParser {
    protected ParserConfig pc;
    protected String inputFile;
    protected List<Address> addresses = new ArrayList<>();
    protected FileType type;
    

    /**
     * Instantiates a new address parser.
     *
     * @param pc the parser configuration
     * @param inputFile the input file containing the addresses
     * @param type the type of file or application
     */
    public AddressParser(ParserConfig pc, String inputFile, FileType type) {
        this.pc = pc;
        this.inputFile = inputFile;
        this.type = type;
        this.load();
    }
    
    /**
     * Parses the provided input file and loads the addresses into an address list.
     */
    public abstract void load();
    
    /**
     * Saves the addresses in the address list back to the provided input file.
     */
    public abstract void save();

    /**
     * Gets the list of addresses.
     *
     * @return the addresses
     */
    public List<Address> getAddresses() {
        return addresses;
    }
}
