package com.delgado.math;

public class Triple<T1, T2, T3> {
    private T1 m1;
    private T2 m2;
    private T3 m3;
    
    public Triple(T1 m1, T2 m2, T3 m3) {
	this.m1 = m1;
	this.m2 = m2;
	this.m3 = m3;
    }
    
    public T1 get1() { return m1; }
    public T2 get2() { return m2; }
    public T3 get3() { return m3; }
    
    public void set1(T1 m1) { this.m1 = m1; }
    public void set2(T2 m2) { this.m2 = m2; }
    public void set3(T3 m3) { this.m3 = m3; }
}