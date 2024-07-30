package sn.dtclab221.tuto.cornerimageview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {
final static int REQUEST_CODE_CAM = 10;
    final static int REQUEST_CODE_IMG = 11;
    Button camera, gallery;
    ShapeableImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.shapImg);
        camera = findViewById(R.id.camera);
        gallery = findViewById(R.id.gallery);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeImageViewCorner();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             chooseImageFromGallery();
            }
        });
    }

    private void chooseImageFromGallery() {

        Intent  imgIntent= new Intent();
        imgIntent.setType("image/*");
        imgIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(imgIntent, "Selectionner une image"), REQUEST_CODE_IMG);
    }

    private void makeImageViewCorner() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAM){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap != null){
                imageView.setImageBitmap(bitmap);
            }
        }else if (requestCode == REQUEST_CODE_IMG){
            Uri imageUri = data.getData();
            if ( imageUri != null) {
                imageView.setImageURI(imageUri);
            }
        }
    }
}

