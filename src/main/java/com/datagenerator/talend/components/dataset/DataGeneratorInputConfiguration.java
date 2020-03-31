package com.datagenerator.talend.components.dataset;

import java.io.Serializable;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;

import lombok.Data;
import lombok.ToString;

@Data
@GridLayout({
        @GridLayout.Row({ "dataset" }),
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

}