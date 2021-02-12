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

import com.datagenerator.talend.components.dataset.FieldConfiguration;
import com.github.javafaker.Faker;
import org.talend.sdk.component.api.configuration.ui.widget.DateTime;
import org.talend.sdk.component.api.record.Record;
import org.talend.sdk.component.api.service.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GeneratorService {

    public Record.Builder addFieldsToRecord(Integer iterator, Faker fake, HashMap<String, WeightedList<String>> weightedlists,
            List<FieldConfiguration> fields, ZoneId id, Record.Builder b) {

        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(now, id);

        // For each field select we add a value to the record
        for (FieldConfiguration field : fields) {

            boolean blank = false;
            if(field.getBlank() > 0)
                if(fake.random().nextInt(0, 100) <= field.getBlank())
                    blank = true;

            switch (field.getType()) {
            // Personal
            case FULLNAME:
                b.withString(field.getName(), fake.name().fullName());
                break;
            case FIRSTNAME:
                b.withString(field.getName(), fake.name().firstName());
                break;
            case LASTNAME:
                b.withString(field.getName(), fake.name().lastName());
                break;
            case MIDDLENAME:
                b.withString(field.getName(), fake.name().nameWithMiddle());
                break;
            case AGE:
                b.withInt(field.getName(), fake.number().numberBetween(field.getMin(), field.getMax()));
                break;
            case GENDER:
                b.withString(field.getName(), fake.demographic().sex());
                break;
            case MARITALSTATUS:
                b.withString(field.getName(), fake.demographic().maritalStatus());
                break;
            case EMAIL:
                b.withString(field.getName(), fake.internet().emailAddress());
                break;
            case USERNAME:
                b.withString(field.getName(), fake.name().username());
                break;
            case PASSWORD:
                b.withString(field.getName(), fake.internet().password(field.getMin(), field.getMax()));
                break;
            case DATEOFBIRTH:
                b.withDateTime(field.getName(), fake.date().birthday());
                break;
            case PHONENUMBER:
                b.withString(field.getName(), fake.phoneNumber().phoneNumber());
                break;
            case CELLPHONE:
                b.withString(field.getName(), fake.phoneNumber().cellPhone());
                break;
            case NATIONALITY:
                b.withString(field.getName(), fake.country().name());
                break;
            case NATIONALITYCODE:
                b.withString(field.getName(), fake.country().countryCode2());
                break;
            case CITY:
                b.withString(field.getName(), fake.address().city());
                break;
            case STATE:
                b.withString(field.getName(), fake.address().state());
                break;
            case STATEABBR:
                b.withString(field.getName(), fake.address().stateAbbr());
                break;
            case POSTALCODE:
                b.withString(field.getName(), fake.address().zipCode());
                break;
            case STREETADDRESS:
                b.withString(field.getName(), fake.address().streetAddress());
                break;
            case FULLADDRESS:
                b.withString(field.getName(), fake.address().fullAddress());
                break;
            case STREETNUMBER:
                b.withString(field.getName(), fake.address().streetAddressNumber());
                break;
            case APPARTNUMBER:
                b.withString(field.getName(), fake.address().buildingNumber());
                break;
            case CARDNUMBER:
                b.withString(field.getName(), fake.business().creditCardNumber());
                break;
            case EXPIRYDATE:
                b.withString(field.getName(), fake.business().creditCardExpiry());
                break;
            case VENDOR:
                b.withString(field.getName(), fake.business().creditCardType());
                break;
            case PASSPORTNUMBER:
                b.withString(field.getName(), fake.regexify("[A-Z]{1}[0-9]{7}"));
                break;
            case COMPANYDOMAIN:
                b.withString(field.getName(), fake.company().industry());
                break;
            case COMPANYNAME:
                b.withString(field.getName(), fake.company().name());
                break;
            case COMPANYURL:
                b.withString(field.getName(), fake.company().url());
                break;
            case UUID:
                b.withString(field.getName(), fake.internet().uuid());
                break;
            case ISBN10:
                b.withString(field.getName(), fake.code().isbn10());
                break;
            case ISBN13:
                b.withString(field.getName(), fake.code().isbn13());
                break;
            case FREETEXT:
                b.withString(field.getName(), field.getFreetext());
                break;
            // Random
            case RANDOMINTBETWEEN:
                b.withInt(field.getName(), fake.number().numberBetween(field.getMin(), field.getMax() + 1));
                break;
            case RANDOMSTRING:
                b.withString(field.getName(),
                        fake.regexify("(\\w){" + field.getMin().toString() + "," + field.getMax().toString() + "}"));
                break;
            case RANDOMWITHINLIST:
                b.withString(field.getName(), weightedlists.get(field.getName()).getRandom());
                break;
            case RANDOMBOOLEAN:
                b.withBoolean(field.getName(), fake.bool().bool());
                break;
            case RANDOMINT:
                b.withInt(field.getName(), (int) fake.number().randomNumber(field.getLength(), false));
                break;
            case INCREMENTALINT:
                b.withInt(field.getName(), (iterator * field.getIncrement()) + field.getMin());
                break;
            case CUSTOM:
                b.withString(field.getName(), fake.regexify(field.getRegex()));
                break;
            // Dates
            case CURRENTDATETIME:
                b.withDateTime(field.getName(), zonedDateTime);
                break;
            case CURRENTTIMESTAMP:
                b.withLong(field.getName(), zonedDateTime.toEpochSecond());
                break;
            case RANDOMDATEBETWEEN:
                b.withDateTime(field.getName(), fake.date().between(java.sql.Date.valueOf(field.getStartTime()),
                        java.sql.Date.valueOf(field.getEndTime())));
                break;
            // Funny
            case BEER:
                b.withString(field.getName(), fake.beer().name());
                break;
            case BEERSTYLE:
                b.withString(field.getName(), fake.beer().style());
                break;
            case BOOKGENRE:
                b.withString(field.getName(), fake.book().genre());
                break;
            case BOOKTITLE:
                b.withString(field.getName(), fake.book().title());
                break;
            case BOOKAUTHOR:
                b.withString(field.getName(), fake.book().author());
                break;
            case BOOKPUBLISHER:
                b.withString(field.getName(), fake.book().publisher());
                break;
            case APPNAME:
                b.withString(field.getName(), fake.app().name());
                break;
            case APPVERSION:
                b.withString(field.getName(), fake.app().version());
                break;
            case TEMPERATURECELSIUS:
                b.withString(field.getName(), fake.weather().temperatureCelsius(field.getMin(), field.getMax()));
                break;
            case TEMPERATUREFAHRENHEIT:
                b.withString(field.getName(), fake.weather().temperatureFahrenheit(field.getMin(), field.getMax()));
                break;
            case WEATHER:
                b.withString(field.getName(), fake.weather().description());
                break;
            case ANIMAL:
                b.withString(field.getName(), fake.animal().name());
                break;
            case AIRCRAFT:
                b.withString(field.getName(), fake.aviation().aircraft());
                break;
            case AIRPORT:
                b.withString(field.getName(), fake.aviation().airport());
                break;
            case FILENAME:
                b.withString(field.getName(), fake.file().fileName());
                break;
            case FILEEXTENSION:
                b.withString(field.getName(), fake.file().extension());
                break;
            case COLORNAME:
                b.withString(field.getName(), fake.color().name());
                break;
            case COLORHEX:
                b.withString(field.getName(), fake.color().hex());
                break;
            case CURRENCYCODE:
                b.withString(field.getName(), fake.currency().code());
                break;
            case CURRENCYNAME:
                b.withString(field.getName(), fake.currency().name());
            default:
                b.withString(field.getName(), "error: field type not found");
            }
        }

        return b;
    }

}