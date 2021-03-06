/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.jspf;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.james.jspf.tester.SPFYamlTestDescriptor;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SPFYamlTest extends AbstractYamlTest {

    private static final String YAMLFILE = "pyspf-tests.yml";


    public SPFYamlTest(String name) throws IOException {
        super(name);
    }

    protected SPFYamlTest(SPFYamlTestDescriptor def, String test) {
        super(def, test);
    }

    protected String getFilename() {
        return YAMLFILE;
    }

    public static Test suite() throws IOException {
        return new SPFSuite();
    }

    static class SPFSuite extends TestSuite {

        public SPFSuite() throws IOException {
            super();
            List<SPFYamlTestDescriptor> tests = SPFYamlTestDescriptor.loadTests(YAMLFILE);
            Iterator<SPFYamlTestDescriptor> i = tests.iterator();
            while (i.hasNext()) {
                SPFYamlTestDescriptor o = i.next();
                Iterator<String> ttt = o.getTests().keySet().iterator();
                while (ttt.hasNext()) {
                    addTest(new SPFYamlTest(o, ttt.next()));
                }
            }
        }

    }
}