package com.datagenerator.talend.components.service;

import com.datagenerator.talend.components.datastore.DataGeneratorDatastore;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.service.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.talend.sdk.component.api.service.completion.SuggestionValues;
import org.talend.sdk.component.api.service.completion.Suggestions;


@Service
public class UIActionService extends DataGeneratorComponentService{

    public static final String LOCALE_CHOICES = "LOCALE_CHOICES";

    @Suggestions(LOCALE_CHOICES)
    public SuggestionValues getLocaleChoices(@Option final DataGeneratorDatastore datastore) {

            return new SuggestionValues(true, Arrays.asList(
                    new SuggestionValues.Item("en-US", "US locale"),
                    new SuggestionValues.Item("en_GB", "UK locale"),
                    new SuggestionValues.Item("de", "DE locale"),
                    new SuggestionValues.Item("fr", "FR locale"),
                    new SuggestionValues.Item("es", "ES locale"),
                    new SuggestionValues.Item("it", "IT locale"),
                    new SuggestionValues.Item("pl", "PL locale")
            ));
    }
}


