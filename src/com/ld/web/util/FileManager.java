package com.ld.web.util;

import java.io.File;

import org.apache.log4j.Logger;

public class FileManager {

    private static final Logger logger = Logger.getLogger(FileManager.class);

    /**
     * Get suffixName by filename
     * 
     * @param filename
     * @return
     */
    public static String getSuffixName(String filename) {
        logger.info(String.format("Get suffix name by filename: %s", filename));
        String suffixName = filename.substring(filename.lastIndexOf("."));

        logger.info(String.format("Get suffix name: %s", filename));
        return suffixName;
    }

    /**
     * Make dirs by filepath
     * 
     * @param filepath
     */
    public static void mkdirs(String filepath) {
        File folder = new File(filepath);

        if (!folder.exists()) {
            folder.mkdirs();
            logger.info(String.format("Make dirs by filepath: %s", filepath));
        }
    }

    public static void createFile(File file) {
        String filepath = file.getParent();
        mkdirs(filepath);
    }

    public static void main(String[] args) {
        System.out.println(getSuffixName("123.jpg"));
        mkdirs("d://adbv/asd");
        createFile(new File("D://asd/asd/asd.jpg"));
    }
}
