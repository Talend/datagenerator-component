package com.datagenerator.talend.components.source;

import java.io.Serializable;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.datagenerator.talend.components.dataset.FieldConfiguration;
import com.github.javafaker.Faker;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.input.Producer;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.record.Record;
import org.talend.sdk.component.api.service.record.RecordBuilderFactory;

import com.datagenerator.talend.components.service.DataGeneratorComponentService;

@Documentation("TODO fill the documentation for this source")
public class DataGeneratorInputSource implements Serializable {

    private final DataGeneratorInputMapperConfiguration configuration;
    private final DataGeneratorComponentService service;
    private final RecordBuilderFactory builderFactory;
    private List<Faker> fakers;
    private Long rows;
    private List<String> locales;
    private Integer iteration;

    public DataGeneratorInputSource(@Option("configuration") final DataGeneratorInputMapperConfiguration configuration,
                        final DataGeneratorComponentService service,
                        final RecordBuilderFactory builderFactory) {
        this.configuration = configuration;
        this.service = service;
        this.builderFactory = builderFactory;
    }

    @PostConstruct
    public void init() {
        // this method will be executed once for the whole component execution,
        // this is where you can establish a connection for instance


        if(configuration.getDataset().getCustomLocale() == true) {
            locales = configuration.getDataset().getLocales();  // Get the different locales (countries for profiles)
        } else {
            locales = new ArrayList<>(Collections.singleton(Locale.ENGLISH.toString()));
        }

        // List<String> locales = new ArrayList<>(Collections.singleton("en"));
        rows = configuration.getDataset().getRows();
        fakers = new ArrayList<Faker>();

        // Create fairy
        for (String locale : locales)
        {
            fakers.add(new Faker(new Locale("locale")));
        }

        iteration = 0;
    }

    @Producer
    public Record next() {
        // this is the method allowing you to go through the dataset associated
        // to the component configuration
        //
        // return null means the dataset has no more data to go through
        // you can use the builderFactory to create a new Record

        // First and foremost
        // If rows limit is reached producer is stopped else rows decremented

        if (rows == 0) return null; else rows--;
        Random rand = new Random();
        Faker selected_faker = fakers.get(rand.nextInt(fakers.size()));

        List<FieldConfiguration> fields = configuration.getDataset().getFields(); // Get list of fields from configuration
        Record.Builder b = builderFactory.newRecordBuilder(); // Create Record Builder instance

        b = service.addFieldsToRecord(iteration,
                                      selected_faker, fields, b);

        iteration++;

        // Build the record and return
        return b.build();
    }

    @PreDestroy
    public void release() {
        // this is the symmetric method of the init() one,
        // release potential connections you created or data you cached
    }

}