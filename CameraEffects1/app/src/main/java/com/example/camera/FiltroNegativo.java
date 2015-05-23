package com.example.camera;

import org.opencv.core.Mat;




/**
 * Created by Administrador on 21/05/2015.
 */
public class FiltroNegativo extends FiltroImagem {


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

        sourceRgba = new FiltroTonsDeCinza().aplicarFiltro(sourceRgba);

        for (int i = 0; i < sourceRgba.rows() ; ++i) {
            for (int j = 0; j < sourceRgba.cols() ; ++j) {
                double[] pixelValue = sourceRgba.get(i, j);
                pixelValue[0] = 255 - pixelValue[0];
                pixelValue[1] = 255 - pixelValue[1];
                pixelValue[2] = 255 - pixelValue[2];
                sourceRgba.put(i, j, pixelValue);
            }
        }

        return sourceRgba;
    }

}
