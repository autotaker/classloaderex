package org.example;

import org.junit.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

import org.example.core.Core;

import java.io.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testClassGetResourceFile() {
        URL url = App.class.getResource("main.txt");
        assertThat(url.getPath(), endsWith("org/example/main.txt"));
        assertThat(url.getProtocol(), is("file"));
    }

    @Test
    public void testClassCannotGetNonExistingResource() {
        URL url = App.class.getResource("notexists.txt");
        assertThat(url, is(nullValue()));
    }

    @Test
    public void testSystemClassLoaderGetResource() throws Exception {
        URL url = ClassLoader.getSystemResource("org/example/main.txt");
        assertThat(url, is(notNullValue()));
    }

    @Test
    public void testClassGetResourceJar() {
        URL url = Core.class.getResource("hello.txt");
        assertThat(url.getPath(), endsWith("org/example/core/hello.txt"));
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
        try (InputStream in = Core.class.getClassLoader().getResourceAsStream("main.txt")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            assertThat(br.readLine(), is("This is resource at root"));
        }
    }

    @Test
    public void testClassGetResourceAsStreamIsRelativeToPackage() throws IOException {
        try (InputStream in = App.class.getResourceAsStream("main.txt")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            assertThat(br.readLine(), is("This is resource at org.example"));
        }
    }

    @Test
    public void testClassGetResourceAsStreamIsAbsoluteIfPathStartWithSlash() throws IOException {
        try (InputStream in = App.class.getResourceAsStream("/main.txt")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            assertThat(br.readLine(), is("This is resource at root"));
        }
    }

    @Test
    public void testClassGetResourceInTestDir() {
        URL url = AppTest.class.getResource("maintest.txt");
        assertThat(url, is(notNullValue()));
    }

    @Test
    public void testClassGetResourceTestResourceIsVisibleFromMainClass() {
        URL url = App.class.getResource("maintest.txt");
        assertThat(url, is(notNullValue()));
    }

    @Test
    public void testClassGetResourceReturnsNullForInTestDirOfOtherModule() {
        URL url = Core.class.getResource("coretest.txt");
        assertThat(url, is(nullValue()));
    }

    @Test
    public void testClassGetDynamicResourceFromDynamicModule() throws Exception {
        URL url = dynamic.getClass().getResource("dynamic.txt");
        assertThat(url, is(notNullValue()));
    }

    @Test
    public void testClassLoaderGetDynamicResourceFromDynamicModule() throws Exception {
        URL url = dynamic.getClass().getClassLoader().getResource("org/example/dynamic/dynamic.txt");
        assertThat(url, is(notNullValue()));
    }
    
    @Test
    public void testSystemClassLoaderCannotGetDynamicResourceFromDynamicModule() throws Exception {
        URL url = ClassLoader.getSystemResource("org/example/dynamic/dynamic.txt");
        assertThat(url, is(nullValue()));
    }
    
    static URLClassLoader classLoader;
    Object dynamic;

    @Before
    public void setup() throws Exception {
        Class<?> clazz = Class.forName("org.example.dynamic.Dynamic", true, classLoader);
        dynamic = clazz.newInstance();
    }

    @BeforeClass
    public static void setupClass() throws Exception {
        URL path = Paths.get("dynamic").toUri().toURL();
        if( classLoader == null ) {
            classLoader = new URLClassLoader(new URL[]{path});
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        if( classLoader != null ) {
            try {
                classLoader.close();
            } finally {
                classLoader = null;
            }
        }
    }


}
