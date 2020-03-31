package com.datagenerator.talend.components.datastore;

import java.io.Serializable;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.type.DataStore;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;

@DataStore("DataGeneratorDatastore")
@GridLayout({
    // the generated layout put one configuration entry per line,
    // customize it as much as needed
})
@Documentation("TODO fill the documentation for this configuration")
public class DataGeneratorDatastore implements Serializable {
}