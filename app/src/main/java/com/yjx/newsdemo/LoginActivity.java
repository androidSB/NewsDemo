package com.yjx.newsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private String str_user,str_pass;
    private EditText username,password;
    private Button btn_login,btn_regester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    class LoginOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            str_user=username.getText().toString().trim();
            if(str_user==null||str_user.length()<=0){
                username.requestFocus();//把输入焦点放到控件上
                username.setError("账号不能为空");
                return;
            }
            str_pass=password.getText().toString().trim();
            if(str_pass==null||str_pass.length()<=0){
                password.requestFocus();
                password.setError("密码不能为空");
            }
        }
    }
}
