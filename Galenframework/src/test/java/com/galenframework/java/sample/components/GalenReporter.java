package com.galenframework.java.sample.components;

/*******************************************************************************
* Copyright 2015 Ivan Shubin http://galenframework.com
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
******************************************************************************/


import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.TestReport;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * A singleton class which is used for storing Galen test reports
 */
public class GalenReporter {

    private static final GalenReporter _instance = new GalenReporter();
    private final List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();

    private GalenReporter() {
    }

    /**
     * Returns a single instance of {@link #GalenReporter}
     * @return an instance of {@link #GalenReporter}
     */
    public static final GalenReporter get() {
        return _instance;
    }

    public TestReport registerTest(String method) {
        GalenTestInfo testInfo = GalenTestInfo.fromString(method);
        tests.add(testInfo);
        return testInfo.getReport();
    }

    public synchronized TestReport registerTest(final String name, final List<String> groups) {
        final GalenTestInfo testInfo = GalenTestInfo.fromString(name, groups);
        tests.add(testInfo);
        return testInfo.getReport();
    }

    public List<GalenTestInfo> getAllTests() {
        return tests;
    }
}
