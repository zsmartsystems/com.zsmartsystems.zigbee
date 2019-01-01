/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * A class of helper methods for running tests.
 * 
 * @author Chris Jackson
 *
 */
public class TestUtilities {

    /**
     * Set the value of a private field in a class
     * 
     * @param clazz the class where the field resides
     * @param object the object where the field resides
     * @param fieldName the field name
     * @param newValue the Object to be set
     * @throws Exception
     */
    static public void setField(Class clazz, Object object, String fieldName, Object newValue) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(object, newValue);
    }

    /**
     * Invokes a private method
     *
     * @param clazz the class where the method resides
     * @param object the object where the method resides
     * @param methodName the method name
     * @param params an array of parameters - each parameter is the parameter class, followed by the parameter
     * @return the return object from the method
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    static public Object invokeMethod(Class clazz, Object object, String methodName, Object... params)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        int paramCount = params.length;
        Method method;
        Class<?>[] classArray = new Class<?>[paramCount / 2];
        Object[] paramArray = new Object[paramCount / 2];
        for (int i = 0; i < paramCount; i += 2) {
            classArray[i] = (Class) params[i];
            paramArray[i] = params[i + 1];
        }
        method = clazz.getDeclaredMethod(methodName, classArray);
        method.setAccessible(true);
        return method.invoke(object, paramArray);
    }

}
