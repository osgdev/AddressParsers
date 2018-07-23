package uk.gov.dvla.osg.address.parser;

import java.util.List;

import uk.gov.dvla.osg.address.model.Address;

/**
 * The interface IAddressParser.
 */
public interface IAddressParser {
    
    /**
     * Saves the addresses in the address list back to the provided input file.
     */
    public void save();

    /**
     * Gets the list of addresses.
     *
     * @return the addresses
     */
    public List<Address> getAddresses();
}
