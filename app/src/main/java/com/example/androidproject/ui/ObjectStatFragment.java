package com.example.androidproject.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidproject.R;

import java.text.MessageFormat;

// Instances of this class are fragments representing a single
// object in our collection.
public class ObjectStatFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    private Integer counter;
    private int[] compteurs =new int[9];
    private String[] photos= new String[10];
    private String[] texts= new String[4];
    public LinearLayout LinearGallery;

    public ObjectStatFragment() {
        // Required empty public constructor
    }
    public static ObjectStatFragment newInstance(Integer counter) {
        ObjectStatFragment fragment = new ObjectStatFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_OBJECT, counter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_OBJECT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        if( args.getInt(ARG_OBJECT) == 1)
            return inflater.inflate(R.layout.fragment_basic_data, container, false);

        if( args.getInt(ARG_OBJECT) == 2)
            return inflater.inflate(R.layout.fragment_stat_players, container, false);

        if( args.getInt(ARG_OBJECT) == 3)
            return inflater.inflate(R.layout.fragment_stat_players, container, false);

        return inflater.inflate(R.layout.fragment_gallery_photo, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        //TextView textViewCounter  = view.findViewById(R.id.TextView01);
        //textViewCounter.setText(Integer.toString(args.getInt(ARG_OBJECT)));
        StatisticsActivity st = new StatisticsActivity();
        if( args.getInt(ARG_OBJECT) == 1){
            texts = st.getTexts();
            setBasic();
        }
        if( args.getInt(ARG_OBJECT) == 2){
            compteurs = st.getCompteurs();
            setStat1();
        }
        if( args.getInt(ARG_OBJECT) == 3){
            compteurs = st.getCompteurs();
            setStat2();
        }
        if( args.getInt(ARG_OBJECT) == 4){
            photos = st.getPhotos();
            setPic();
        }
    }


    public void setBasic() {

        TextView tv0 = (TextView) getView().findViewById(R.id.TextView10);
        tv0.setText(texts[4]);
        TextView tv1 = (TextView) getView().findViewById(R.id.TextView03);
        tv1.setText(texts[0]);
        TextView tv2 = (TextView) getView().findViewById(R.id.TextView04);
        tv2.setText(texts[1]);
        TextView tv3 = (TextView) getView().findViewById(R.id.TextView06);
        tv3.setText(texts[2]);
        TextView tv4 = (TextView) getView().findViewById(R.id.TextView08);
        tv4.setText(texts[3]);
    }

    public void setStat1() {
        float total = compteurs[1]+compteurs[2]+compteurs[3]+compteurs[4]+compteurs[5]+compteurs[6]+compteurs[7]+compteurs[8];
        float totalj1 = compteurs[1]+compteurs[2]+compteurs[3]+compteurs[4];
        float totalj2 = compteurs[5]+compteurs[6]+compteurs[7]+compteurs[8];
        float totalpoing_j1 = compteurs[1]+compteurs[3];
        float totalpied_j1 = compteurs[2]+compteurs[4];
        float totalpoing_j2 = compteurs[5]+compteurs[7];
        float totalpied_j2 = compteurs[6]+compteurs[8];
        float totalreussiscoup_j1 = compteurs[1]+compteurs[2];
        float totalreussiscoup_j2 = compteurs[5]+compteurs[6];


        TextView tv1 = (TextView) getView().findViewById(R.id.TextView02);
        tv1.setText(MessageFormat.format("{0}%", (int)((totalj1 / total) * 100)));
        TextView tv2 = (TextView) getView().findViewById(R.id.TextView04);
        tv2.setText(MessageFormat.format("{0}% hand and {1}% foot ", (int)((totalpoing_j1 / totalj1) * 100),(int)((totalpied_j1 / totalj1) * 100)));
        TextView tv3 = (TextView) getView().findViewById(R.id.TextView06);
        tv3.setText(MessageFormat.format("{0}% successful hand punch", (int)((compteurs[1] / totalpoing_j1) * 100)));
        TextView tv4 = (TextView) getView().findViewById(R.id.TextView07);
        tv4.setText(MessageFormat.format("{0}% successful foot kick",(int)((compteurs[2] / totalpied_j1) * 100)));
        TextView tv5 = (TextView) getView().findViewById(R.id.TextView09);
        tv5.setText(MessageFormat.format("{0}% of failure in the global defense", (int)((totalreussiscoup_j2 / totalj2) * 100)));
        TextView tv6 = (TextView) getView().findViewById(R.id.TextView10);
        tv6.setText(MessageFormat.format("{0}% of failure in the top defense", (int)((compteurs[5] / totalpoing_j2) * 100)));
        TextView tv7 = (TextView) getView().findViewById(R.id.TextView11);
        tv7.setText(MessageFormat.format("{0}% of failure in the bottom defense", (int)((compteurs[6] / totalpied_j2) * 100)));
    }

    public void setStat2() {
        float total = compteurs[1]+compteurs[2]+compteurs[3]+compteurs[4]+compteurs[5]+compteurs[6]+compteurs[7]+compteurs[8];
        float totalj1 = compteurs[1]+compteurs[2]+compteurs[3]+compteurs[4];
        float totalj2 = compteurs[5]+compteurs[6]+compteurs[7]+compteurs[8];
        float totalpoing_j1 = compteurs[1]+compteurs[3];
        float totalpied_j1 = compteurs[2]+compteurs[4];
        float totalpoing_j2 = compteurs[5]+compteurs[7];
        float totalpied_j2 = compteurs[6]+compteurs[8];
        float totalreussiscoup_j1 = compteurs[1]+compteurs[2];
        float totalreussiscoup_j2 = compteurs[5]+compteurs[6];


        TextView tv1 = (TextView) getView().findViewById(R.id.TextView02);
        tv1.setText(MessageFormat.format("{0}%", (int)((totalj2 / total) * 100)));
        TextView tv2 = (TextView) getView().findViewById(R.id.TextView04);
        tv2.setText(MessageFormat.format("{0}% hand and {1}% foot ", (int)((totalpoing_j2 / totalj2) * 100),(int)((totalpied_j2 / totalj2) * 100)));
        TextView tv3 = (TextView) getView().findViewById(R.id.TextView06);
        tv3.setText(MessageFormat.format("{0}% successful hand punch", (int)((compteurs[5] / totalpoing_j2) * 100)));
        TextView tv4 = (TextView) getView().findViewById(R.id.TextView07);
        tv4.setText(MessageFormat.format("{0}% successful foot kick",(int)((compteurs[6] / totalpied_j2) * 100)));
        TextView tv5 = (TextView) getView().findViewById(R.id.TextView09);
        tv5.setText(MessageFormat.format("{0}% of failure in the global defense", (int)((totalreussiscoup_j1 / totalj1) * 100)));
        TextView tv6 = (TextView) getView().findViewById(R.id.TextView10);
        tv6.setText(MessageFormat.format("{0}% of failure in the top defense", (int)((compteurs[1] / totalpoing_j1) * 100)));
        TextView tv7 = (TextView) getView().findViewById(R.id.TextView11);
        tv7.setText(MessageFormat.format("{0}% of failure in the bottom defense", (int)((compteurs[2] / totalpied_j1) * 100)));
    }



    public void setPic() {

        for(int i= 0; i < photos.length;i++){

            ImageView image = new ImageView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            image.setLayoutParams(lp);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(photos[i], bmOptions);
            image.setImageBitmap(bitmap);
            LinearGallery = (LinearLayout) getView().findViewById(R.id.Lineargallery);
            LinearGallery.addView(image);
        }

    }
}
