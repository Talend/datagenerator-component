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

        ZonedDateTime now = ZonedDateTime.now();
        // LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime = now.withZoneSameInstant(id);

        // For each field select we add a value to the record
        for (FieldConfiguration field : fields) {

            boolean canBeBlank = false;
            boolean blank = false;
            if(field.getBlank() > 0) {
                canBeBlank = true;
                if (fake.random().nextInt(0, 100) <= field.getBlank())
                    blank = true;
            }

            switch (field.getType()) {
            // Personal
            case FULLNAME:
                addFieldWithString(b, blank, field.getName(), fake.name().fullName());
                break;
            case FIRSTNAME:
                addFieldWithString(b, blank, field.getName(), fake.name().firstName());
                break;
            case LASTNAME:
                addFieldWithString(b, blank, field.getName(), fake.name().lastName());
                break;
            case MIDDLENAME:
                addFieldWithString(b, blank, field.getName(), fake.name().nameWithMiddle());
                break;
            case AGE:
                if(canBeBlank)
                    addFieldWithString(b, blank, field.getName(),
                            String.valueOf(fake.number().numberBetween(field.getMin(), field.getMax())));
                else
                    b.withInt(field.getName(), fake.number().numberBetween(field.getMin(), field.getMax()));
                break;
            case GENDER:
                addFieldWithString(b, blank, field.getName(), fake.demographic().sex());
                break;
            case MARITALSTATUS:
                addFieldWithString(b, blank, field.getName(), fake.demographic().maritalStatus());
                break;
            case EMAIL:
                addFieldWithString(b, blank, field.getName(), fake.internet().emailAddress());
                break;
            case USERNAME:
                addFieldWithString(b, blank, field.getName(), fake.name().username());
                break;
            case PASSWORD:
                addFieldWithString(b, blank, field.getName(), fake.internet().password(field.getMin(), field.getMax()));
                break;
            case DATEOFBIRTH:
                if(canBeBlank)
                    addFieldWithString(b, blank, field.getName(), String.valueOf(fake.date().birthday()));
                else
                    b.withDateTime(field.getName(), fake.date().birthday());
                break;
            case PHONENUMBER:
                addFieldWithString(b, blank, field.getName(), fake.phoneNumber().phoneNumber());
                break;
            case CELLPHONE:
                addFieldWithString(b, blank, field.getName(), fake.phoneNumber().cellPhone());
                break;
            case NATIONALITY:
                addFieldWithString(b, blank, field.getName(), fake.country().name());
                break;
            case NATIONALITYCODE:
                addFieldWithString(b, blank, field.getName(), fake.country().countryCode2());
                break;
            case CITY:
                addFieldWithString(b, blank, field.getName(), fake.address().city());
                break;
            case STATE:
                addFieldWithString(b, blank, field.getName(), fake.address().state());
                break;
            case STATEABBR:
                addFieldWithString(b, blank, field.getName(), fake.address().stateAbbr());
                break;
            case POSTALCODE:
                addFieldWithString(b, blank, field.getName(), fake.address().zipCode());
                break;
            case STREETADDRESS:
                addFieldWithString(b, blank, field.getName(), fake.address().streetAddress());
                break;
            case FULLADDRESS:
                addFieldWithString(b, blank, field.getName(), fake.address().fullAddress());
                break;
            case STREETNUMBER:
                addFieldWithString(b, blank, field.getName(), fake.address().streetAddressNumber());
                break;
            case APPARTNUMBER:
                addFieldWithString(b, blank, field.getName(), fake.address().buildingNumber());
                break;
            case CARDNUMBER:
                addFieldWithString(b, blank, field.getName(),fake.business().creditCardNumber());
                break;
            case EXPIRYDATE:
                addFieldWithString(b, blank, field.getName(), fake.business().creditCardExpiry());
                break;
            case VENDOR:
                addFieldWithString(b, blank, field.getName(), fake.business().creditCardType());
                break;
            case PASSPORTNUMBER:
                addFieldWithString(b, blank, field.getName(), fake.regexify("[A-Z]{1}[0-9]{7}"));
                break;
            case COMPANYDOMAIN:
                addFieldWithString(b, blank, field.getName(), fake.company().industry());
                break;
            case COMPANYNAME:
                addFieldWithString(b, blank, field.getName(), fake.company().name());
                break;
            case COMPANYURL:
                addFieldWithString(b, blank, field.getName(), fake.company().url());
                break;
            case UUID:
                addFieldWithString(b, blank, field.getName(), fake.internet().uuid());
                break;
            case ISBN10:
                addFieldWithString(b, blank, field.getName(),  fake.code().isbn10());
                break;
            case ISBN13:
                addFieldWithString(b, blank, field.getName(), fake.code().isbn13());
                break;
            case FREETEXT:
                addFieldWithString(b, blank, field.getName(), field.getFreetext());
                break;
            // Random
            case RANDOMINTBETWEEN:
                if(canBeBlank)
                    addFieldWithString(b, blank, field.getName(), String.valueOf(fake.number().numberBetween(field.getMin(), field.getMax() + 1)));
                else
                    b.withInt(field.getName(), fake.number().numberBetween(field.getMin(), field.getMax() + 1));
                break;
            case RANDOMSTRING:
                addFieldWithString (b, blank, field.getName(),
                        fake.regexify("(\\w){" + field.getMin().toString() + "," + field.getMax().toString() + "}"));
                break;
            case RANDOMWITHINLIST:
                addFieldWithString(b, blank, field.getName(), weightedlists.get(field.getName()).getRandom());
                break;
            case RANDOMBOOLEAN:
                if(canBeBlank)
                    addFieldWithString(b, blank, field.getName(), String.valueOf(fake.bool().bool()));
                else
                    b.withBoolean(field.getName(), fake.bool().bool());
                break;
                case RANDOMINT:
                    if (canBeBlank) {
                        addFieldWithString(b, blank, field.getName(),
                                String.valueOf(fake.number().randomNumber(field.getLength(), false)));
                    }
                    else
                        b.withInt(field.getName(), (int) fake.number().randomNumber(field.getLength(), false));
                    break;
                case RANDOMLONG:
                    if (canBeBlank) {
                        addFieldWithString(b, blank, field.getName(),
                                String.valueOf(fake.number().randomNumber(field.getLength(), false)));
                    }
                    else
                        b.withLong(field.getName(), (Long) fake.number().randomNumber(field.getLength(), false));
                    break;
            case INCREMENTALINT:
                if(canBeBlank)
                    addFieldWithString(b, blank, field.getName(), String.valueOf((iterator * field.getIncrement() + field.getMin())));
                else
                    b.withInt(field.getName(), (iterator * field.getIncrement()) + field.getMin());
                break;
            case CUSTOM:
                addFieldWithString(b, blank, field.getName(), fake.regexify(field.getRegex()));
                break;
            // Dates
            case CURRENTDATETIME:
                if(canBeBlank)
                    addFieldWithString(b, blank, field.getName(), String.valueOf(zonedDateTime));
                else
                    b.withDateTime(field.getName(), zonedDateTime);
                break;
            case CURRENTTIMESTAMP:
                if(canBeBlank)
                    addFieldWithString(b, blank, field.getName(), String.valueOf(zonedDateTime.toEpochSecond()));
                else
                    b.withLong(field.getName(), zonedDateTime.toEpochSecond());
                break;
            case RANDOMDATEBETWEEN:
                if(canBeBlank)
                    addFieldWithString(b, blank, field.getName(), String.valueOf(fake.date().between(java.sql.Date.valueOf(field.getStartTime()),
                            java.sql.Date.valueOf(field.getEndTime()))));
                else
                    b.withDateTime(field.getName(), fake.date().between(java.sql.Date.valueOf(field.getStartTime()),
                            java.sql.Date.valueOf(field.getEndTime())));
                break;
            // Theme
            case BEER:
                addFieldWithString(b, blank, field.getName(), fake.beer().name());
                break;
            case BEERSTYLE:
                addFieldWithString(b, blank, field.getName(), fake.beer().style());
                break;
            case BOOKGENRE:
                addFieldWithString(b, blank, field.getName(), fake.book().genre());
                break;
            case BOOKTITLE:
                addFieldWithString(b, blank, field.getName(), fake.book().title());
                break;
            case BOOKAUTHOR:
                addFieldWithString(b, blank, field.getName(), fake.book().author());
                break;
            case BOOKPUBLISHER:
                addFieldWithString(b, blank, field.getName(), fake.book().publisher());
                break;
            case APPNAME:
                addFieldWithString(b, blank, field.getName(), fake.app().name());
                break;
            case APPVERSION:
                addFieldWithString(b, blank, field.getName(),  fake.app().version());
                break;
            case TEMPERATURECELSIUS:
                addFieldWithString(b, blank, field.getName(), fake.weather().temperatureCelsius(field.getMin(), field.getMax()));
                break;
            case TEMPERATUREFAHRENHEIT:
                addFieldWithString(b, blank, field.getName(), fake.weather().temperatureFahrenheit(field.getMin(), field.getMax()));
                break;
            case WEATHER:
                addFieldWithString(b, blank, field.getName(), fake.weather().description());
                break;
            case ANIMAL:
                addFieldWithString(b, blank, field.getName(),  fake.animal().name());
                break;
            case AIRCRAFT:
                addFieldWithString(b, blank, field.getName(), fake.aviation().aircraft());
                break;
            case AIRPORT:
                addFieldWithString(b, blank, field.getName(), fake.aviation().airport());
                break;
            case FILENAME:
                addFieldWithString(b, blank, field.getName(), fake.file().fileName());
                break;
            case FILEEXTENSION:
                addFieldWithString(b, blank, field.getName(), fake.file().extension());
                break;
            case COLORNAME:
                addFieldWithString(b, blank, field.getName(), fake.color().name());
                break;
            case COLORHEX:
                addFieldWithString(b, blank, field.getName(), fake.color().hex());
                break;
            case CURRENCYCODE:
                addFieldWithString(b, blank, field.getName(), fake.currency().code());
                break;
            case CURRENCYNAME:
                addFieldWithString(b, blank, field.getName(), fake.currency().name());
            default:
                addFieldWithString(b, blank, field.getName(), "");
            }
        }

        return b;
    }

    private void addFieldWithString(Record.Builder b, boolean blank, String name, String value) {
        if(blank)
            value = null;

        b.withString(name, value);
    }

}