/*
 * Copyright 2012-2014 by Victor Denisov (vdenisov@plukh.org).
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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class OptionsSet implements CollectionBackedOption, Set{
    public static final Class<? extends Set> DEFAULT_SET_IMPLEMENTATION_CLASS = HashSet.class;

    private final Set set;
    private final Class elementClass;

    public OptionsSet (Class elementClass) {
        this.elementClass = elementClass;
        try {
            set = DEFAULT_SET_IMPLEMENTATION_CLASS.newInstance();
        } catch (IllegalAccessException e) {
            throw new CollectionInitializationException("Error initializing backing Set instance", e);
        } catch (InstantiationException e) {
            throw new CollectionInitializationException("Error initializing backing Set instance", e);
        }
    }

    public OptionsSet(Class elementClass, Class<? extends Set> backingClass) {
        this.elementClass = elementClass;

        try {
            set = backingClass.newInstance();
        } catch (IllegalAccessException e) {
            throw new CollectionInitializationException("Error initializing backing Queue instance for class " +
                    backingClass.getName(), e);
        } catch (InstantiationException e) {
            throw new CollectionInitializationException("Error initializing backing Queue instance for class " +
                    backingClass.getName(), e);
        }
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public boolean removeAll(Collection c) {
        return set.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection c) {
        return set.retainAll(c);
    }

    @Override
    public Collection getBackingCollection() {
        return set;
    }

    @Override
    public Class getCollectionClass() {
        return Set.class;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(Object o) {
        if (o == null) throw new NullPointerException("OptionsSet doesn't allow for null elements");
        if (!(elementClass.isAssignableFrom(o.getClass()))) throw new IllegalArgumentException("Only elements of class " +
                elementClass.getName() + " supported in this OptionsSet instance");
        return set.add(o);
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object o : c) {
            if (o == null) throw new NullPointerException("OptionsSet doesn't allow for null elements");
            if (!(elementClass.isAssignableFrom(o.getClass()))) throw new IllegalArgumentException("Only elements of class " +
                    elementClass.getName() + " supported in this OptionsSet instance");
        }
        return set.addAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
        return set.containsAll(c);
    }
}
