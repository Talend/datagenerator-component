package com.datagenerator.talend.components.dataset;

import com.datagenerator.talend.components.service.Types;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.condition.ActiveIf;
import org.talend.sdk.component.api.configuration.constraint.Max;
import org.talend.sdk.component.api.configuration.constraint.Min;
import org.talend.sdk.component.api.configuration.constraint.Required;
import org.talend.sdk.component.api.configuration.ui.DefaultValue;
import org.talend.sdk.component.api.configuration.ui.OptionsOrder;
import org.talend.sdk.component.api.meta.Documentation;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@OptionsOrder({ "name", "type", "regex", "length", "min", "max", "startTime", "endTime", "increment"})
public class FieldConfiguration implements Serializable {

    @Option
    @Required
    @Documentation("Name")
    private String name;

    @Option
    @Required
    @Documentation("types")
    private Types type;

    @Option
    @Required
    @Documentation("From Regex")
    @ActiveIf(target = "type",  value = "CUSTOM")
    private String regex = "(\\w)*";

    @Option
    @Min(1)
    @Max(50)
    @Required
    @Documentation("Length")
    @DefaultValue("6")
    @ActiveIf(target = "type", value = {} )
    private Integer length = 6;

    @Option
    @Required
    @Documentation("Min")
    @DefaultValue("1")
    @ActiveIf(target = "type", value = {"PASSWORD", "RANDOMSTRING", "RANDOMINTBETWEEN", "INCREMENTALINT"} )
    private Integer min = 1;

    @Option
    @Required
    @Documentation("Max")
    @DefaultValue("30")
    @ActiveIf(target = "type", value = {"PASSWORD", "RANDOMSTRING", "RANDOMINTBETWEEN", "RANDOMINT"})
    private Integer max = 10;

    @Option
    @Required
    @Documentation("Start Date")
    @ActiveIf(target = "type", value = {"RANDOMDATEBETWEEN"} )
    LocalDate startTime;

    @Option
    @Required
    @Documentation("End Date")
    @ActiveIf(target = "type", value = {"RANDOMDATEBETWEEN"} )
    LocalDate endTime;

    @Option
    @Required
    @Documentation("Increment")
    @DefaultValue("1")
    @ActiveIf(target = "type", value = {"INCREMENTALINT"})
    private Integer increment = 1;
}
