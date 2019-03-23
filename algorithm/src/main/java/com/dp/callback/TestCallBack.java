package com.dp.callback;

public class TestCallBack {
   
    public void compute(int n, ComputeCallBack callback) { 
        for (int i = 0; i < n; i++) { 
            System.out.println(i); 
        } 
        callback.onComputeEnd(); 
    } 
} 