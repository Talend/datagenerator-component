/*
 * Copyright (C) 2006-2021 Talend Inc. - www.talend.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.datagenerator.talend.components.service;

import com.datagenerator.talend.components.datastore.DataGeneratorDatastore;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.service.Service;

import java.util.Arrays;

import org.talend.sdk.component.api.service.completion.SuggestionValues;
import org.talend.sdk.component.api.service.completion.Suggestions;

@Service
public class UIActionService extends GeneratorService {

    public static final String LOCALE_CHOICES = "LOCALE_CHOICES";

    @Suggestions(LOCALE_CHOICES)
    public SuggestionValues getLocaleChoices(@Option final DataGeneratorDatastore datastore) {

        return new SuggestionValues(true,
                Arrays.asList(new SuggestionValues.Item("bg", "bg"), new SuggestionValues.Item("ca", "ca"),
                        new SuggestionValues.Item("ca-CAT", "ca-CAT"), new SuggestionValues.Item("da-DK", "da-DK"),
                        new SuggestionValues.Item("de", "de"), new SuggestionValues.Item("de-AT", "de-AT"),
                        new SuggestionValues.Item("de-CH", "de-CH"), new SuggestionValues.Item("en", "en"),
                        new SuggestionValues.Item("en-AU", "en-AU"), new SuggestionValues.Item("en-au-ocker", "en-au-ocker"),
                        new SuggestionValues.Item("en-BORK", "en-BORK"), new SuggestionValues.Item("en-CA", "en-CA"),
                        new SuggestionValues.Item("en-GB", "en-GB"), new SuggestionValues.Item("en-IND", "en-IND"),
                        new SuggestionValues.Item("en-MS", "en-MS"), new SuggestionValues.Item("en-NEP", "en-NEP"),
                        new SuggestionValues.Item("en-NG", "en-NG"), new SuggestionValues.Item("en-NZ", "en-NZ"),
                        new SuggestionValues.Item("en-PAK", "en-PAK"), new SuggestionValues.Item("en-SG", " en-SG"),
                        new SuggestionValues.Item("en-UG", "en-UG"), new SuggestionValues.Item("en-US", "en-US"),
                        new SuggestionValues.Item("en-ZA", "en-ZA"), new SuggestionValues.Item("es", "es"),
                        new SuggestionValues.Item("es-MX", "es-MX"), new SuggestionValues.Item("fa", "fa"),
                        new SuggestionValues.Item("fi-FI", "fi-FI"), new SuggestionValues.Item("fr", "fr"),
                        new SuggestionValues.Item("he", "he"), new SuggestionValues.Item("hu", "hu"),
                        new SuggestionValues.Item("in-ID", "in-ID"), new SuggestionValues.Item("it", "it"),
                        new SuggestionValues.Item("ja", "ja"), new SuggestionValues.Item("ko", "ko"),
                        new SuggestionValues.Item("nb-NO", "nb-NO"), new SuggestionValues.Item("nb-NO", "nb-NO"),
                        new SuggestionValues.Item("nl", "nl"), new SuggestionValues.Item("pl", "pl"),
                        new SuggestionValues.Item("pt", "DE pt"), new SuggestionValues.Item("pt-BR", "pt-BR"),
                        new SuggestionValues.Item("ru", "ru"), new SuggestionValues.Item("it", "IT locale"),
                        new SuggestionValues.Item("sv", "sv"), new SuggestionValues.Item("sk", "sk"),
                        new SuggestionValues.Item("tr", "tr"), new SuggestionValues.Item("sv-SE", "sv-SE"),
                        new SuggestionValues.Item("uk", "uk"), new SuggestionValues.Item("vi", "vi"),
                        new SuggestionValues.Item("zh-CN", "zh-CN"), new SuggestionValues.Item("zh-TW", "zh-TW")
                ));
    }

}
