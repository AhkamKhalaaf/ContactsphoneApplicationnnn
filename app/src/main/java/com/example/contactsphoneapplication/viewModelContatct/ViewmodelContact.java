package com.example.contactsphoneapplication.viewModelContatct;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.contactsphoneapplication.beanContact.Contactbean;

import java.util.ArrayList;

public class ViewmodelContact extends ViewModel {
    MutableLiveData<ArrayList<Contactbean>>mutableLiveData;
    Cursor cursor;
    ArrayList<Contactbean>storcontacts;
    Contactbean contactbean;


    public LiveData<ArrayList<Contactbean>>GetContatctArraylist(Context context)
    {
        storcontacts = new ArrayList<>();
        mutableLiveData = new MutableLiveData<>();
        cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,
                null,null);
        while (cursor.moveToNext())
        {
            contactbean = new Contactbean();
            contactbean.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            contactbean.setPhone( cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            storcontacts.add(contactbean);
        }
        cursor.close();
        mutableLiveData.setValue(storcontacts);
       return mutableLiveData;
    }
}
