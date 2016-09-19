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

package org.plukh.options.impl.options;

import org.plukh.options.ParseException;

import java.lang.reflect.Constructor;

/**
 * Created by Alex on 18.09.2016.
 */
public class ArbitraryOption extends AbstractOption {
    private Class clazz;

    public ArbitraryOption(Class clazz) {
        this.clazz = clazz;
    }

    public ArbitraryOption(Class clazz, String key, String stringValue) {
        super(key, stringValue);
        this.clazz = clazz;
    }

    public ArbitraryOption(Class clazz, String key, Object value) {
        super(key);
        this.clazz = clazz;
        this.value = value;
        this.stringToValueConverted = true;
        this.valueToStringConverted = false;
    }

    public ArbitraryOption(Class clazz, String key) {
        super(key);
        this.clazz = clazz;
    }

    @Override
    public Object convertStringToValue(String s) throws ParseException {
        if (s == null || s.isEmpty()) return null;
        try {
            Constructor<?> ctor = clazz.getConstructor(String.class);
            return ctor.newInstance(new Object[]{s});
        }
        catch (Exception e) {
            throw new ParseException("Error parsing string: " + s);
        }
    }

    @Override
    public String convertValueToString(Object o) {
        if (o == null) return null;
        return o.toString();
    }

    @Override
    public void setValue(Object value) {
        if (value != null && !(clazz.isInstance(value))) throw new IllegalArgumentException("Can only set value to " + clazz.getName());
        super.setValue(value);
    }
}
