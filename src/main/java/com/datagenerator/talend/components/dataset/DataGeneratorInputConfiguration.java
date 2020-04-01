package com.datagenerator.talend.components.dataset;

import java.io.Serializable;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.condition.ActiveIf;
import org.talend.sdk.component.api.configuration.constraint.Max;
import org.talend.sdk.component.api.configuration.constraint.Min;
import org.talend.sdk.component.api.configuration.constraint.Required;
import org.talend.sdk.component.api.configuration.ui.DefaultValue;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;

import lombok.Data;
import lombok.ToString;

@Data
@GridLayout({
        @GridLayout.Row({ "dataset" }),
        @GridLayout.Row({ "pseudoStreaming" }),
        @GridLayout.Row({ "subset" }),
        @GridLayout.Row({ "delay" })
})
@Documentation("Data Generator Source Configuration")
@ToString(callSuper = true)
public class DataGeneratorInputConfiguration implements Serializable {

    public static final String NAME = "DataGeneratorInputConfiguration";

    /*
     * DataSet
     */
    @Option
    @Documentation("Data Generator dataSet")
    private DataGeneratorDataset dataset;

    @Option
    @Required
    @DefaultValue("false")
    @Documentation("Pseudo Streaming")
    private Boolean pseudoStreaming;

    @Option
    @Documentation("Subset size")
    @DefaultValue("100")
    @Min(1)
    @Max(1000000000)
    @ActiveIf(target = "pseudoStreaming", value = { "true" })
    private Integer subset = 100;

    @Option
    @Documentation("Delay (ms)")
    @DefaultValue("5000")
    @Min(1)
    @Max(1000000000)
    @ActiveIf(target = "pseudoStreaming", value = { "true" })
    private Integer delay = 5000;
}