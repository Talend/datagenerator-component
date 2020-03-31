package com.datagenerator.talend.components.source;

import java.io.Serializable;

import com.datagenerator.talend.components.dataset.DataGeneratorDataset;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;

@GridLayout({
    // the generated layout put one configuration entry per line,
    // customize it as much as needed
    @GridLayout.Row({ "dataset" }),
    @GridLayout.Row({ "repeat" })
})
@Documentation("TODO fill the documentation for this configuration")
public class DataGeneratorInputMapperConfiguration implements Serializable {
    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private DataGeneratorDataset dataset;

    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private int repeat;

    public DataGeneratorDataset getDataset() {
        return dataset;
    }

    public DataGeneratorInputMapperConfiguration setDataset(DataGeneratorDataset dataset) {
        this.dataset = dataset;
        return this;
    }

    public int getRepeat() {
        return repeat;
    }

    public DataGeneratorInputMapperConfiguration setRepeat(int repeat) {
        this.repeat = repeat;
        return this;
    }
}