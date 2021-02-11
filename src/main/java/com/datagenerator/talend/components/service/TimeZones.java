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

public enum TimeZones {

    DEFAULT("Default"),
    GMTM12("Etc/GMT-12"),
    GMTM11("Pacific/Pago_Pago"),
    GMTM10("Pacific/Honolulu"),
    GMTM09("Pacific/Gambier"),
    GMTM08("America/Juneau"),
    GMTM07("America/Vancouver"),
    GMTM06("America/Denver"),
    GMTM05("America/Chicago"),
    GMTM04("America/Havana"),
    GMTM03("America/Cayenne"),
    GMTM02("America/Miquelon"),
    GMTM01("Atlantic/Cape_Verde"),
    GMTP00("Etc/UTC"),
    GMTP01("Europe/Dublin"),
    GMTP02("Europe/Paris"),
    GMTP03("Europe/Vilnius"),
    GMTP04("Asia/Dubai"),
    GMTP45("Asia/Kabul"),
    GMTP05("Asia/Karachi"),
    GMTP55("Asia/Colombo"),
    GMTP06("Asia/Almaty"),
    GMTP07("Asia/Bangkok"),
    GMTP08("Asia/Singapore "),
    GMTP09("Asia/Seoul"),
    GMTP95("Australia/Yancowinna"),
    GMTP10("Australia/Melbourne"),
    GMTP11("Pacific/Norfolk"),
    GMTP12("Pacific/Fiji");

    private final String name;

    private TimeZones(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String getName() {
        return this.name;
    }
}
