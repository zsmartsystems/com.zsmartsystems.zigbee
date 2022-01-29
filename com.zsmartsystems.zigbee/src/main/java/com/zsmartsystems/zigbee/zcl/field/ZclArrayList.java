/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * An implementation of {@link List} class defining a ZigBee ZCL Array, holding a {@link ZclDataType} and a list of
 * corresponding values as an {@link Object}
 *
 * @author Chris Jackson
 *
 */
public class ZclArrayList implements List<Object> {
    private ZclDataType dataType;
    private List<Object> list = new ArrayList<>();

    public ZclArrayList() {
    }

    public ZclArrayList(ZclDataType dataType) {
        this.dataType = dataType;
    }

    public ZclArrayList(ZclDataType dataType, List<Object> values) {
        this.dataType = dataType;
        this.list = values;
    }

    /**
     * @return the dataType
     */
    public ZclDataType getDataType() {
        return dataType;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(ZclDataType dataType) {
        this.dataType = dataType;
    }

    /**
     * Gets the number of elements in the array
     *
     * @return the number of elements in the array
     */
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean add(Object newValue) {
        return list.add(newValue);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<Object> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Object> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Object> c) {
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Object get(int index) {
        return list.get(index);
    }

    @Override
    public Object set(int index, Object element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, Object element) {
        list.add(index, element);
    }

    @Override
    public Object remove(int index) {
        return remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<Object> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<Object> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataType == null) ? 0 : dataType.hashCode());
        result = prime * result + ((list == null) ? 0 : list.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ZclArrayList other = (ZclArrayList) obj;
        if (dataType != other.dataType) {
            return false;
        }
        if (list == null) {
            if (other.list != null) {
                return false;
            }
        } else if (!list.equals(other.list)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ZclArrayList [dataType=" + dataType + ", values=" + list + "]";
    }
}
