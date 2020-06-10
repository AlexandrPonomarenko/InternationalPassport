package com.InternationalPassport.helper;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLoader {
    private static final String PATH_LOAD_TWO = "/home/multimedia/www/InternationalPassport/src/main/webapp/WEB-INF/resources/images/";
    private Path path;
    private boolean statusLoad;
    private byte [] byteArrayImage;
    private MultipartFile file;
    private String fileName;
    private String rightPath;
    private String dirName;

    public FileLoader() {
        path = Paths.get(PATH_LOAD_TWO);
    }

    public FileLoader(MultipartFile file) {
        this.file = file;
        this.fileName = file.getOriginalFilename();
    }

    public void loadFile() {
        try {
            if (!Files.exists(Paths.get(PATH_LOAD_TWO + dirName))) {
                createDirectory();
            } else {
                clearDirectory();
            }
            byteArrayImage = file.getBytes();
            path = Paths.get(PATH_LOAD_TWO + transformToStringPath() + file.getOriginalFilename());
            Files.write(path, byteArrayImage);
            statusLoad = true;
            rightPath = path.toString().substring(path.toString().lastIndexOf("/resources/images/"));
        } catch (IOException e) {
            statusLoad = false;
            e.printStackTrace();
        }
    }

    private void createDirectory() throws IOException {
        Files.createDirectory(Paths.get(PATH_LOAD_TWO + dirName));
    }


    private String transformToStringPath () {
        return dirName + "/";
    }

    private void clearDirectory() throws IOException {
        File file = new File(PATH_LOAD_TWO + dirName);
        if (file.isDirectory()) {
            String [] listFiles = file.list();
            if (listFiles != null && listFiles.length > 0) {
                for (String fileName: listFiles) {
                    Files.deleteIfExists(Paths.get(PATH_LOAD_TWO + transformToStringPath() + fileName));
                }
            }
        }
    }

    public Path getPath() {
        return path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRightPath() {
        return rightPath;
    }

    public void setRightPath(String rightPath) {
        this.rightPath = rightPath;
    }

    public boolean isStatusLoad() {
        return statusLoad;
    }

    public void setMultipartFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }
}
