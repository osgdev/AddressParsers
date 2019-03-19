package uk.gov.dvla.osg.address.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class Address.
 */
public class Address {

    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String postcode;

    /**
     * Builder for an address object.
     */
    public static class AddressBuilder {
        private String nestedAddress1;
        private String nestedAddress2;
        private String nestedAddress3;
        private String nestedAddress4;
        private String nestedAddress5;
        private String nestedPostcode;
        
        /**
         * Gets the single instance of AddressBuilder.
         *
         * @return single instance of AddressBuilder
         */
        public static AddressBuilder getInstance() {
            return new AddressBuilder();
        }
        
        /**
         * Instantiates a new address builder.
         */
        private AddressBuilder () { }
        
        /**
         * Address line 1.
         *
         * @param newAddress1 the new address line 1
         * @return the address builder
         */
        public AddressBuilder address1(String newAddress1) throws IllegalArgumentException {
            if (StringUtils.isAllBlank(newAddress1)) {
                throw new IllegalArgumentException("Address line 1 must not be blank");
            }
            this.nestedAddress1 = newAddress1;
            return this;
        }
        
        /**
         * Address line 2.
         *
         * @param newAddress2 the new address line 2
         * @return the address builder
         */
        public AddressBuilder address2(String newAddress2) {
            this.nestedAddress2 = newAddress2;
            return this;
        }
        
        /**
         * Address line 3.
         *
         * @param newAddress3 the new address line 3
         * @return the address builder
         */
        public AddressBuilder address3(String newAddress3) {
            this.nestedAddress3 = newAddress3;
            return this;
        }
        
        /**
         * Address line 4.
         *
         * @param newAddress4 the new address line 4
         * @return the address builder
         */
        public AddressBuilder address4(String newAddress4) {
            this.nestedAddress4 = newAddress4;
            return this;
        }
        
        /**
         * Address line 5.
         *
         * @param newAddress5 the new address line 5
         * @return the address builder
         */
        public AddressBuilder address5(String newAddress5) {
            this.nestedAddress5 = newAddress5;
            return this;
        }
        
        /**
         * Postcode.
         *
         * @param newPostcode the new postcode
         * @return the address builder
         */
        public AddressBuilder postcode(String newPostcode) {
            this.nestedPostcode = newPostcode;
            return this;
        }
        
        /**
         * Builds the Address.
         *
         * @return the address
         */
        public Address build() {
            return new Address(this);
        }
    }

    /**
     * Instantiates a new address from the address builder.
     *
     * @param addressBuilder the address builder
     */
    private Address(AddressBuilder addressBuilder) {
        this.address1 = addressBuilder.nestedAddress1;
        this.address2 = addressBuilder.nestedAddress2;
        this.address3 = addressBuilder.nestedAddress3;
        this.address4 = addressBuilder.nestedAddress4;
        this.address5 = addressBuilder.nestedAddress5;
        this.postcode = addressBuilder.nestedPostcode;
    }

    /**
     * Gets the address line 1.
     *
     * @return the address line 1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the address line 1.
     *
     * @param address1 the new address line 1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * Gets the address line 2.
     *
     * @return the address line 2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the address line 2.
     *
     * @param address2 the new address line 2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
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
     * Sets the address line 3.
     *
     * @param address3 the new address line 3
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    /**
     * Gets the address line 4.
     *
     * @return the address line 4
     */
    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    /**
     * Gets the address line 5.
     *
     * @return the address line 5
     */
    public String getAddress5() {
        return address5;
    }

    /**
     * Sets the address line 5.
     *
     * @param address5 the new address line 5
     */
    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    /**
     * Gets the postcode.
     *
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the postcode.
     *
     * @param postcode the new postcode
     * @throws Exception 
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("address1", address1).append("address2", address2).append("address3", address3).append("address4", address4).append("address5", address5).append("postcode", postcode).toString();
    }
}
