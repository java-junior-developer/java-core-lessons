package com.company.project.multithreading.lesson2930;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
// задача для ForkJoinPool
// Future
// ForkJoinTask
// RecursiveTask<return type> | RecursiveAction
public class FileFinder extends RecursiveTask<File> /* RecursiveAction */ {
    private File file;
    private File directory;

    public FileFinder(File file, File directory) {
        if (file == null)
            throw new IllegalArgumentException("file is null");
        this.file = file;
        if (directory == null || !directory.isDirectory())
            throw new IllegalArgumentException("error in directory");
        this.directory = directory;
    }

    @Override
    protected File compute() {
        File[] subFiles = directory.listFiles();
        List<FileFinder> fileFinders = new ArrayList<>();
        if (subFiles != null){
            for (File subFile : subFiles) {
                if (subFile.isDirectory()){
                    FileFinder subFinder = new FileFinder(file, subFile);
                    subFinder.fork();
                    fileFinders.add(subFinder);
                } else {
                    if (file.getName().equals(subFile.getName())){
                        return subFile.getAbsoluteFile();
                    }
                }
            }
        }
        for (FileFinder fileFinder : fileFinders) {
            File found = fileFinder.join();
            if (found != null && found.getName().equals(file.getName())){
                return found.getAbsoluteFile();
            }
        }

        return null;
    }
}
