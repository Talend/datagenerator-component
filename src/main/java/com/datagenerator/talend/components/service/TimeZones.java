package com.datagenerator.talend.components.service;
import java.time.ZoneId;
import java.time.ZoneOffset;

public enum TimeZones{

    GMT(0),
    UTC(0),
    ECT(3600),
    EET(7200),
    AR(7200),
    EAT(10800),
    MET(12600),
    NET(14400),
    PLT(18000),
    IST(19800),
    BST(21600),
    VST(25200),
    CTT(28800),
    JST(32400),
    ACT(34200),
    AET(36000),
    SST(39600),
    NST(43200),
    MIT(-39600),
    HST(-36000),
    AST(-32400),
    PST(-28800),
    PNT(-25200),
    MST(-25200),
    CST(-21600),
    EST(-18000),
    IET(-18000),
    PRT(-14400),
    CNT(-12600),
    AGT(-10800),
    BET(-10800),
    CAT(-3600),;

    private int totalSeconds;

    TimeZones(int seconds) {
        this.totalSeconds = totalSeconds;
    }

    public int getOffsetSeconds() {
        return totalSeconds;
    }

}
