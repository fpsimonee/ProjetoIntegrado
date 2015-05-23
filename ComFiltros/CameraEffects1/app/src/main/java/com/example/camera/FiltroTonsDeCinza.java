package com.example.camera;


import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


/**
 * Created by Administrador on 21/05/2015.
 */
public class FiltroTonsDeCinza extends FiltroImagem{

    /**
     * Método que aplica o filtro
     * @param sourceRgba Mat aonde será aplicado o filtro.
     * */    @Override
    public Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray) {
        return aplicarFiltro(sourceRgba);
    }

    @Override
    public Mat aplicarFiltro(Mat sourceRgba) {
        Imgproc.cvtColor(sourceRgba, sourceRgba, Imgproc.COLOR_RGB2GRAY);
        Imgproc.cvtColor(sourceRgba, sourceRgba, Imgproc.COLOR_GRAY2RGB, 4);
        return sourceRgba;
    }

}
