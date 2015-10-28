package com.ld.web.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * 
 *<p>Title: FileManager</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: 文件工具</p>
 *
 *@author LD
 *
 *@date 2015-10-28
 */
public class FileManager {

    private static final Logger logger = Logger.getLogger(FileManager.class);

    /**
     * Get suffixName by filename
     * 
     * @param filename
     * @return
     */
    public static String getSuffixName(String filename) {
        String suffixName = filename.substring(filename.lastIndexOf("."));

        logger.info(String.format("Get suffix name: %s by filename: %s", suffixName, filename));
        return suffixName;
    }

    /**
     * Get prefixName by filename
     * 
     * @param filename
     * @return
     */
    public static String getPrefixName(String filename) {
        String prefixName = filename.substring(0, filename.lastIndexOf("."));

        logger.info(String.format("Get prefix name: %s by filename: %s", prefixName, filename));
        return prefixName;
    }

    /**
     * Make dirs by file directory
     * 
     * @param directory
     */
    public static void mkdirs(File directory) {
        if (!directory.exists()) {
            directory.mkdirs();
            logger.info(String.format("Make dirs by directory path: %s", directory.getAbsoluteFile()));
        }
    }

    /**
     * Make dirs by filepath
     * 
     * @param filepath
     */
    public static void mkdirs(String filepath) {
        File directory = new File(filepath);

        mkdirs(directory);
    }

    /**
     * Create File
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public static boolean createFile(File file) throws Exception {
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdirs();
            logger.info(String.format("Make dirs by directory path: %s", directory.getAbsoluteFile()));
        }

        logger.info(String.format("Create file absolute path: %s ", file.getAbsolutePath()));
        return file.createNewFile();
    }

    /**
     * Create temp file
     * 
     * @param prefixName
     * @param suffixName
     * @param directory
     * @return
     * @throws IOException
     */
    public static File createTempFile(String prefixName, String suffixName, File directory) throws IOException {
        if (null != directory) {
            String path = directory.getAbsolutePath();
            logger.info(String.format("Create temp file prefixName: %s, suffixName: %s, directory path: %s", prefixName, suffixName, path));
            mkdirs(path);
        }
        File tempFile = File.createTempFile(prefixName, suffixName, directory);

        logger.info(String.format("Create file absolute path: %s ", tempFile.getAbsolutePath()));
        return tempFile;
    }

    /**
     * Copy srcFile to destFile
     * 
     * @param srcFile
     * @param destFile
     * @return
     */
    public static boolean copyFile(File srcFile, File destFile) {
        mkdirs(destFile.getParentFile());

        try {
            FileUtils.copyFile(srcFile, destFile);
            return true;
        } catch (IOException e) {
            logger.error(String.format("Copy file error by: %s", e.getMessage()), e);
            return false;
        }
    }
}
