/***********************************************************************
 * Copyright (c) 2006 The Apache Software Foundation.             *
 * All rights reserved.                                                *
 * ------------------------------------------------------------------- *
 * Licensed under the Apache License, Version 2.0 (the "License"); you *
 * may not use this file except in compliance with the License. You    *
 * may obtain a copy of the License at:                                *
 *                                                                     *
 *     http://www.apache.org/licenses/LICENSE-2.0                      *
 *                                                                     *
 * Unless required by applicable law or agreed to in writing, software *
 * distributed under the License is distributed on an "AS IS" BASIS,   *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or     *
 * implied.  See the License for the specific language governing       *
 * permissions and limitations under the License.                      *
 ***********************************************************************/

package org.apache.james.jspf;

import org.apache.james.jspf.core.SPF1Data;

/**
 * This class is used to return the result of an SPF lookup.
 * 
 */

public class SPFResult extends SPFInternalResult {

    private String headerTextAsString = "";

    private String headerName = "Received-SPF";
    
    private String result = null;

    public SPFResult(String result, String resultChar, String explanation, SPF1Data spf1data) {
        super(resultChar, explanation);
        this.result = result;
        this.headerTextAsString = generateHeader(result, spf1data);
    }

    /**
     * Get the full SPF-Header (headername and headertext)
     * 
     * @return SPF-Header
     */
    public String getHeader() {
        return headerName+": "+getHeaderText();
    }

    /**
     * Get the SPF-Headername
     * 
     * @return headername
     */
    public String getHeaderName() {
        return headerName;
    }

    /**
     * Get SPF-Headertext
     * 
     * @return headertext
     */
    public String getHeaderText() {
        return headerTextAsString != null ? headerTextAsString : "";
    }

    /**
     * Generate a SPF-Result header
     * 
     * @param result
     *            The result we should use to generate the header
     */
    private String generateHeader(String result, SPF1Data spfData) {

        StringBuffer headerText = new StringBuffer();

        if (result.equals(SPF1Utils.PASS_CONV)) {
            headerText.append(result + " (spfCheck: domain of "
                    + spfData.getCurrentDomain() + " designates "
                    + spfData.getIpAddress() + " as permitted sender) ");
        } else if (result.equals(SPF1Utils.FAIL_CONV)) {
            headerText.append(result + " (spfCheck: domain of "
                    + spfData.getCurrentDomain() + " does not designate "
                    + spfData.getIpAddress() + " as permitted sender) ");
        } else if (result.equals(SPF1Utils.NEUTRAL_CONV)
                || result.equals(SPF1Utils.NONE_CONV)) {
            headerText.append(result + " (spfCheck: " + spfData.getIpAddress()
                    + " is neither permitted nor denied by domain of "
                    + spfData.getCurrentDomain() + ") ");

        } else if (result.equals(SPF1Utils.SOFTFAIL_CONV)) {
            headerText.append(result + " (spfCheck: transitioning domain of "
                    + spfData.getCurrentDomain() + " does not designate "
                    + spfData.getIpAddress() + " as permitted sender) ");
        } else if (result.equals(SPF1Utils.PERM_ERROR_CONV)) {
            headerText.append(result
                    + " (spfCheck: Error in processing SPF Record) ");

        } else if (result.equals(SPF1Utils.TEMP_ERROR_CONV)) {
            headerText.append(result
                    + " (spfCheck: Error in retrieving data from DNS) ");

        }

        String headerTextAsString;
        if (headerText.length() > 0) {
            headerText.append("client-ip=" + spfData.getIpAddress()
                    + "; envelope-from=" + spfData.getMailFrom() + "; helo="
                    + spfData.getHostName() + ";");
            headerTextAsString = headerText.toString();
        } else {
            headerTextAsString = "";
        }
        return headerTextAsString;
    }

    /**
     * Get the result string
     * 
     * @see SPF1Utils
     * @return result
     */
    public String getResult() {
        return result;
    }

    /**
     * Get the explanation string
     * If no explanation exists return the empty string
     * 
     * @return explanation
     */
    public String getExplanation() {
        return explanation != null ? explanation : "";
    }
    
    
}