package com.example.darius.sharelocation.ui;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.darius.sharelocation.R;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 7/6/16.
 */
public class FriendsFragment extends Fragment {
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Bind(R.id.quickbadge) QuickContactBadge mBadge;


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Bitmap mThumbnail =
                        loadContactPhotoThumbnail(getContactList().get(0).get("PHOTO"));
                Log.d(TAG, "onRequestPermissionsResult: " + mThumbnail);

                mBadge.setImageBitmap(mThumbnail);
            }
        }
    }

    private Bitmap loadContactPhotoThumbnail(String photoData) {
        AssetFileDescriptor afd = null;

        try {
            Uri thumbUri;
            thumbUri = Uri.parse(photoData);
            afd = getActivity().getContentResolver().
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        ButterKnife.bind(this, view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            Bitmap mThumbnail =
                    loadContactPhotoThumbnail(getContactList().get(0).get("PHOTO"));
            mBadge.setImageBitmap(mThumbnail);
        }

        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public ArrayList<HashMap<String, String>> getContactList() {
        ArrayList<HashMap<String, String>> contactList = new ArrayList<>();
        contactList.clear();
        getActivity();
        Cursor phones = null;

        try {
            phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
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
}
