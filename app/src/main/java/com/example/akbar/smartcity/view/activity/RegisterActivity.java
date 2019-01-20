package com.example.akbar.smartcity.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.akbar.smartcity.R;
import com.example.akbar.smartcity.api.BaseApiService;
import com.example.akbar.smartcity.api.Server;
import com.example.akbar.smartcity.model.ResponsModelUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.input_nama) EditText editTextNama;
    @BindView(R.id.input_email) EditText editTextEmail;
    @BindView(R.id.input_nohp) EditText editTextNoHp;
    @BindView(R.id.input_alamat) EditText editTextAlamat;
    @BindView(R.id.input_username_registrasi) EditText editTextUsername;
    @BindView(R.id.input_password_registrasi) EditText editTextPassword;
    @BindView(R.id.input_pass_val) EditText editTextPassVal;
    @BindView(R.id.registrasi) Button buttonRegister;

    private String nama, email, nohp, alamat, username, password, passval;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        loading = new ProgressDialog(this);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.register));

    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean isPasswordConfValid(String passwordConf) {
        return passwordConf.length() > 4;
    }

    private void validation(){

        boolean cancel = false;
        View focusView = null;

        nama = editTextNama.getText().toString();
        email = editTextEmail.getText().toString();
        nohp = editTextNoHp.getText().toString();
        alamat = editTextAlamat.getText().toString();
        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();
        passval = editTextPassVal.getText().toString();

        // Check for a valid name.
        if (TextUtils.isEmpty(nama)) {
            editTextNama.setError(getString(R.string.error_field_required));
            focusView = editTextNama;
            cancel = true;

        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError(getString(R.string.error_field_required));
            focusView = editTextEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            editTextEmail.setError(getString(R.string.error_invalid_email));
            focusView = editTextEmail;
            cancel = true;
        }

        // Check for a valid nohp
        if (TextUtils.isEmpty(nohp)) {
            editTextNoHp.setError(getString(R.string.error_field_required));
            focusView = editTextNoHp;
            cancel = true;
        }

        // Check for a valid address
        if (TextUtils.isEmpty(alamat)) {
            editTextAlamat.setError(getString(R.string.error_field_required));
            focusView = editTextAlamat;
            cancel = true;

        }

        // Check for a valid username
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError(getString(R.string.error_field_required));
            focusView = editTextUsername;
            cancel = true;

        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            editTextPassword.setError(getString(R.string.error_invalid_password));
            focusView = editTextPassword;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            editTextPassword.setError(getString(R.string.error_field_required));
            focusView = editTextPassword;
            cancel = true;
        }


        if (TextUtils.isEmpty(passval)) {
            editTextPassVal.setError(getString(R.string.error_field_required));
            focusView = editTextPassVal;
            cancel = true;

        }

        if (isPasswordValid(password) != isPasswordConfValid(passval)) {
            editTextPassVal.setError(getString(R.string.error_passwordConf));
            focusView = editTextPassVal;
            cancel = true;

        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            register();
        }

    }

    private void register(){
        loading.setMessage("Mengirim Data ...");
        loading.setCancelable(false);
        loading.show();
        BaseApiService apiService = Server.getUrl().create(BaseApiService.class);
        Call<ResponsModelUser> register = apiService.register(nama, email, nohp, alamat, username, password);
        register.enqueue(new Callback<ResponsModelUser>() {
            @Override
            public void onResponse(@NonNull Call<ResponsModelUser> call, @NonNull Response<ResponsModelUser> response) {
                loading.dismiss();
                String kode = response.body().getKode();
                String message = response.body().getPesan();
                if (kode.equals("1")) {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    Intent register = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(register);
                } else {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponsModelUser> call, @NonNull Throwable t) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Gagal Menghubungkan Internet !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
