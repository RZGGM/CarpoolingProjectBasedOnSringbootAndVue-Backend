package com.gzasc.onlinecarhailing.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAndPathIO {
    //    从目录中得到里面的文件名
    public static List<String> getAllFileNames(String directoryPath) {
        List<String> fileNames = new ArrayList<>();
        File directory = new File(directoryPath);

        // 确保传入的路径是一个目录
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileNames.add(file.getName());
                    } else if (file.isDirectory()) {
                        // 递归获取子目录下的文件名
                        fileNames.addAll(getAllFileNames(file.getAbsolutePath()));
                    }
                }
            }
        }

        return fileNames;
    }

    //    获取所有文件的完整路径
    public static List<String> getAllFilePaths(String directoryPath) {
        List<String> filePaths = new ArrayList<>();
        File directory = new File(directoryPath);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        filePaths.add(file.getAbsolutePath());
                    } else if (file.isDirectory()) {
                        // 递归获取子目录下的文件路径
                        filePaths.addAll(getAllFilePaths(file.getAbsolutePath()));
                    }
                }
            }
        }

        return filePaths;
    }
    //    根据完整的文件路径得到文件内容
//    从传入的文件路径中得到文件内容
    public static String getFileContent(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
