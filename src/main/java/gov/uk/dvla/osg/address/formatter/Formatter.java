package gov.uk.dvla.osg.address.formatter;

import static gov.uk.dvla.osg.address.formatter.AddressFormatter.*;
import static gov.uk.dvla.osg.address.formatter.PostcodeFormatter.*;

import java.util.List;

import uk.gov.dvla.osg.address.model.Address;

public class Formatter {
    
    /**
     * Loop through each address and format all address lines.
     * @param addresses the addresses to process
     */
    public static void formatAll(List<Address> addresses) {
        for (Address address : addresses) {
           formatSingle(address);
        }
    }
    
    /**
     * Format all address lines in the address.
     * @param address the address to process
     */
    public static void formatSingle(Address address) {
        address.setAddress1(formatLine(address.getAddress1()));
        address.setAddress2(formatLine(address.getAddress2()));
        address.setAddress3(formatLine(address.getAddress3()));
        address.setAddress4(formatLine(address.getAddress4()));
        address.setAddress5(formatLine(address.getAddress5()));
       /* Postcodes might not always be in the postcode field, for some file types, so we 
        * loop through each field to see if it contains a valid postcode. */
        if (validatePC(address.getPostcode())) {
            address.setPostcode(formatPC(address.getPostcode()));
        } else if (validatePC(address.getAddress5())) {
            address.setAddress5(formatPC(address.getAddress5()));
        } else if (validatePC(address.getAddress4())) {
            address.setAddress4(formatPC(address.getAddress4()));
        } else if (validatePC(address.getAddress3())) {
            address.setAddress3(formatPC(address.getAddress3()));
        } else if (validatePC(address.getAddress2())) {
            address.setAddress2(formatPC(address.getAddress2()));
        } else if (validatePC(address.getAddress1())) {
            address.setAddress1(formatPC(address.getAddress1()));
        }
    }
}
