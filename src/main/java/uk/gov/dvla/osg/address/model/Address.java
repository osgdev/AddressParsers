package uk.gov.dvla.osg.address.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Address {

    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String postcode;

    public Address(String address1, String address2, String address3, String address4, String address5, String postcode) {
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.address4 = address4;
        this.address5 = address5;
        this.postcode = postcode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getAddress5() {
        return address5;
    }

    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("address1", address1).append("address2", address2).append("address3", address3).append("address4", address4).append("address5", address5).append("postcode", postcode).toString();
    }
}
