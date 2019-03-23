package com.dp.callback;

public class Main {
    public static void main(String[] args) {
        new TestCallBack().compute(1000, () ->
                System.out.println("end back!!!"));
    }
}

//public class Main {
//    public static void main(String[] args) {
//        new TestCallBack().compute(1000, new ComputeCallBack() {
//
//            @Override
//            public void onComputeEnd() {
//                System.out.println("end back!!!");
//
//            }
//        });
//    }
//}