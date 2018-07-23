package gov.uk.dvla.osg.address.formatter;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class AddressFormatter {

    /**
     * <p>Removes unneccessary spaces and commas from an address line.</p>
     * <p>Ensures that address lines conform to the following rules:</p>
     * <ul>
     *  <li>Words in the address are separated by no more than a single space.</li>
     *  <li>There are no commas in the address line.</li>
     * </ul>
     * @param line Address line to format.
     * @return Formatted address line.
     */
    public static String formatLine(String line) {
        // searches for all whitespace longer than a single space
        String temp = Pattern.compile("\\s+").matcher(line).replaceAll(" ");
        // trim extra spaces from start and end of string
        temp = StringUtils.trimToEmpty(temp);
        // remove all commas from the string
        return temp.replaceAll(",", "");
    }
    
}
