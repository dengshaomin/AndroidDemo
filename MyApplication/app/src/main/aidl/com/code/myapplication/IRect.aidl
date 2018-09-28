// ICompute.aidl
package com.code.myapplication;

// Declare any non-default types here with import statements

interface IRect {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
            int area(int length,int width);
            int perimeter(int length,int width);
}
