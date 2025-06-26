package com.jimboyz.cims.model;

import com.github.sarxos.webcam.Webcam;
import com.jimboyz.cims.AppProperties;
//import com.jimboyz.cims.LoadProperties;
import com.jimboyz.cims.MySimpleDateFormat;
import com.jimboyz.cims.err.ErrorDialog;
import com.jimboyz.cims.err.Message;
import com.jimboyz.cims.view.SwingUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Properties;

public class WebcamModel {

    AppProperties appProperties = new AppProperties();
    Properties properties  = new Properties();
    boolean isCaptured = false;

    public void setImage(JLabel lblImage, JDialog dialog) {
        if(isCaptured) {
            lblImage.setText("");
            lblImage.setIcon(SwingUtil.newImageIcon(getSaveFilename(), lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
            dialog.dispose();
        }
    }

    public void setPreviewImage(JPanel board, Webcam webcam, JLabel lblImage) {
        CardLayout cl = (CardLayout) board.getLayout();
        BufferedImage bufferedImage = webcam.getImage();
        lblImage.setIcon(SwingUtil.newImageIcon(bufferedImage, lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
        cl.show(board, "preview");
    }

    public void setCapturedImage(Webcam webcam) {
        try {
            isCaptured = true;
            File file = capturedImageFilename();
            ImageIO.write(webcam.getImage(), appProperties.imageFormat, file);
            StudentImageFile.getInstance().setImageFile(file);
//            saveFilename();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File capturedImageFilename() {
        return new File(appProperties.capturedImageLocation + File.separator +"IMGCIMS-"+ MySimpleDateFormat.getInstance().format(new Date(), "MMddyyyyhhmmssa") + "." + appProperties.imageFormat);
    }

    public void retakeImage(JPanel board) {
        CardLayout cl = (CardLayout) board.getLayout();
        isCaptured = false;
//        LoadProperties.deleteConfigFile();
        StudentImageFile.getInstance().removeImageFile();
        cl.show(board, "webcam panel");
    }

    @Deprecated(forRemoval = true, since = "current-version (2.0)")
    private void saveFilename() {
        // Change to StudentImageFile
        try(FileOutputStream fos = new FileOutputStream(AppProperties.CIMS_DIR + File.separator + AppProperties.CAPTURED_IMG_FILE)) {
            properties.setProperty("app.image.filename", capturedImageFilename().getAbsolutePath());
            properties.store(fos, "jimBoYz Ni ChOy!!!");

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private String getSaveFilename() {
//       return properties.getProperty("app.image.filename");
        return StudentImageFile.getInstance().getImageFile().getAbsolutePath();
    }

    public byte[] getImageByte(File filename) {

        try {
            if(filename != null) {
                return Files.readAllBytes(filename.toPath());
            }
        } catch (IOException e) {
            ErrorDialog.error(e);
        }

        return null;
    }
}
