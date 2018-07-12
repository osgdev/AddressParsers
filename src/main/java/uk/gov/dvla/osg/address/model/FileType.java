package uk.gov.dvla.osg.address.model;

import com.google.gson.annotations.SerializedName;

/**
 * The Enum FileType.
 */
public enum FileType {
    @SerializedName("dpf") DPF,
    @SerializedName("tsv") TSV,
    @SerializedName("v11") V11,
    @SerializedName("v62") V62
}
