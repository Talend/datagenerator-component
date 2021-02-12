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

import com.datagenerator.talend.components.service.Types;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.condition.ActiveIf;
import org.talend.sdk.component.api.configuration.constraint.Max;
import org.talend.sdk.component.api.configuration.constraint.Min;
import org.talend.sdk.component.api.configuration.constraint.Required;
import org.talend.sdk.component.api.configuration.ui.DefaultValue;
import org.talend.sdk.component.api.configuration.ui.OptionsOrder;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.configuration.ui.widget.TextArea;
import org.talend.sdk.component.api.meta.Documentation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
@GridLayout({ @GridLayout.Row({ "name" }), @GridLayout.Row({ "type"}), @GridLayout.Row({ "blank" }), @GridLayout.Row({ "regex" }),
        @GridLayout.Row({ "length" }), @GridLayout.Row({ "min" }), @GridLayout.Row({ "max" }), @GridLayout.Row({ "startTime" }),
        @GridLayout.Row({ "endTime" }), @GridLayout.Row({ "increment" }), @GridLayout.Row({ "freetext" }),
        @GridLayout.Row({ "randomwithinlist" }) })
@Documentation("Field configuration.")
public class FieldConfiguration implements Serializable {

    @Option
    @Required
    @Documentation("Name.")
    private String name;

    @Option
    @Required
    @DefaultValue("FULLNAME")
    @Documentation("Types.")
    private Types type;

    @Option
    @Min(0)
    @Max(100)
    @Documentation("Blank percentage.")
    private Integer blank = 0;

    @Option
    @Required
    @Documentation("From Regex.")
    @ActiveIf(target = "type", value = "CUSTOM")
    private String regex = "(\\w)*";

    @Option
    @Min(1)
    @Max(50)
    @Required
    @Documentation("Length.")
    @DefaultValue("6")
    @ActiveIf(target = "type", value = { "RANDOMINT" })
    private Integer length = 6;

    @Option
    @Required
    @Documentation("Min.")
    @DefaultValue("1")
    @ActiveIf(target = "type", value = { "AGE", "PASSWORD", "RANDOMSTRING", "RANDOMINTBETWEEN", "INCREMENTALINT" })
    private Integer min = 0;

    @Option
    @Required
    @Documentation("Max.")
    @DefaultValue("100")
    @ActiveIf(target = "type", value = { "AGE", "PASSWORD", "RANDOMSTRING", "RANDOMINTBETWEEN" })
    private Integer max = 100;

    @Option
    @Required
    @Documentation("Start Date.")
    @ActiveIf(target = "type", value = { "RANDOMDATEBETWEEN" })
    LocalDate startTime;

    @Option
    @Required
    @Documentation("End Date.")
    @ActiveIf(target = "type", value = { "RANDOMDATEBETWEEN" })
    LocalDate endTime;

    @Option
    @Required
    @Documentation("Increment.")
    @DefaultValue("1")
    @ActiveIf(target = "type", value = { "INCREMENTALINT" })
    private Integer increment = 1;

    @Option
    @TextArea
    @Documentation("Text.")
    @ActiveIf(target = "type", value = { "FREETEXT" })
    private String freetext = "";

    @Option
    @Documentation("List.")
    @ActiveIf(target = "type", value = { "RANDOMWITHINLIST" })
    private List<ListConfiguration> randomwithinlist;
}
