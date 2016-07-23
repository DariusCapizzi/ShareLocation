package com.example.darius.sharelocation.services;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;

import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.ui.MainActivity;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Guest on 7/8/16.
 */
public class UserContactsService {

    public static final String TAG = "UserContactsService";

    public ArrayList<HashMap<String, String>> getContactList(Context context) {
        ArrayList<HashMap<String, String>> contactList = new ArrayList<>();
        contactList.clear();
        Cursor phones = null;

        try {
            phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            while (phones.moveToNext())
            {
                String _name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String _number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String _thumb = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI));

                HashMap<String , String> contactMap= new HashMap<String, String>();


                contactMap.put("NAME", _name);
                contactMap.put("NUMBER", _number);
                contactMap.put("PHOTO", _thumb);

                contactList.add(contactMap);
//                numbers.add("+" + _number);
                Log.d(TAG, _number + " " + _name + " " + _thumb);
            }
            phones.close();

        } catch ( Exception e ) {
            // TODO: handle exception
            Log.d(TAG, String.valueOf(e));
        }
        finally {
            if(phones != null){
                phones.close();
            }

        }

        return contactList;
    }

    public Bitmap loadContactPhotoThumbnail(Context context, String photoData) {
        AssetFileDescriptor afd;
        if(photoData == null){
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.aragorn);

        } else
        {

            try {
                Uri thumbUri;
                thumbUri = Uri.parse(photoData);
                afd = context.getContentResolver().
                        openAssetFileDescriptor(thumbUri, "r");
                FileDescriptor fileDescriptor = afd.getFileDescriptor();
                if (fileDescriptor != null) {
                    return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, null);
                }
            } catch (FileNotFoundException e){
                Log.d(TAG, "loadContactPhotoThumbnail: "+ e);
            }
            return null;

        }

    }
}
