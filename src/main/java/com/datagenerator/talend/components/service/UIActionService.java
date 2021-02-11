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
package com.datagenerator.talend.components.service;

import com.datagenerator.talend.components.datastore.DataGeneratorDatastore;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.service.Service;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.talend.sdk.component.api.service.completion.SuggestionValues;
import org.talend.sdk.component.api.service.completion.Suggestions;

@Service
public class UIActionService extends GeneratorService {

    public static final String LOCALE_CHOICES = "LOCALE_CHOICES";

    @Suggestions(LOCALE_CHOICES)
    public SuggestionValues getLocaleChoices(@Option final DataGeneratorDatastore datastore) throws IOException {

        String locales = "service/locales.txt";
        Collection localesList = new ArrayList();

        System.out.println("getResourceAsStream : " + locales);
        InputStream is = this.getFileFromResourceAsStream(locales);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        while (reader.ready()) {
            String line = reader.readLine();
            localesList.add(new SuggestionValues.Item(line, line));
        }
        reader.close();
        is.close();
        return new SuggestionValues(true, localesList);
    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    /*
     * The resource URL is not working in the JAR
     * If we try to access a file that is inside a JAR,
     * It throws NoSuchFileException (linux), InvalidPathException (Windows)
     * 
     * Resource URL Sample: file:java-io.jar!/json/file1.json
     */
    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            // return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

}
