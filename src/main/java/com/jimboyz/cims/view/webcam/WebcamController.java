package com.jimboyz.cims.view.webcam;

import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.jimboyz.cims.model.WebcamModel;
//import com.jimboyz.cims.ui.StudentInfoPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Deprecated(forRemoval = true)
public class WebcamController implements WebcamListener, ActionListener {

    private CameraView cameraView;
    private WebcamModel webcamModel;
//    private StudentInfoPage studentInfoPage;

//    public WebcamController(StudentInfoPage studentInfoPage, CameraView cameraView, WebcamModel webcamModel) {
//        this.cameraView = cameraView;
//        this.webcamModel = webcamModel;
//        this.studentInfoPage = studentInfoPage;
//    }

    public void init() {
        cameraView.setCaptureImageListener(this);
        cameraView.setOkImageListener(this);
        cameraView.setRetakeImageListener(this);
    }

    @Override
    public void webcamOpen(WebcamEvent webcamEvent) {

    }

    @Override
    public void webcamClosed(WebcamEvent webcamEvent) {

    }

    @Override
    public void webcamDisposed(WebcamEvent webcamEvent) {

    }

    @Override
    public void webcamImageObtained(WebcamEvent webcamEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

//        switch (cmd) {
//            case "btn-capture" -> {
//                webcamModel.setCapturedImage(cameraView.webcam);
//                webcamModel.setPreviewImage(cameraView.boardPanel, cameraView.webcam, cameraView.lblPreview);
//                studentInfoPage.filename = null;
//            }
//            case "btn-ok" -> webcamModel.setImage(studentInfoPage.lblPhoto, cameraView);
//            case "btn-retake" -> webcamModel.retakeImage(cameraView.boardPanel);
//        }

//        if(cmd.equals("btn-capture")) {
//            webcamModel.setCapturedImage(cameraView.webcam);
//            webcamModel.setPreviewImage(cameraView.boardPanel, cameraView.webcam, cameraView.lblPreview);
//
//        } else if(cmd.equals("btn-ok")) {
//
//            webcamModel.setImage(studentInfoPage.lblPhoto, cameraView);
//
//        } else if(cmd.equals("btn-retake")) {
//            webcamModel.retakeImage(cameraView.boardPanel);
//        }
    }
}
