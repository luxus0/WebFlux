package webflux.Domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@JsonInclude(Include.NON_NULL)
@Validated
public class Adress
{
    @NotNull
    private Integer streetNumber;

    @NotNull
    private String streetName;

    @NotNull
    private String city;

    @NotNull
    private String zipCode;

    @NotNull
    private String stateOfProvince;

    @NotNull
    private String country;

    private Adress(){}

    private Adress(Integer streetNumber, String streetName, String city, String zipCode, String stateOfProvince, String country) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.zipCode = zipCode;
        this.stateOfProvince = stateOfProvince;
        this.country = country;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStateOfProvince() {
        return stateOfProvince;
    }

    public void setStateOfProvince(String stateOfProvince) {
        this.stateOfProvince = stateOfProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
