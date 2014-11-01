package com.ahmadnaser.employeedbapp.sqliteAdapters;

/***
 *    Application Name : MessageBox 
 *    Author : Vimal Rughani
 *    Website : http://pulse7.net
 *    For more details visit http://pulse7.net/android/sqlite-database-android/
 */


import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadnaser.employeedbapp.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JobsCursorAdapter extends CursorAdapter {
    public JobsCursorAdapter(Context context, Cursor cursor)
    {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_job_crow, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView EPname = (TextView) view.findViewById(R.id.EPname);

        // Extract properties from cursor
        String epname = cursor.getString(cursor.getColumnIndexOrThrow("name"));


   ImageView imgView =(ImageView)view.findViewById(R.id.ImageView01);


   imgView.setImageResource(R.drawable.ic_launcher);





        // Populate fields with extracted properties
        EPname.setText(epname);

    }



    public static Bitmap getImageFromWeb(String imglink)
    {

        Bitmap bmpImage=null;
try {
    URL imgURL = new URL(imglink);
    URLConnection conn = imgURL.openConnection();
    conn.connect();

    InputStream is = conn.getInputStream();
    BufferedInputStream bis = new BufferedInputStream(is);

    bmpImage = BitmapFactory.decodeStream(bis);
    bis.close();
    is.close();
}
catch (Exception ex){}

        return bmpImage;

    }


    private Drawable ImageOperations(Context ctx, String url, String saveFilename) {
        try {
            InputStream is = (InputStream) this.fetch(url);
            Drawable d = Drawable.createFromStream(is, "src");
            return d;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object fetch(String address) throws MalformedURLException,IOException {
        URL url = new URL(address);
        Object content = url.getContent();
        return content;
    }
}