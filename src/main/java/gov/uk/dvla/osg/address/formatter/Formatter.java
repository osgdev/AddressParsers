package gov.uk.dvla.osg.address.formatter;

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
        address.setAddress1(AddressFormatter.formatLine(address.getAddress1()));
        address.setAddress2(AddressFormatter.formatLine(address.getAddress2()));
        address.setAddress3(AddressFormatter.formatLine(address.getAddress3()));
        address.setAddress4(AddressFormatter.formatLine(address.getAddress4()));
        address.setAddress5(AddressFormatter.formatLine(address.getAddress5()));
       /* Postcodes might not always be in the postcode field, for some file types, so we 
        * loop through each field to see if it contains a valid postcode. */
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
    }
}
