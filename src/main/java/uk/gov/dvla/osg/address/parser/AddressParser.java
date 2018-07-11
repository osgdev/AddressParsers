package uk.gov.dvla.osg.address.parser;

import java.util.ArrayList;
import java.util.List;

import uk.gov.dvla.osg.address.config.ParserConfig;
import uk.gov.dvla.osg.address.model.Address;
import uk.gov.dvla.osg.address.model.FileType;

public abstract class AddressParser {
    protected ParserConfig pc;
    protected String inputFile;
    protected List<Address> addresses = new ArrayList<>();
    protected FileType type;
    

    public AddressParser(ParserConfig pc, String inputFile, FileType type) {
        this.pc = pc;
        this.inputFile = inputFile;
        this.type = type;
        this.load();
    }
    
    public abstract void load();
    public abstract void save();

    public List<Address> getAddresses() {
        return addresses;
    }
}
