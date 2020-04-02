package com.datagenerator.talend.components.source;

import java.io.Serializable;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.datagenerator.talend.components.DataGeneratorRuntimeException;
import com.datagenerator.talend.components.dataset.FieldConfiguration;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.input.Producer;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.record.Record;
import org.talend.sdk.component.api.service.record.RecordBuilderFactory;

import com.datagenerator.talend.components.service.DataGeneratorComponentService;

@Slf4j
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
        // initialization
        rows = configuration.getDataset().getRows();
        log.info("===== configuration =====");
        log.info("===== rows: " + rows);
        if(configuration.getDataset().getCustomSeed() == true) {
            seed = configuration.getDataset().getSeed();
            log.info("===== seed: " + rows);
        } else {
            log.info("===== no seed specified");
        }
        iteration = 0;
        fakers = new ArrayList<Faker>();
        log.info("===== fakers: " + fakers.toString());

        // safeguards
        if(configuration.isPseudoStreaming()) {
            log.info("Pseudo streaming is enabled.");
            if (configuration.getSubset() > rows) {
                log.error("Subset is greater than total rows.");
                throw new DataGeneratorRuntimeException("With Pseudo Streaming enabled the subset cannot be greater " +
                        "than the total number of rows. Please check your source configuration.");
            }
        }

        // custom locales
        if(configuration.getDataset().getCustomLocale() == true) {
            log.info("Custom locales is enabled.");
            locales = configuration.getDataset().getLocales();
        } else {
            locales = new ArrayList<>(Collections.singleton(Locale.ENGLISH.toString()));
        }

        // create faker generators and add to list
        for (String locale : locales)
        {
            if(configuration.getDataset().getCustomSeed() == true) {
                log.info("Custom seed is enabled.");
                fakers.add(new Faker(new Locale(locale), new Random(seed)));
            } else {
                fakers.add(new Faker(new Locale(locale)));
            }
        }
    }

    @Producer
    public Record next() {
        // check remaining rows
        if (rows == 0) return null; else rows--;

        // use random faker within the list
        Random rand = new Random();
        Faker selected_faker = fakers.get(rand.nextInt(fakers.size()));

        // get list of fields from configuration
        List<FieldConfiguration> fields = configuration.getDataset().getFields();

        // Create Record Builder instance
        Record.Builder b = builderFactory.newRecordBuilder();
        b = service.addFieldsToRecord(iteration, selected_faker, fields, b);

        // increment iteration
        iteration++;

        // if pseudo streaming enabled
        if(configuration.isPseudoStreaming()) {
            if ((iteration % configuration.getSubset()) == 0) {
                try {
                    // sleep for delay every subset
                    Thread.sleep(configuration.getDelay());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
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