package com.example.natalia.projeto2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static org.opencv.android.Utils.bitmapToMat;
import static org.opencv.android.Utils.matToBitmap;


public class MainActivity extends Activity implements OnItemSelectedListener {

    static{ System.loadLibrary("opencv_java"); }


    Bitmap photo = null;
    Bitmap photoAtualizada1 = null;
    Bitmap photoAtualizada2 = null;
    Bitmap photoAtualizada3 = null;
    Bitmap photoAtualizada4 = null;
    Bitmap photoAtualizada5 = null;
    Bitmap photoAtualizada6 = null;
    Bitmap photoUltima = null;
    MediaPlayer botao = null;

    Button btnTakePhoto;
    ImageView imgTakenPhoto;

    private static final int CAM_REQUEST = 1313;

    //Adiciona codigo do botao spinner e do botao de tirar a foto
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = MediaPlayer.create(this, R.raw.b);

        photo = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher);
        photoAtualizada1 = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher);
        photoAtualizada2 = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher);
        photoAtualizada3 = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher);
        photoAtualizada4 = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher);
        photoAtualizada5 = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher);
        photoUltima = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher);

        imgTakenPhoto = (ImageView) findViewById(R.id.imageview1);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.filtro, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnTakePhoto = (Button) findViewById(R.id.button1);



        btnTakePhoto.setOnClickListener(new btnTakePhotoClicker());


    }

    //captura a foto tirada, acessando dispositivo de camera do usuário
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST){
            if (resultCode == RESULT_OK){

                photo = (Bitmap) data.getExtras().get("data");
                photoAtualizada1 = (Bitmap) data.getExtras().get("data");
                photoAtualizada2 = (Bitmap) data.getExtras().get("data");
                photoAtualizada3 = (Bitmap) data.getExtras().get("data");
                photoAtualizada4 = (Bitmap) data.getExtras().get("data");
                photoAtualizada5 = (Bitmap) data.getExtras().get("data");
                photoAtualizada6 = (Bitmap) data.getExtras().get("data");
                photoUltima = (Bitmap) data.getExtras().get("data");
                imgTakenPhoto.setImageBitmap(photo);

            }
            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelado!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Falhou!",
                        Toast.LENGTH_LONG).show();
            }


        }
    }
    class btnTakePhotoClicker implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            botao.start();
            Intent cameraintent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraintent, CAM_REQUEST);
        }

    }




    //Codigo do botao que mostra o nome dos integrantes do grupo
    public void verNomes(View view){

        final MediaPlayer verGrupo = MediaPlayer.create(this, R.raw.b);

        verGrupo.start();

        Toast.makeText(this, "Felipe Simone 20721439\nMarcelo Blanes 20474886\nNatalia Oliveira 20470090\nRenato Costa 20467657", Toast.LENGTH_LONG).show();
    }
    // Adiciona os filtros nos itens selecionados no botao spinner
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        String i = parent.getItemAtPosition(pos).toString();

        switch(i){
            //filtro Tons de cinza
            case "Filtro 1":
                Mat g = new Mat();
                Mat h = new Mat();
                bitmapToMat(photoAtualizada1, g);
                Imgproc.cvtColor(g, h, Imgproc.COLOR_BGR2GRAY);
                matToBitmap(h, photoAtualizada1);
                imgTakenPhoto.setImageBitmap(photoAtualizada1);
                photoUltima = photoAtualizada1;

                break;
            //Filtro Laplaciano
            case "Filtro 2":
                Mat k = new Mat();
                bitmapToMat(photoAtualizada2, k);
                Mat l = new Mat(3,3, CvType.CV_32FC1);
                float[] dt = {0, -1, 0, -1, 4, -1, 0, -1, 0};
                l.put(0,0,dt);
                Mat m = new Mat();
                Imgproc.filter2D(k, m, -1, l);
                matToBitmap(m, photoAtualizada2);
                imgTakenPhoto.setImageBitmap(photoAtualizada2);
                photoUltima = photoAtualizada2;
            //Filtro de Contraste
            case "Filtro 3":
                Mat c = new Mat();
                Mat d = new Mat();

                bitmapToMat(photoAtualizada3, c);
                Imgproc.cvtColor(c, d, Imgproc.COLOR_BGR2GRAY);
                matToBitmap(d, photoAtualizada3);
                imgTakenPhoto.setImageBitmap(photoAtualizada3);
                photoUltima = photoAtualizada3;
                break;
            //Filtro de Contraste
            case "Filtro 4":

                Mat e = new Mat();
                Mat f = new Mat();
                Mat ef = new Mat();
                bitmapToMat(photoAtualizada4, e);
                Imgproc.cvtColor(e, f, Imgproc.COLOR_BGR2GRAY);
                Imgproc.equalizeHist(f, ef);

                matToBitmap(ef, photoAtualizada4);
                imgTakenPhoto.setImageBitmap(photoAtualizada4);
                photoUltima = photoAtualizada4;
                break;
            //Detectar Bordas
            case "Filtro 5":

                Mat a = new Mat();
                Mat b = new Mat();
                Mat ab = new Mat();
                bitmapToMat(photoAtualizada5, a);
                Imgproc.cvtColor(a, b, Imgproc.COLOR_BGR2GRAY);
                Imgproc.Canny(b, ab, 10, 100, 3, true);
                matToBitmap(ab, photoAtualizada5);
                imgTakenPhoto.setImageBitmap(photoAtualizada5);
                photoUltima = photoAtualizada5;
                break;

            case "Escolher Filtro":
                break;
            case "Foto sem filtros!":
                imgTakenPhoto.setImageBitmap(photo);
                photoUltima = photo;
            default:
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
