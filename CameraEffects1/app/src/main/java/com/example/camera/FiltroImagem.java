package com.example.camera;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Administrador on 21/05/2015.
 */
public abstract class FiltroImagem {
    Mat matGray= new Mat();


    public FiltroImagem() {
    }

    public abstract Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray);
    public abstract Mat aplicarFiltro(Mat sourceRgba);

    public Mat toGray(Mat sourceRgba) {
        Imgproc.cvtColor(sourceRgba, matGray, Imgproc.COLOR_RGB2GRAY);
        return matGray;
    }
}
