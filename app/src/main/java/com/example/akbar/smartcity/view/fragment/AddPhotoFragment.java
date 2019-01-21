package com.example.akbar.smartcity.view.fragment;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.akbar.smartcity.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPhotoFragment extends Fragment {

    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.btn_select_image)
    Button btn_select_image;

    Unbinder unbinder;

    private static final int REQUEST_CHOOSE_IMAGE = 3;

    public AddPhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_photo, container, false);

        ButterKnife.bind(this, view);
        unbinder = ButterKnife.bind(this, view);



        btn_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openChooserWithGallery(getActivity(), "Choose Picture",
                        REQUEST_CHOOSE_IMAGE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                CropImage.activity(Uri.fromFile(imageFile))
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setCropShape(CropImageView.CropShape.OVAL)
                        .setFixAspectRatio(true)
                        .start(getActivity());
            }

            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                super.onImagePickerError(e, source, type);
                Toast.makeText(getActivity(), e.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type){
                super.onCanceled(source, type);
            }

        });


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
                Uri resultUri = result.getUri();

                Glide.with(this)
                        .load(new File(resultUri.getPath()))
                        .apply(new RequestOptions().centerCrop())
                        .apply(new RequestOptions())
                        .into(imageView1);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();

                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }

}
