package com.datagenerator.talend.components.dataset;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.datagenerator.talend.components.datastore.DataGeneratorDatastore;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.action.Suggestable;
import org.talend.sdk.component.api.configuration.condition.ActiveIf;
import org.talend.sdk.component.api.configuration.constraint.Max;
import org.talend.sdk.component.api.configuration.constraint.Min;
import org.talend.sdk.component.api.configuration.constraint.Required;
import org.talend.sdk.component.api.configuration.type.DataSet;
import org.talend.sdk.component.api.configuration.ui.DefaultValue;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;

import static com.datagenerator.talend.components.service.UIActionService.LOCALE_CHOICES;
import static org.talend.sdk.component.api.configuration.ui.layout.GridLayout.FormType.ADVANCED;

@Data
@DataSet("DataGeneratorDataset")
@Documentation("DataGenerator Dataset")
@ToString
@GridLayout({
        @GridLayout.Row({ "datastore" }),
        @GridLayout.Row({ "rows" }),
        @GridLayout.Row({ "fields" })
})
@GridLayout(names = ADVANCED, value = { @GridLayout.Row({"customLocale"}),  @GridLayout.Row({"locales"}), @GridLayout.Row({"customSeed"}),  @GridLayout.Row({"seed"}) })

public class DataGeneratorDataset implements Serializable {

    @Option
    @Documentation("Connection")
    private DataGeneratorDatastore datastore;

    @Option
    @Required
    @DefaultValue("10000")
    @Min(1)
    @Max(1000000000)
    @Documentation("Rows")
    private Long rows;

    @Option
    @Required
    @Documentation("Fields")
    private List<FieldConfiguration> fields;

    @Option
    @Required
    @DefaultValue("false")
    @Documentation("Custom Locale")
    private Boolean customLocale;

    @Option
    @Documentation("Locales")
    @ActiveIf(target = "customLocale", value = { "true" })
    @Suggestable(value = LOCALE_CHOICES, parameters = { "../datastore" })
    private List<String> locales;

    @Option
    @Required
    @DefaultValue("false")
    @Documentation("Custom Seed")
    private Boolean customSeed;

    @Option
    @DefaultValue("123456")
    @Documentation("Seed")
    @ActiveIf(target = "customSeed", value = { "true" })
    private Long seed;

}