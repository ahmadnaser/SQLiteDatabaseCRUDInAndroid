package com.ahmadnaser.employeedbapp.sqliteAdapters;

/***
 *    Application Name : MessageBox 
 *    Author : Vimal Rughani
 *    Website : http://pulse7.net
 *    For more details visit http://pulse7.net/android/sqlite-database-android/
 */


import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.CursorAdapter;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ahmadnaser.employeedbapp.R;

import java.io.BufferedInputStream;
public class MyCursorAdapter extends CursorAdapter {
    public MyCursorAdapter(Context context, Cursor cursor)
    {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_crow, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView Ename = (TextView) view.findViewById(R.id.Ename);
        TextView Designation = (TextView) view.findViewById(R.id.Designation);
        TextView Salary = (TextView) view.findViewById(R.id.Salary);
        // Extract properties from cursor
        String ename = cursor.getString(cursor.getColumnIndexOrThrow("ename"));
        String designation = cursor.getString(cursor.getColumnIndexOrThrow("designation"));
        int salary = cursor.getInt(cursor.getColumnIndexOrThrow("salary"));

        ImageView imgView =(ImageView)view.findViewById(R.id.ImageView01);


      imgView.setImageResource(R.drawable.ic_launcher);





        // Populate fields with extracted properties
        Ename.setText(ename);
        Designation.setText(designation);
        Salary.setText(String.valueOf(salary));
    }



}