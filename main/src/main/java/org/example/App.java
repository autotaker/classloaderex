package org.example;

import org.example.core.Core;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "hello.txt: " + Core.class.getResource("hello.txt"));
        System.out.println( "main.txt: " + App.class.getResource("main.txt"));
        System.out.println( "maintest.txt: " + App.class.getResource("maintest.txt"));
    }
}
