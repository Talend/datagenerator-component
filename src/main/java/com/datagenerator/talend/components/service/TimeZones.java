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
    GMTM12("Etc/GMT+12"),
    GMTM11("Pacific/Samoa"),
    GMTM10("Pacific/Honolulu"),
    GMTM09("America/Anchorage"),
    GMTM08("America/Los_Angeles"),
    GMTM07("America/Denver"),
    GMTM06("America/Regina"),
    GMTM05("America/New_York"),
    GMTM04("America/Santiago"),
    GMTM03("America/Sao_Paulo"),
    GMTM02("Etc/GMT+2"),
    GMTM01("Atlantic/Azores"),
    GMTP00("Etc/UTC"),
    GMTP01("Europe/Paris"),
    GMTP02("Europe/Kiev"),
    GMTP03("Europe/Moscow"),
    GMTP04("Asia/Dubai"),
    GMTP45("Asia/Kabul"),
    GMTP05("Asia/Karachi"),
    GMTP55("Asia/Colombo"),
    GMTP06("Asia/Almaty"),
    GMTP07("Asia/Bangkok"),
    GMTP08("Asia/Singapore"),
    GMTP09("Asia/Seoul"),
    GMTP95("Australia/Darwin"),
    GMTP10("Australia/Sydney"),
    GMTP11("Pacific/Guadalcanal"),
    GMTP12("Pacific/Auckland");

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
