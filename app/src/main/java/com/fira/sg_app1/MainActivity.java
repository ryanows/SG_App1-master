package com.fira.sg_app1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fira.sg_app1.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.Objects;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.til_nama)
    TextInputLayout til_nama;

    @BindView(R.id.et_umur)
    EditText et_umur;

    @BindView(R.id.spinner_gender)
    Spinner spinner_gender;

    @BindView(R.id.btn_process)
    Button btn_process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

    }


    @OnClick (R.id.btn_process)
    void validateData(){
        if (til_nama.getEditText() != null) {
            //jika nama dan umur tidak kosong
            if ( !til_nama.getEditText().getText().toString().equals("") && !et_umur.getText().toString().equals("")) {
                User user = new User();
                user.nama = til_nama.getEditText().getText().toString();
                user.umur = Integer.parseInt(et_umur.getText().toString());
                user.gender = spinner_gender.getSelectedItem().toString();

                processData(user);

            }else {

                Toast.makeText(this, "Data Tidak boleh Kosong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void processData(User user) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

}
