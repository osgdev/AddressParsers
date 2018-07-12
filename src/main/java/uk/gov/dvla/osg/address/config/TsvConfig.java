package uk.gov.dvla.osg.address.config;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.SerializedName;

public class TsvConfig {

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
    

    /**
     * Gets the address line 1.
     *
     * @return the address line 1
     */
    public int getAddress1() {
        return address1.intValue();
    }

    /**
     * Gets the address line 2.
     *
     * @return the address line 2
     */
    public int getAddress2() {
        return address2.intValue();
    }

    /**
     * Gets the address line 3.
     *
     * @return the address line 3
     */
    public int getAddress3() {
        return address3.intValue();
    }

    /**
     * Gets the address line 4.
     *
     * @return the address line 4
     */
    public int getAddress4() {
        return address4.intValue();
    }

    /**
     * Gets the address line 5.
     *
     * @return the address line 5
     */
    public int getAddress5() {
        return address5.intValue();
    }

    /**
     * Gets the postcode.
     *
     * @return the postcode
     */
    public int getPostcode() {
        return postcode.intValue();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("address1", address1)
                .append("address2", address2)
                .append("address3", address3)
                .append("address4", address4)
                .append("address5", address5)
                .append("postcode", postcode)
                .toString();
    }
}
