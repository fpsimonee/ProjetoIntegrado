package com.example.camera;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends Activity {
	//Declaração das variaveis
	Button btnTakePhoto;
	ImageView imgTakenPhoto;
	private static final int CAM_REQUEST = 1313;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		btnTakePhoto = (Button) findViewById(R.id.button1);
		imgTakenPhoto = (ImageView) findViewById(R.id.imageview1);
		
		btnTakePhoto.setOnClickListener(new btnTakePhotoClicker());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.camera, menu);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case R.id.rgba:
				viewMode = Util.VIEW_MODE_RGBA;
				break;
			case R.id.negativo:
				viewMode = Util.VIEW_MODE_NEGATIVO;
				break;
			case R.id.laplace:
				viewMode = Util.VIEW_MODE_LAPLACE;
				break;
			case R.id.cinza:
				viewMode = Util.VIEW_MODE_CINZA;
				break;
			case R.id.b_vertical:
				viewMode = Util.VIEW_MODE_VERTICAL;
				break;
			case R.id.b_horizontal:
				viewMode = Util.VIEW_MODE_HORIZONTAL;
				break;
			case R.id.contraste:
				viewMode = Util.VIEW_MODE_CONTRASTE;
				break;
			case R.id.histograma:
				viewMode = Util.VIEW_MODE_HISTOGRAMA;
				break;
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == CAM_REQUEST){
			Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
			imgTakenPhoto.setImageBitmap(thumbnail);
			
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
	
}
