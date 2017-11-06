package com.example.yasha.carcontrol;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.bumptech.glide.signature.StringSignature;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import static java.lang.System.load;


/**
 * A simple {@link Fragment} subclass.
 */
public class KameraAtasFragment extends Fragment {
    private static final String TAG = KameraAtasFragment.class.getSimpleName();

    private FirebaseDatabase fDb;

    private ImageView imgVideo;
    private ImageView imgVideoBuffer;
    private FirebaseStorage fSt;
//    private StorageReference storageReference;

    public KameraAtasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kamera_atas, container, false);
        imgVideo = (ImageView) view.findViewById(R.id.imgVideo);
        imgVideoBuffer = (ImageView) view.findViewById(R.id.imgVideoBuffer);
        fDb = FirebaseDatabase.getInstance();
        fSt = FirebaseStorage.getInstance();
/*        StorageReference storageReference = fSt.getReference("Cyqqo98wl6amXIrufPlTzvFVGQg1.jpg");
        Glide.with(getContext())
                .using(new FirebaseImageLoader())
                .load(storageReference)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imgVideo);*/
        fDb.getReference("/profile/Cyqqo98wl6amXIrufPlTzvFVGQg1/imageTime/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Data Changed");

                StorageReference storageReference = FirebaseStorage.getInstance().getReference("Cyqqo98wl6amXIrufPlTzvFVGQg1.jpg");
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        new DownloadImage().execute(uri.toString());
                    }
                });
//                String url = storageReference.getDownloadUrl().getResult().toString();
//                AsyncTask.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        //TODO your background code
//                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                // Got the download URL for 'users/me/profile.png'
//                                Bitmap bitmap = null;
//                                try {
//                                    bitmap = Glide.
//                                            with(getContext()).
//                                            load(uri.toString()).
//                                            asBitmap().
//                                            into(854, 480). // Width and height
//                                            get();
//
//                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
//                                    Glide.with(getContext())
//                                            .load(stream.toByteArray())
//                                            .asBitmap()
//                                            .into(imgVideo);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                } catch (ExecutionException e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception exception) {
//                                // Handle any errors
//                            }
//                        });
//
//                    }
//                });


//                Glide.with(getContext())
//                        .using(new FirebaseImageLoader())
//                        .load(storageReference)
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .skipMemoryCache(true)
//                        .into(imgVideo);
//                Glide.with(getContext())
//                        .using(new FirebaseImageLoader())
//                        .load(storageReference)
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        .skipMemoryCache(true)
//                        .into(imgVideoBuffer);
                Log.d(TAG, "Image Downloaded") ;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

    // DownloadImage AsyncTask
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // Set the bitmap into ImageView
            imgVideo.setImageBitmap(result);
            imgVideoBuffer.setImageBitmap(result);
        }
    }
}