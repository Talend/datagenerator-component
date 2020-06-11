package com.datagenerator.talend.components.dataset;

import lombok.Data;
import com.datagenerator.talend.components.service.Types;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.condition.ActiveIf;
import org.talend.sdk.component.api.configuration.constraint.Max;
import org.talend.sdk.component.api.configuration.constraint.Min;
import org.talend.sdk.component.api.configuration.constraint.Required;
import org.talend.sdk.component.api.configuration.ui.DefaultValue;
import org.talend.sdk.component.api.configuration.ui.OptionsOrder;
import org.talend.sdk.component.api.configuration.ui.widget.TextArea;
import org.talend.sdk.component.api.meta.Documentation;

import java.io.Serializable;
import java.util.List;

@Data
@OptionsOrder({ "item", "weight"})
public class ListConfiguration implements Serializable {

    @Option
    @Required
    @Documentation("Item")
    private String item;

    @Option
    @Required
    @Min(0)
    @Max(1)
    @Documentation("Weight")
    private Float weight;
}
