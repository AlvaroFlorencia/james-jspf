/***********************************************************************
 * Copyright (c) 1999-2006 The Apache Software Foundation.             *
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

package org.apache.james.spf;

import java.util.ArrayList;
import java.util.Collection;

public class SPF1Record {

    private Collection directives = new ArrayList();

    private Collection modifiers = new ArrayList();
    
    /**
     * Return the directives as Collection
     * 
     * @return directives Collection of all qualifier+mechanism which should be used
     */
    public Collection getDirectives() {
        return directives;
    }


    /**
     * Return the modifiers as Collection
     * 
     * @return modifiers Collection of all modifiers which should be used
     */
    public Collection getModifiers() {
        return modifiers;
    }

}
