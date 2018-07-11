package uk.gov.dvla.osg.address.config;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.SerializedName;

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
    /**
     * No args constructor for use in serialization
     * 
     */
    public DelimitedConfig() {}

    public int getAddress1() {
        return address1.intValue();
    }

    public int getAddress2() {
        return address2.intValue();
    }

    public int getAddress3() {
        return address3.intValue();
    }

    public int getAddress4() {
        return address4.intValue();
    }

    public int getAddress5() {
        return address5.intValue();
    }

    public int getPostcode() {
        return postcode.intValue();
    }

    public char getDelimiter() {
        return delimiter;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("delimiter", delimiter)
                .append("address1", address1)
                .append("address2", address2)
                .append("address3", address3)
                .append("address4", address4)
                .append("address5", address5)
                .append("postcode", postcode)
                .toString();
    }

}
