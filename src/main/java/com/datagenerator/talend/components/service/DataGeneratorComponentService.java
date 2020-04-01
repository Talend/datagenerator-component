package com.datagenerator.talend.components.service;

import com.datagenerator.talend.components.dataset.FieldConfiguration;
import com.github.javafaker.Faker;
import org.talend.sdk.component.api.record.Record;
import org.talend.sdk.component.api.service.Service;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DataGeneratorComponentService {

    public Record.Builder addFieldsToRecord(Integer iterator,
                                            Faker fake, List<FieldConfiguration> fields, Record.Builder b) {

        Date date = new Date();

        // For each field select we add a value to the record
        for (FieldConfiguration field : fields) {
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
                    case SEX:
                        b.withString(field.getName(), fake.demographic().sex());
                        break;
                    case EMAIL:
                        b.withString(field.getName(), fake.internet().emailAddress());
                        break;
                    case USERNAME:
                        b.withString(field.getName(), fake.name().username());
                        break;
                    case PASSWORD:
                        b.withString(field.getName(), fake.internet().password());
                        break;
                    case DATEOFBIRTH:
                        b.withDateTime(field.getName(), fake.date().birthday());
                        break;
                    case TELEPHONENUMBER:
                        b.withString(field.getName(), fake.phoneNumber().phoneNumber());
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
                    case POSTALCODE:
                        b.withString(field.getName(), fake.address().zipCode());
                        break;
                    case STREETADDRESS:
                        b.withString(field.getName(), fake.address().streetAddress());
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
                        b.withString(field.getName(), fake.regexify("^(?!^0+$)[a-zA-Z0-9]{3,20}$"));
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
                    // Random
                    case RANDOMINTBETWEEN:
                        b.withInt(field.getName(), fake.number().numberBetween(field.getMin(), field.getMax()));
                        break;
                    case RANDOMSTRING:
                        b.withString(field.getName(), fake.regexify("(\\w){" + field.getMin().toString() + "," + field.getMax().toString() + "}"));
                        break;
                    case RANDOMBOOLEAN:
                        b.withBoolean(field.getName(), fake.bool().bool());
                        break;
                    case RANDOMINT:
                        b.withInt(field.getName(), (int) fake.number().randomNumber(field.getLength(), true));
                        break;
                    case INCREMENTALINT:
                        b.withInt(field.getName(), (iterator * field.getIncrement()) + field.getMin());
                        break;
                    case CUSTOM:
                        b.withString(field.getName(), fake.regexify(field.getRegex()));
                        break;
                    // Dates
                    case CURRENTDATETIME:
                        b.withDateTime(field.getName(), date);
                        break;
                    case CURRENTTIMESTAMP:
                        b.withLong(field.getName(), date.getTime());
                        break;
                    case RANDOMDATEBETWEEN:
                        b.withDateTime(field.getName(), fake.date().between(java.sql.Date.valueOf(field.getStartTime()), java.sql.Date.valueOf(field.getEndTime())));
                        break;
                    default:  // code block
                }
        }
        return b;
    }
}