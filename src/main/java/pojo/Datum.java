package pojo;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ID Nation",
        "Nation",
        "ID Year",
        "Year",
        "Population",
        "Slug Nation"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("ID Nation")
    private String iDNation;
    @JsonProperty("Nation")
    private String nation;
    @JsonProperty("ID Year")
    private Integer iDYear;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Population")
    private Integer population;
    @JsonProperty("Slug Nation")
    private String slugNation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ID Nation")
    public String getIDNation() {
        return iDNation;
    }

    @JsonProperty("ID Nation")
    public void setIDNation(String iDNation) {
        this.iDNation = iDNation;
    }

    @JsonProperty("Nation")
    public String getNation() {
        return nation;
    }

    @JsonProperty("Nation")
    public void setNation(String nation) {
        this.nation = nation;
    }

    @JsonProperty("ID Year")
    public Integer getIDYear() {
        return iDYear;
    }

    @JsonProperty("ID Year")
    public void setIDYear(Integer iDYear) {
        this.iDYear = iDYear;
    }

    @JsonProperty("Year")
    public String getYear() {
        return year;
    }

    @JsonProperty("Year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonProperty("Population")
    public Integer getPopulation() {
        return population;
    }

    @JsonProperty("Population")
    public void setPopulation(Integer population) {
        this.population = population;
    }

    @JsonProperty("Slug Nation")
    public String getSlugNation() {
        return slugNation;
    }

    @JsonProperty("Slug Nation")
    public void setSlugNation(String slugNation) {
        this.slugNation = slugNation;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
