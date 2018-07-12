package uk.gov.dvla.osg.address.config;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.SerializedName;

/**
 * The Class DpfConfig.
 */
public class DpfConfig {

    @SerializedName("address1")
    private String address1;
    @SerializedName("address2")
    private String address2;
    @SerializedName("address3")
    private String address3;
    @SerializedName("address4")
    private String address4;
    @SerializedName("address5")
    private String address5;
    @SerializedName("postcode")
    private String postcode;

    /**
     * Gets the address line 1.
     *
     * @return the address line 1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Gets the address line 2.
     *
     * @return the address  line 2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Gets the address line 3.
     *
     * @return the address line 3
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * Gets the address line 4.
     *
     * @return the address 4
     */
    public String getAddress4() {
        return address4;
    }

    /**
     * Gets the address line 5.
     *
     * @return the address 5
     */
    public String getAddress5() {
        return address5;
    }


    /**
     * Gets the postcode.
     *
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("address1", address1).append("address2", address2).append("address3", address3).append("address4", address4).append("address5", address5).append("postcode", postcode).toString();
    }

}
