package uk.gov.dvla.osg.address.model;


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
     * Instantiates a new address from the address builder.
     *
     * @param addressBuilder the address builder
     */
    private Address(AddressBuilder addressBuilder) {
        this.address1 = addressBuilder.address1;
        this.address2 = addressBuilder.address2;
        this.address3 = addressBuilder.address3;
        this.address4 = addressBuilder.address4;
        this.address5 = addressBuilder.address5;
        this.postcode = addressBuilder.postcode;
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
    
    /**
     * Builder for an address object.
     */
    @SuppressWarnings("hiding")
    public static class AddressBuilder {
        private String address1;
        private String address2;
        private String address3;
        private String address4;
        private String address5;
        private String postcode;
        
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
        public AddressBuilder address1(String address1) {
/*            if (StringUtils.isAllBlank(newAddress1)) {
                throw new IllegalArgumentException("Address line 1 must not be blank");
            }*/
            this.address1 = address1;
            return this;
        }
        
        /**
         * Address line 2.
         *
         * @param newAddress2 the new address line 2
         * @return the address builder
         */
        public AddressBuilder address2(String address2) {
            this.address2 = address2;
            return this;
        }
        
        /**
         * Address line 3.
         *
         * @param newAddress3 the new address line 3
         * @return the address builder
         */
        public AddressBuilder address3(String address3) {
            this.address3 = address3;
            return this;
        }
        
        /**
         * Address line 4.
         *
         * @param newAddress4 the new address line 4
         * @return the address builder
         */
        public AddressBuilder address4(String address4) {
            this.address4 = address4;
            return this;
        }
        
        /**
         * Address line 5.
         *
         * @param newAddress5 the new address line 5
         * @return the address builder
         */
        public AddressBuilder address5(String address5) {
            this.address5 = address5;
            return this;
        }
        
        /**
         * Postcode.
         *
         * @param newPostcode the new postcode
         * @return the address builder
         */
        public AddressBuilder postcode(String postcode) {
            this.postcode = postcode;
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
    
}
