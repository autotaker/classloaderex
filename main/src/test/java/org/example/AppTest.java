package org.example;

import org.junit.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.net.URL;
import org.example.core.Core;

import java.io.*;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testClassGetResourceFile() {
        URL url = App.class.getResource("main.txt");
        assertThat(url.getProtocol(), is("file"));
    }

    @Test
    public void testClassGetResourceJar() {
        URL url = Core.class.getResource("hello.txt");
        assertThat(url.getProtocol(), is("jar"));
    }

    @Test
    public void testClassGetResourceFileDoesNotExistIfItIsInJar() {
        URL url = Core.class.getResource("hello.txt");
        File file = new File(url.getPath());
        assertThat(file.exists(), is(false));
    }
    
    @Test
    public void testClassGetResourceFileExists() {
        URL url = App.class.getResource("main.txt");
        File file = new File(url.getPath());
        assertThat(file.exists(), is(true));
    }

    @Test 
    public void testClassLoaderGetResourceAsStreamIsAbsolute() throws IOException {
        try( InputStream in = Core.class.getClassLoader().getResourceAsStream("main.txt")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            assertThat(br.readLine(), is("This is resource at root"));
        }
    }

    @Test 
    public void testClassGetResourceAsStreamIsRelativeToPackage() throws IOException {
        try( InputStream in = App.class.getResourceAsStream("main.txt")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            assertThat(br.readLine(), is("This is resource at org.example"));
        }
    }
    
    @Test 
    public void testClassGetResourceAsStreamIsAbsoluteIfPathStartWithSlash() throws IOException {
        try( InputStream in = App.class.getResourceAsStream("/main.txt")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            assertThat(br.readLine(), is("This is resource at root"));
        }
    }

}
