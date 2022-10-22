package com.example.io_nio;

public class Main {
    public static void main(String[] args) {
        System.out.println(fib(10));
    }
    public static int fib(int n){
        if (n==0||n==1)
            return 0;
        else if(n==2)
            return 1;
        return fib(n - 1) + fib(n - 2);

    }

}
