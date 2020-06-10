package com.InternationalPassport.helper;

import com.InternationalPassport.springConfigs.SpringJPAConfigTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

@ContextConfiguration(classes = SpringJPAConfigTest.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CheckPathFileTest {
    public static final Logger logger = LogManager.getLogger(CheckPathFileTest.class);

//    Path path = Paths.get("../resources");

    @Test
    public void testPath() {
        String dirName = "img1";
        String dirName2 = "img2";
        Path path = Paths.get("src/resources/will-test-image.jpeg" + dirName + dirName2);
        logger.debug(path.getParent() + " + parent directory");
        Path root = path.getRoot();
        logger.debug(root + " + getRoot");
        logger.debug(path + " + path");
        logger.debug(Files.exists(path) + "IS EXIST");

        assertTrue(true);
    }

    @Test
    public void createFile() throws IOException {
        Path path = Paths.get("src/main/resources/images/test.txt");
        logger.debug(path.getParent() + " + parent directory");
        Path pathTxt = Files.createFile(Paths.get("src/main/resources/images/test.txt"));
        logger.debug(Files.exists(path) + " IS EXIST createFile");
        assertTrue(true);
    }

    @Test
    public void createDirectory() throws IOException{
        Path path = Paths.get("src/main/resources/images/testDir/");
        logger.debug("CREATE DIRECTORY " +  path.toString());
        boolean exist = Files.exists(path);
        if (!exist) {
            Files.createDirectory(path);
        }

        assertTrue(Files.exists(path));

        Files.createFile(Paths.get("src/main/resources/images/testDir/test.txt"));
        Files.deleteIfExists(path);

        assertFalse(Files.exists(path));
    }

    @Test
    public void createDirectoryTwo() throws IOException{
        Path path = Paths.get("src/main/resources/images/testDirTwo/");
        logger.debug("CREATE DIRECTORY TWO" +  path.toString());
        boolean exist = Files.exists(path);
        if (!exist) {
            Files.createDirectory(path);
        }

        assertTrue(Files.exists(path));

        Files.createFile(Paths.get("src/main/resources/images/testDirTwo/test.txt"));
    }

    @Test
    public void findSubstring() {
        String base = "/home/multimedia/www/InternationalPassport/src/main/webapp/WEB-INF/resources/images/CusLOGIN/232252352.png";
        String expect = "resources/images/CusLOGIN/232252352.png";
        String actualStr;
        int actual = base.lastIndexOf("/resources/images/");
        actualStr = base.substring(actual);
        logger.debug(actual + " -- ACTUAL -- " + actualStr);
        assertTrue(true);
    }

}
