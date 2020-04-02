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
                    case AGE:
                        b.withInt(field.getName(), fake.number().numberBetween(field.getMin(), field.getMax()));
                        break;
                    case SEX:
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
                    case STANDARDCODE:
                        switch (field.getCode()) {
                            case ASIN: b.withString(field.getName(), fake.code().asin()); break;
                            case EAN8: b.withString(field.getName(), fake.code().ean8()); break;
                            case EAN13: b.withString(field.getName(), fake.code().ean13()); break;
                            case GTIN8: b.withString(field.getName(), fake.code().gtin8()); break;
                            case GTIN13: b.withString(field.getName(), fake.code().gtin13()); break;
                            case IMEI: b.withString(field.getName(), fake.code().imei()); break;
                            case ISBN10: b.withString(field.getName(), fake.code().isbn10()); break;
                            case ISBN13: b.withString(field.getName(), fake.code().isbn13()); break;
                            default: b.withString(field.getName(), "Something went wrong"); break;
                        }
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
                    default:  b.withString(field.getName(),"Something went wrong");
                }
        }
        return b;
    }
}