package com.jimboyz;

import com.jimboyz.cims.AppProperties;
import com.jimboyz.cims.controller.AppController;
import com.jimboyz.cims.err.ErrorDialog;
import com.jimboyz.cims.model.server.FileServer;
import com.jimboyz.cims.view.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        try{

            createDir();
            UtilitiesClass.setLaf();
            new FileServer();

            EventQueue.invokeLater(Main::appController);

        } catch (Exception e) {
            ErrorDialog.show(e);
//            throw new RuntimeException(e);
        }

    }

    private static void appController() {
        new AppController();
    }

    private static void createDir() {
        try {
            File file = new File(AppProperties.CIMS_DIR);
            if (!file.exists()) {
                Files.createDirectories(file.toPath());
            }
        } catch (IOException e) {
            ErrorDialog.show(e);
        }
    }

    private static void deleteIndexFile() {
        Path indexPath = Paths.get("/Student");
        if(Files.exists(indexPath)) {
            try {
                Files.walkFileTree(indexPath, new SimpleFileVisitor<Path>() {
                   @Override
                   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                       Files.delete(file);
                       return FileVisitResult.CONTINUE;
                   }

                   @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                       Files.delete(dir);
                       return FileVisitResult.CONTINUE;
                   }

                });

            } catch (IOException e) {
                ErrorDialog.show(e);
            }
        }
    }
}