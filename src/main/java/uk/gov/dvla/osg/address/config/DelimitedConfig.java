package uk.gov.dvla.osg.address.config;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.SerializedName;

/**
 * The Class DelimitedConfig.
 */
public class DelimitedConfig {

    @SerializedName("address1")
    private Integer address1;
    @SerializedName("address2")
    private Integer address2;
    @SerializedName("address3")
    private Integer address3;
    @SerializedName("address4")
    private Integer address4;
    @SerializedName("address5")
    private Integer address5;
    @SerializedName("postcode")
    private Integer postcode;
    @SerializedName("delimiter")
    private char delimiter;
    @SerializedName("hasHeader")
    private boolean hasHeader;
    @SerializedName("footerMarker")
    private String footerMarker;
    

    /**
     * Gets the index of the column for address line 1.
     *
     * @return the address 1
     */
    public int getAddress1() {
        return address1.intValue();
    }

    /**
     * Gets the index of the column for address line 2.
     *
     * @return the address 2
     */
    public int getAddress2() {
        return address2.intValue();
    }

    /**
     * Gets the index of the column for address line 3.
     *
     * @return the address 3
     */
    public int getAddress3() {
        return address3.intValue();
    }

    /**
     * Gets the index of the column for address line 4.
     *
     * @return the address 4
     */
    public int getAddress4() {
        return address4.intValue();
    }

    /**
     * Gets the index of the column for address line 5.
     *
     * @return the address 5
     */
    public int getAddress5() {
        return address5.intValue();
    }

    /**
     * Gets the index of the column for the postcode field.
     *
     * @return the postcode
     */
    public int getPostcode() {
        return postcode.intValue();
    }

    /**
     * Gets the delimiter used to separate fields in the file.
     *
     * @return the delimiter
     */
    public char getDelimiter() {
        return delimiter;
    }
    
    /**
     * Indicates if the file has a header row.
     *
     * @return true, if the file has a header row
     */
    public boolean hasHeader() {
        return hasHeader;
    }
    
    /**
     * Gets the footer marker used to indicate the final row in the file
     * e.g. "999" in the first (OTT) field
     *
     * @return the footer marker
     */
    public String getFooterMarker() {
        return footerMarker;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("delimiter", delimiter)
                .append("hasHeader", hasHeader)
                .append("footerMarker", footerMarker)
                .append("address1", address1)
                .append("address2", address2)
                .append("address3", address3)
                .append("address4", address4)
                .append("address5", address5)
                .append("postcode", postcode)
                .toString();
    }

}
