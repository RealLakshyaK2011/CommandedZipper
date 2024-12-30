package net.chauhandevs.utils.commandedzipper;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.lingala.zip4j.*;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;

public class CommandedZipper {
    public static void main(String[] args) {
        if(args.length < 2) return;
        if(!folderExists(args[0])) throw new IllegalArgumentException("The provided folder: " + args[0] + " does not exists!");
        if(!folderExists(args[1])) throw new IllegalArgumentException("The provided folder: " + args[1] + " does not exists!");

        System.out.println(System.getProperty("user.dir"));
        File[] fileFolderList;
        File rootDir = new File(args[0]);

        fileFolderList = rootDir.listFiles();

        ZipParameters params = new ZipParameters();
        params.setCompressionLevel(CompressionLevel.NO_COMPRESSION);

        ZipFile targetFile = new ZipFile(args[1] + "/" + getFolderName(args[0]) + ".zip");
        
        File currentFile = null;
        try{
        for(File f: fileFolderList){
            currentFile = f;
            if(f.isDirectory()){
                targetFile.addFolder(f);
            }else{
                targetFile.addFile(f);
            }

            System.out.println("----" + f.getName());
            System.out.println("It is " + ( !f.isDirectory() ? "not " : "") + "a directory");
        }
        }catch(ZipException e){
            System.err.println("Caught an exception while zipping the file/folder: " + currentFile.getName() + ", reason: " + e.getMessage());
        }

        fileFolderList = null;
    }

    public static String getFolderName(String path){
        String[] splitment = path.split("[\\\\/]");
        return splitment[splitment.length-1];
    }

    public static boolean folderExists(String path){
        File tF = new File(path);

        return  tF.exists() && tF.isDirectory();
    }

    // public static boolean isValidPath(String path){
    //     String _1st = path.replaceAll("[\\\\/]", "/");
    //     String _2nd = _1st.replaceAll("[/]{2,}", "/");
    //     boolean equals = _1st.equals(_2nd);

    //     if(!equals) return false;
    //     String startTrimmedPath = path.matches("[\\\\/].*") ? path.substring(1) : path;
    //     String[] splittedPath = startTrimmedPath.split("[\\\\/]");

    //     for(String part: splittedPath){
    //         if(!part.matches("[A-Za-z0-9_\\-\\.]+")){
    //             return false;
    //         }
    //         if(part.endsWith(".") || part.endsWith(" ")){
    //             return false;
    //         }
    //     }

    //     return true;
    // }
}
