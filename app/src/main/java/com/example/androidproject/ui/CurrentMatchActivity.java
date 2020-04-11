package com.example.androidproject.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
//import androidx.core.content.

import com.example.androidproject.MainActivity;
import com.example.androidproject.R;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentMatchActivity extends AppCompatActivity implements CurrentMatch_j1_fragment.OnFragmentInteractionPlayer1Listener{


    private TextView debugField;
    public LinearLayout LayoutO;
    public TextView name1;
    public TextView name2;
    public TextView textPhoto;
    private int mPosition;
    public int orientation = LinearLayout.VERTICAL;

    private static final String PHOTOS = "photos";
    private static final String TEXTS = "texts";
    private static final String COMPTEURS = "compteurs";

    static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;


    private int[] compteurs ={0,0,0,0,0,0,0,0,0};
    private String[] photos= new String[10];
    private String[] texts;

    //public File getExternalFilesDir(String type);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentmatch);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Current Match");
        setTextbtns();

        Bundle extras = getIntent().getExtras();
        texts = extras.getStringArray("infos");

        /*for (int i =0 ; i< 4 ; i++)
            Log.d("first"+i,""+texts[i]);
            //debugField.setText(debugField.getText() + tab[i]);*/

        LayoutO = (LinearLayout) findViewById(R.id.LayoutOrient);
        LayoutO.setOrientation(orientation);

        name1 = (TextView) findViewById(R.id.TextView01);
        if (texts != null) {
            name1.setText(texts[0]);
        }
        name2 = (TextView) findViewById(R.id.TextView02);
        if (texts != null) {
            name2.setText(texts[1]);
        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},200);//MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION

        }
        /*debugField = (TextView) findViewById(R.id.textview1);
        for (int i =0 ; i< 4 ; i++)
            debugField.setText(debugField.getText() + tab[i]);*/
    }



    @Override
    public void onResume() {
        super.onResume();
        int ori = getResources().getConfiguration().orientation;
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            orientation= LinearLayout.HORIZONTAL;
        } else {
            orientation = LinearLayout.VERTICAL;
        }
        LayoutO.setOrientation(orientation);

    }

    @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArray(PHOTOS,photos);
        outState.putStringArray(TEXTS,texts);
        outState.putIntArray(COMPTEURS,compteurs);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Read the state of item position
        photos = savedInstanceState.getStringArray(PHOTOS);
        texts = savedInstanceState.getStringArray(TEXTS);
        compteurs = savedInstanceState.getIntArray(COMPTEURS);

        setTextPic();
        setTextbtns();
    }




    public void myClickHandler(View view) {
        String tagg = String.valueOf(view.getTag());
        String textat = "" ;

        if (tagg.equals("1") ||tagg.equals("5"))
            textat = getString(R.string.coup_de_npoint_r_ussis);
        if (tagg.equals("2") ||tagg.equals("6"))
            textat = getString(R.string.coup_de_npied_r_ussis);
        if (tagg.equals("3") ||tagg.equals("7"))
            textat = getString(R.string.coup_de_npoint_manqu);
        if (tagg.equals("4") ||tagg.equals("8"))
            textat = getString(R.string.coup_de_npied_manqu);

        if (tagg.equals("photo")) {
            if(compteurs[0]<11){
                dispatchTakePictureIntent();
            }

        }else if(tagg.equals("finish")) {

            // TODO Ici implémenter dans la base toutes les données : texts[], photos[] et compteurs[]


            Log.d("finisher","finiiiiii ----------------------------------");

            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }else{
            int i = Integer.parseInt(tagg);
            compteurs[i]++;
            //view.setText()
            Button boutonscompteurs = view.findViewById(view.getId());
            boutonscompteurs.setText(MessageFormat.format("{0} {1}", textat, compteurs[i]));
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        setTextPic();
        //setPic();
    }

    private  void setTextPic(){
        textPhoto = (TextView) findViewById(R.id.TextViewphoto);
        textPhoto.setText(MessageFormat.format("{0} {1} {2}", getString(R.string.prefixtextphoto), compteurs[0], getString(R.string.sufixtextphoto)));
    }

    private  void setTextbtns(){
        int[] idbutton = {0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8};
        String textat = "" ;
        for(int i = 1;i<9;i++){
            if (i==1||i==5)
                textat = getString(R.string.coup_de_npoint_r_ussis);
            if (i==2||i==6)
                textat = getString(R.string.coup_de_npied_r_ussis);
            if (i==3||i==7)
                textat = getString(R.string.coup_de_npoint_manqu);
            if (i==4||i==8)
                textat = getString(R.string.coup_de_npied_manqu);
            Button boutonscompteurs = findViewById(idbutton[i]);
            boutonscompteurs.setText(MessageFormat.format("{0}{1}", textat, compteurs[i]));
        }

    }
/*
    private void setPic() {
        // Get the dimensions of the View
        ImageView imageView = findViewById(R.id.imageview01);

        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }*/


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //Log.d("imageFileName : ",imageFileName);
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        photos[compteurs[0]]= currentPhotoPath;
        compteurs[0]++;
        Log.d("storageDir : ",""+currentPhotoPath);
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.d("shit","shit ----------------------------------");
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {

                Log.d("file",""+photoFile);
                //Log.d("Uri",""+FileProvider.getUriForFile(this,"com.example.android.fileprovider",photoFile));
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }




    @Override
    public void onFragmentPlayer1Interaction(Object ref) {
        //Log.d("provider : ","=/-----------------------------------------------------------------------------------///");
        //Log.d("provider : ","=/-----------------------------------------------------------------------------------///");


    }

}







