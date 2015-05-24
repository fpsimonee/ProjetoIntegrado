package com.example.natalia.projeto2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
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

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.File;

import static org.opencv.android.Utils.bitmapToMat;
import static org.opencv.android.Utils.matToBitmap;


public class MainActivity extends Activity implements OnItemSelectedListener {

    static{ System.loadLibrary("opencv_java"); }

    Bitmap photoAtualizada = null;
    Bitmap photo = null;


    Button btnTakePhoto;
    ImageView imgTakenPhoto;
    Uri fileUri;
    private static final int CAM_REQUEST = 1313;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.filtro, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnTakePhoto = (Button) findViewById(R.id.button1);
        imgTakenPhoto = (ImageView) findViewById(R.id.imageview1);


        btnTakePhoto.setOnClickListener(new btnTakePhotoClicker());


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST){
            if (resultCode == RESULT_OK){

                photo = (Bitmap) data.getExtras().get("data");
                photoAtualizada = (Bitmap) data.getExtras().get("data");

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
            Intent cameraintent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraintent, CAM_REQUEST);
        }

    }

    private void Foto (Uri photoUri) {

        File imagem = new File(photoUri.getPath());

        if (imagem.exists()) {
            Drawable oldDrawable = imgTakenPhoto.getDrawable();
            if (oldDrawable != null) {
                ((BitmapDrawable) oldDrawable).getBitmap().recycle();
            }

            Bitmap bitmap = BitmapFactory.decodeFile(imagem
                    .getAbsolutePath());
            BitmapDrawable drawable = new BitmapDrawable(this.getResources(),
                    bitmap);
            imgTakenPhoto.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imgTakenPhoto.setImageDrawable(drawable);
        }
    }
    public void verNomes(View view){

        final MediaPlayer verGrupo = MediaPlayer.create(this, R.raw.botao);

        verGrupo.start();

        Toast.makeText(this, "Felipe Simone 20721439\nMarcelo Blanes 20474886\nNatalia Oliveira 20470090\nRenato Costa 20467657", Toast.LENGTH_LONG).show();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        String i = parent.getItemAtPosition(pos).toString();

        switch(i){
            case "Detecção de Bordas":
                Mat a = new Mat();
                Mat b = new Mat();
                Mat ab = new Mat();
                bitmapToMat(photo, a);
                Imgproc.cvtColor(a, b, Imgproc.COLOR_BGR2GRAY);
                Imgproc.Canny(b, ab, 10, 100, 3, true);
                matToBitmap(ab, photo);
                imgTakenPhoto.setImageBitmap(photo);
                break;
            case "Escolher Filtro":
                break;
            case "Foto sem filtros!":
                imgTakenPhoto.setImageBitmap(photo);
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
