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

import org.talend.sdk.component.api.exception.ComponentException;

public class DataGeneratorException extends ComponentException {

    public DataGeneratorException(String message) {
        super(message);
    }

}