package com.example.akbar.smartcity.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.akbar.smartcity.R;
import com.example.akbar.smartcity.api.BaseApiService;
import com.example.akbar.smartcity.api.Server;
import com.example.akbar.smartcity.model.ModelUser;
import com.example.akbar.smartcity.model.ResponsModelUser;
import com.example.akbar.smartcity.util.SessionManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_username)
    EditText edUsername;

    @BindView(R.id.input_password)
    EditText edPassword;

    ProgressDialog loading;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        loading = new ProgressDialog(LoginActivity.this);
        sessionManager = new SessionManager(LoginActivity.this);

        if (sessionManager.Login()){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();

        }

    }

    @OnClick(R.id.btn_login)
    public void login() {
        funLogin();
    }

    private void funLogin (){
        loading.setMessage("Loading ...");
        loading.show();
        BaseApiService apiService = Server.getUrl().create(BaseApiService.class);
        Call<ResponsModelUser> login = apiService.login(edUsername.getText().toString(), edPassword.getText().toString());
        login.enqueue(new Callback<ResponsModelUser>() {
            @Override
            public void onResponse(retrofit2.Call<ResponsModelUser> call, Response<ResponsModelUser> response) {

                loading.dismiss();
                Log.d(TAG,"Massage : " + response.toString());
                ResponsModelUser res = response.body();
                List<ModelUser> user = res.getResult();
                if (res.getKode().equals("1")){
                    sessionManager.storeLogin(user.get(0).getNama_lengkap()
                            ,user.get(0).getEmail());
                    Toast.makeText(getApplicationContext(),res.getPesan(),
                            Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(getApplicationContext(),HomeActivity.class);
                    login.putExtra("nama_lengkap", user.get(0).getNama_lengkap());
                    login.putExtra("email", user.get(0).getEmail());
                    startActivity(login);

                } else {
                    Toast.makeText(getApplicationContext(), res.getPesan(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponsModelUser> call, Throwable t) {
                loading.dismiss();
                Log.e("onFailure : ", "Message : "+String.valueOf(t.getMessage()));
                Toast.makeText(getApplicationContext(), "Gagagl Menghubungkan Internet!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
