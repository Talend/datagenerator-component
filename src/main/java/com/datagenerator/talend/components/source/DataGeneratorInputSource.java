package com.datagenerator.talend.components.source;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.datagenerator.talend.components.dataset.FieldConfiguration;
import com.github.javafaker.App;
import com.github.javafaker.Faker;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.input.Producer;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.record.Record;
import org.talend.sdk.component.api.service.record.RecordBuilderFactory;

import com.datagenerator.talend.components.service.DataGeneratorComponentService;

@Documentation("TODO fill the documentation for this source")
public class DataGeneratorInputSource implements Serializable {

    private final DataGeneratorInputConfiguration configuration;
    private final DataGeneratorComponentService service;
    private final RecordBuilderFactory builderFactory;
    private List<Faker> fakers;
    private Long rows;
    private Long seed;
    private List<String> locales;
    private Integer iteration;

    public DataGeneratorInputSource(@Option("configuration") final DataGeneratorInputConfiguration configuration,
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
        seed = configuration.getDataset().getSeed();
        fakers = new ArrayList<Faker>();

        // Create fairy
        for (String locale : locales)
        {
            if(configuration.getDataset().getCustomSeed() == true) {
                fakers.add(new Faker(new Locale(locale), new Random(seed)));
            } else {
                fakers.add(new Faker(new Locale(locale)));
            }
        }
        iteration = 0;

    }

    @Producer
    public Record next() {

        if (rows == 0) return null; else rows--;

        Random rand = new Random();
        Faker selected_faker = fakers.get(rand.nextInt(fakers.size()));

        List<FieldConfiguration> fields = configuration.getDataset().getFields(); // Get list of fields from configuration
        Record.Builder b = builderFactory.newRecordBuilder(); // Create Record Builder instance

        b = service.addFieldsToRecord(iteration, selected_faker, fields, b);
        iteration++;

        if((iteration % configuration.getSubset()) == 0) {
            try {
                Thread.sleep(configuration.getDelay());
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        // Build the record and return
        return b.build();
    }

    @PreDestroy
    public void release() {
        // this is the symmetric method of the init() one,
        // release potential connections you created or data you cached
    }

}