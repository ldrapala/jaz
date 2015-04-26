package luke.jaz.entity;

public class Address extends Entity {

    private AddressType addressType;
    private Province province;
    private String city;
    private String postCode;
    private String street;
    private String nrOfApartament;

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNrOfApartament() {
        return nrOfApartament;
    }

    public void setNrOfApartament(String nrOfApartament) {
        this.nrOfApartament = nrOfApartament;
    }

    @Override
    public String toString() {
        return "Address{" + "addressType=" + addressType + 
                ", province=" + province + ", city=" + city + 
                ", postCode=" + postCode + ", street=" + street + 
                ", nrOfApartament=" + nrOfApartament + '}';
    }
    
}
