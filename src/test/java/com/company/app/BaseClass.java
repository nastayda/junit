package com.company.app;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.After;
import org.junit.Before;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.rules.TemporaryFolder;

@RunWith(DataProviderRunner.class)
public class BaseClass {

        Path pathToDirectory;
        @Before
        public void createDirectory() throws IOException {
                // real direcrory
                //File path = new File("/Users/anastasiadanilkina/Desktop/my-app/src/test/testDirectory");
                // directory.mkdir();
                //temp directory
                //get the default temporary folders path
                //String default_tmp = System.getProperty("java.io.tmpdir");
                //System.out.println(default_tmp);
                pathToDirectory = Files.createTempDirectory("test");
                //System.out.println("TMP: " + pathToDirectory.toString());
                //return this.pathToDirectory;
        }
        @After
        public void deleteDirectory() {
                if (pathToDirectory != null) {
                        try {
                                FileUtils.cleanDirectory(new File(pathToDirectory.toString()));
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        // System.out.println("TMP: " + pathToDirectory);
                        File directory = new File(pathToDirectory.toString());
                        boolean deleted = directory.delete();
                        if (deleted) {
                                //System.out.println("Yes!");
                        } else
                                System.out.println("No");
                }
        }
}
