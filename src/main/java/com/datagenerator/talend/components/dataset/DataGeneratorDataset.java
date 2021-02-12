/*
 * Copyright (C) 2006-2021 Talend Inc. - www.talend.com
 *
 * This source code is available under agreement available at
 * %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
 *
 * You should have received a copy of the agreement
 * along with this program; if not, write to Talend SA
 * 9 rue Pages 92150 Suresnes, France
 */
package com.datagenerator.talend.components.dataset;

import java.io.Serializable;
import java.util.List;

import com.datagenerator.talend.components.service.TimeZones;
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
import org.talend.sdk.component.api.configuration.ui.widget.Structure;
import org.talend.sdk.component.api.configuration.ui.widget.TextArea;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;

import static com.datagenerator.talend.components.service.UIActionService.LOCALE_CHOICES;
import static org.talend.sdk.component.api.configuration.ui.layout.GridLayout.FormType.ADVANCED;

@Data
@DataSet("DataGeneratorDataset")
@Documentation("Data Generator Dataset.")
@ToString
@GridLayout({ @GridLayout.Row({ "datastore" }), @GridLayout.Row({ "rows" }),
        @GridLayout.Row({ "fields" }) })
@GridLayout(names = ADVANCED, value = { @GridLayout.Row({ "customSeed" }), @GridLayout.Row({ "customLocale" }),
        @GridLayout.Row({ "locales" }), @GridLayout.Row({ "seed" }),
        @GridLayout.Row({ "zone" }) })

public class DataGeneratorDataset implements Serializable {

    @Option
    @Documentation("Connection.")
    private DataGeneratorDatastore datastore;

    @Option
    @Required
    @DefaultValue("10000")
    @Min(1)
    @Max(1000000000)
    @Documentation("Rows.")
    private Long rows;

    @Option
    @Required
    @Documentation("Fields.")
    private List<FieldConfiguration> fields;

    @Option
    @Required
    @DefaultValue("false")
    @Documentation("Custom Locale.")
    private Boolean customLocale;

    @Option
    @Documentation("Select a locale to generate data in the associated language.")
    @ActiveIf(target = "customLocale", value = { "true" })
    @Suggestable(value = LOCALE_CHOICES, parameters = { "../datastore" })
    private List<String> locales;

    @Option
    @Required
    @DefaultValue("false")
    @Documentation("Custom Seed.")
    private Boolean customSeed;

    @Option
    @DefaultValue("123456")
    @Documentation("Seed.")
    @ActiveIf(target = "customSeed", value = { "true" })
    private Long seed;

    @Option
    @DefaultValue("GMTP00")
    @Documentation("Time zone.")
    private TimeZones zone = TimeZones.GMTP00;

}