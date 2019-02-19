package ru.job4j.scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchTest {

    @Before
    public void setUp() throws Exception {
        File testDir = new File(System.getProperty("java.io.tmpdir") + "/test2/test2/test2");
        testDir.mkdirs();
    }

    @Test
    public void whenSearchFilesInTempDirectory() throws IOException {
        Search search = new Search();
        List<String> exts = new ArrayList<>();
        exts.add("abc");
        exts.add("cba");
        List<File> expected = new ArrayList<>();
        File testOne = new File(System.getProperty("java.io.tmpdir") + "/test2/test2/test2", "testFileOne.abc");
        File testTwo = new File(System.getProperty("java.io.tmpdir") + "/test2/test2/test2", "testFileTwo.cba");
        new File(System.getProperty("java.io.tmpdir") + "/test2/test2/test2", "testFileThree.cbc").createNewFile();
        testOne.createNewFile();
        testTwo.createNewFile();
        expected.add(testOne);
        expected.add(testTwo);
        List<File> result = search.files(System.getProperty("java.io.tmpdir"), exts);
        assertThat(result, is(expected));
    }

    @After
    public void tearDown() throws Exception {
        new File(System.getProperty("java.io.tmpdir") + "/test2/test2/test2", "testFileOne.abc").delete();
        new File(System.getProperty("java.io.tmpdir") + "/test2/test2/test2", "testFileTwo.cba").delete();
        new File(System.getProperty("java.io.tmpdir") + "/test2/test2/test2", "testFileThree.cbc").delete();
        new File(System.getProperty("java.io.tmpdir") + "/test2/test2/test2").delete();
        new File(System.getProperty("java.io.tmpdir") + "/test2/test2").delete();
        new File(System.getProperty("java.io.tmpdir") + "/test2").delete();
    }
}