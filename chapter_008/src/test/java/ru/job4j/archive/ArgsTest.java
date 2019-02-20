package ru.job4j.archive;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ArgsTest {
    @Before
    public void setUp() {
        File testDir = new File(System.getProperty("java.io.tmpdir") + "/FolderForTestingZipping");
        testDir.mkdirs();
    }

    @Test
    public void whenPackIntoTempFolder() throws IOException {
        new File(System.getProperty("java.io.tmpdir") + "/FolderForTestingZipping", "testFileOne.abc").createNewFile();
        new File(System.getProperty("java.io.tmpdir") + "/FolderForTestingZipping", "testFileTwo.cba").createNewFile();
        String[] args = {"-d", System.getProperty("java.io.tmpdir") + "/FolderForTestingZipping", "-e", "cba", "-o", "ZipTestFile.zip"};
        Args.main(args);
        assertTrue(new File("C:/projects/job4j/chapter_008", "ZipTestFile.zip").exists());
    }

    @After
    public void tearDown() throws Exception {
        new File(System.getProperty("java.io.tmpdir") + "/FolderForTestingZipping", "testFileOne.abc").delete();
        new File(System.getProperty("java.io.tmpdir") + "/FolderForTestingZipping", "testFileTwo.cba").delete();
        new File(System.getProperty("java.io.tmpdir") + "/FolderForTestingZipping").delete();
        new File("C:/projects/job4j/chapter_008", "ZipTestFile.zip").delete();
    }
}