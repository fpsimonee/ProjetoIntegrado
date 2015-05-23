package com.example.camera;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * Created by Administrador on 21/05/2015.
 */
public class FiltroContraste extends FiltroImagem {
    @Override
    public Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray) {
        return aplicarFiltro(sourceRgba);
    }

    /**
     * Método que aplica o filtro
     * @param sourceRgba Mat aonde será aplicado o filtro.
     * */
    @Override
    public Mat aplicarFiltro(Mat sourceRgba) {

        Mat matConstraste = toGray(sourceRgba);

        Mat matAux = new Mat(sourceRgba.rows(), sourceRgba.cols(),
                CvType.CV_8UC3);

        matConstraste.convertTo(matAux, -1, 2.2, 50.0);
        matAux.release();

        return matConstraste;
    }

}
