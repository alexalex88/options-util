/*
 * Copyright 2012-2016 by Victor Denisov (vdenisov@plukh.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *            http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.plukh.options.impl.collections;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.SortedSet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Alex on 19.09.2016.
 */
public class OptionsSetTest {
    private OptionsSet set;

    @Before
    public void setUp() {
        set = new OptionsSet(String.class);
    }

    @Test
    public void classMustHaveProperConstructors() throws NoSuchMethodException {
        //Single-arg Class constructor
        set.getClass().getConstructor(Class.class);
        //Dual-arg Class constructor
        set.getClass().getConstructor(Class.class, Class.class);
    }

    @Test
    public void backingCollectionClassMustBeUsed() {
        set = new OptionsSet(String.class, LinkedHashSet.class);
        assertTrue(set.getBackingCollection() instanceof LinkedHashSet);
    }

    @Test
    public void addMethodsShouldThrowExceptionForWrongClasses() {
        try {
            set.add(1);
            fail("Expected exception not thrown for add()");
        } catch (IllegalArgumentException e) {
            //Expected
        }

        try {
            Collection c = new HashSet();
            c.add(1);
            set.addAll(c);
            fail("Expected exception not thrown for addAll()");
        } catch (IllegalArgumentException e) {
            //Expected
        }

    }
}
