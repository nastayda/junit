package com.company.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

//@RunWith(JUnitParamsRunner.class)
@RunWith(DataProviderRunner.class)
public class CreateFileTestFunction {
        @Rule
        public TemporaryFolder tmpDir = new TemporaryFolder();

        //Проверка существования файла в указанной директрии
        public boolean isExistedFileByName(Path pathToDirectory, String nameFile) {
                boolean flag = false;
                String listl[] = new File(pathToDirectory.toString()).list();
                for (String item : listl) {
                        if (item.contains(nameFile)) {
                                flag = true;
                                return flag;
                        }
                }
                return flag;
        }

        //Генерация случайного номера в имени файла
        public static Object name() {
                return "test" + Integer.toString(new Random().nextInt());
        }

        @DataProvider //for positive
        public static Object[][] randomFileName() {
                return new Object[][]{
                        { name(), ".txt" },
                        { name(), ".txt" }
                };
        }

        @DataProvider //for negative
        public static Object[][] loadDataFromFile() throws IOException {
                FileReader br = new FileReader("/Users/anastasiadanilkina/my-app-junit/src/test/java/resources/names.data");
                BufferedReader in = new BufferedReader(br);
                // BufferedReader in = new BufferedReader(new InputStreamReader(
               //         CreateFileTestFunction.class.getResourceAsStream("/names.data")));

                List <Object[]> userData = new ArrayList <>();
                String line = in.readLine();
                while (line != null) {
                        userData.add(line.split(";"));
                        line = in.readLine();
                }
                in.close();
                return userData.toArray(new Object[][]{});
        }

        @Test
        @Category(MyCategories.Positive.class)
        @UseDataProvider("randomFileName")
        public void createTxtFile(String nameFile, String ext) {
                System.out.println("Positive 1 ");
                //String nameFile = "testTXT.txt";
                File file = new File(tmpDir.getRoot() + "/" + nameFile + ext);
                try {
                        file.createNewFile();
                } catch (IOException e) {
                        // e.printStackTrace();
                }
                boolean isExistedFile = isExistedFileByName(tmpDir.getRoot().toPath(), nameFile + ext);
                assertThat("TXT file was not created", isExistedFile, is(true));
        }

        @Test
        @Category(MyCategories.Negative.class)
        @UseDataProvider("loadDataFromFile")
        public void createFileInNotExistedDirectory(String nameFile, String ext) {
                System.out.println("Negative 1 ");
                //String nameFile = "testWithoutDir.csv";
                File file = new File(nameFile + "/" + nameFile + ext);
                try {
                        file.createNewFile();
                } catch (IOException e) {
                        //  e.printStackTrace();
                }
                boolean isExistedFile = isExistedFileByName(tmpDir.getRoot().toPath(), nameFile + ext);
                assertThat("File was not created", isExistedFile, is(true));
        }
}
