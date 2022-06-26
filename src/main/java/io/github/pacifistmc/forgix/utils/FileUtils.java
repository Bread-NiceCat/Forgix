package io.github.pacifistmc.forgix.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<File> manifestJars(File dir) {
        List<File> jars = new ArrayList<>();
        File jarsLocation = new File(dir, "META-INF/jars");
        if (jarsLocation.exists()) {
            File[] list = dir.listFiles();
            if (list == null) return jars;
            for (File jar : list) {
                if (jar.getName().endsWith(".jar")) {
                    jars.add(jar);
                }
            }
        }
        return jars;
    }

    public static List<File> listAllTextFiles(File dir) {
        List<File> files = new ArrayList<>();
        File[] list = dir.listFiles();
        if (list == null) return files;
        for (File file : list) {
            if (file.isDirectory()) {
                files.addAll(listAllTextFiles(file));
            } else {
                if (!FilenameUtils.getExtension(file.getName()).equals("class")) {
                    if (!isBinary(file)) files.add(file);
                }
            }
        }
        return files;
    }

    private static boolean isBinary(File file) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            int read = bis.read();
            while (read != -1) {
                if (isMagicCharacter(read)) return true;
                read = bis.read();
            }
            bis.close();
            return false;
        } catch (IOException exception) {
            return false;
        }
    }

    private static boolean isMagicCharacter(int decimal) {
        if (decimal > 127) return true;
        if (decimal < 37) {
            return decimal != 10 && decimal != 13 && decimal != 9 && decimal != 32 && decimal != 11 && decimal != 12 && decimal != 8;
        }
        return false;
    }
}
