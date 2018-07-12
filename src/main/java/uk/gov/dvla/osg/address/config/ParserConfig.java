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
     * Gets the dpf parser configuration.
     *
     * @return the dpf
     */
    public DpfConfig getDpf() {
        return dpf;
    }
    
    /**
     * Gets the tsv parser configuration.
     *
     * @return the tsv
     */
    public TsvConfig getTsv() {
        return tsv;
    }

    /**
     * Gets the configuration for a specific application type e.g. V11.
     *
     * @param type the type
     * @return the app config
     */
    public DelimitedConfig getAppConfig(FileType type) {
        return other.get(type);
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("dpf", dpf).append("tsv", tsv).append("other", other).toString();
    }

}
