package uk.gov.dvla.osg.address.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.SerializedName;

import uk.gov.dvla.osg.address.model.FileType;

public class ParserConfig {

    @SerializedName("dpf")
    private DpfConfig dpf;
    @SerializedName("tsv")
    private TsvConfig tsv;
    @SerializedName("other")
    private Map<FileType, DelimitedConfig> other = new HashMap<>();
    
    /**
     * No args constructor for use in serialization
     * 
     */
    public ParserConfig() {}

    public DpfConfig getDpf() {
        return dpf;
    }
    
    public TsvConfig getTsv() {
        return tsv;
    }

    public DelimitedConfig getAppConfig(FileType type) {
        return other.get(type);
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("dpf", dpf).append("tsv", tsv).append("other", other).toString();
    }

}
