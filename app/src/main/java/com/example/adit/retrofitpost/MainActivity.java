package com.example.adit.retrofitpost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adit.retrofitpost.http.APIService;
import com.example.adit.retrofitpost.http.APIUtils;
import com.example.adit.retrofitpost.http.model.Post;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity{

    @BindView(R2.id.et_title) EditText titleEt;
    @BindView(R2.id.et_body) EditText bodyEt;
    @BindView(R2.id.btn_submit) Button submitBtn;
    @BindView(R2.id.tv_response) TextView mResponseTv;

    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mAPIService = APIUtils.getAPIService();
    }

    @OnClick(R2.id.btn_submit)
    void clickBtn(){
        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
        String title = titleEt.getText().toString().trim();
        String body = bodyEt.getText().toString().trim();

        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)){
            sendPost(title, body);
        }
    }

    private void sendPost(String title, String body) {
        mAPIService.savePost(title, body, 1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    showResponse(response.body().toString());
                    Log.i("dodol", "onResponse: " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("dodol", "onFailure: unable to submit post to api" + t);
            }
        });
    }

    public void showResponse(String response){
        if(mResponseTv.getVisibility() == View.GONE){
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }

}

