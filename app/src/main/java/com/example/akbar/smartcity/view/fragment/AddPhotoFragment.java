package com.example.akbar.smartcity.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.akbar.smartcity.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPhotoFragment extends Fragment {

    @BindView(R.id.imageViewValue)
    ImageView imageView1;
    @BindView(R.id.btn_select_image)
    Button btn_select_image;

//    Unbinder unbinder;
//
//    private static final int REQUEST_CHOOSE_IMAGE = 3;



    public AddPhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_photo, container, false);

        ButterKnife.bind(this, view);
//        unbinder = ButterKnife.bind(this, view);

        btn_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EasyImage.openChooserWithGallery(getActivity(), "Choose Picture",
//                        REQUEST_CHOOSE_IMAGE);
            }
        });

        return view;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // method untuk menghandle ketika user sudah memilih gambar.
//        // ketika gambar sudah dipilih maka gambar akan di redirect ke activity
//        // library android-image-picker
//        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
//            @Override
//            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
//                CropImage.activity(Uri.fromFile(imageFile))
//                        //u can comment 3 lines of code for hide cropping circle image
////                        .setGuidelines(CropImageView.Guidelines.ON)
////                        .setCropShape(CropImageView.CropShape.OVAL)
////                        .setFixAspectRatio(true)
//                        .start(getActivity());
//            }
//
//            @Override
//            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
//                super.onImagePickerError(e, source, type);
//                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCanceled(EasyImage.ImageSource source, int type) {
//                super.onCanceled(source, type);
//            }
//        });
//        // ----
//
//        // Method ini berfungsi ketika sudah selesai dari activity android-image-picker
//        // Jika result_ok maka gambar yang sudah di crop akan dimasukan kedalam imageview
//        // yang kita olah menggunakan library glide.
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == Activity.RESULT_OK) {
//                Uri resultUri = result.getUri();
//
//                Glide.with(this)
//                        .load(new File(resultUri.getPath()))
//                        //u can use this method for display circle picture
////                        .apply(new RequestOptions().circleCrop())
//                        .apply(new RequestOptions())
//                        .into(imageView1);
//
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Exception error = result.getError();
//
//                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        unbinder.unbind();
//    }

}
