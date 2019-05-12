package com.example.contactsphoneapplication.vIew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.contactsphoneapplication.AdapterContact;
import com.example.contactsphoneapplication.R;
import com.example.contactsphoneapplication.beanContact.Contactbean;
import com.example.contactsphoneapplication.viewModelContatct.ViewmodelContact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public  static final int RequestPermissionCode  = 1 ;
    ViewmodelContact viewmodelContact;
    AdapterContact adapterContact;
    RecyclerView recyclerView;
    ArrayList<Contactbean>arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        EnableCOntactpermission();
        viewmodelContact = ViewModelProviders.of(this).get(ViewmodelContact.class);
        viewmodelContact.GetContatctArraylist(MainActivity.this).observe(this, new Observer<ArrayList<Contactbean>>() {
            @Override
            public void onChanged(ArrayList<Contactbean> contactbeans) {
                arrayList = new ArrayList<>();
                arrayList = contactbeans;
                adapterContact = new AdapterContact(contactbeans,MainActivity.this);
                adapterContact.notifyDataSetChanged();
                recyclerView.setAdapter(adapterContact);

              //  Toast.makeText(MainActivity.this,contactbeans.size()+"", Toast.LENGTH_LONG).show();


            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case RequestPermissionCode:
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity.this,"Permission Granted ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
    public void EnableCOntactpermission()
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CONTACTS))
        {
            Toast.makeText(MainActivity.this,"permsion is ok ",Toast.LENGTH_SHORT).show();
        }
        else
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},RequestPermissionCode);
        }
    }
}
