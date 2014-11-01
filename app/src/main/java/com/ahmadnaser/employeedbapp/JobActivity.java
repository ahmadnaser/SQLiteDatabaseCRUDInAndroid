package com.ahmadnaser.employeedbapp;


import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.ahmadnaser.employeedbapp.domain.Employee;
import com.ahmadnaser.employeedbapp.domain.Job;
import com.ahmadnaser.employeedbapp.sqliteAdapters.JobsCursorAdapter;
import com.ahmadnaser.employeedbapp.sqliteAdapters.MyCursorAdapter;

public class JobActivity extends Activity implements OnClickListener {

    // Primitive Variables
    String selected_ID = "";

    // Widget GUI Declare
    EditText txtEPname;
    Button btnAddJob, btnUpdateJob, btnDeleteJob;
    ListView lvJobs;

    // DB Objects
    DBHelper helper;
    SQLiteDatabase db;

    // Adapter Object
    SimpleCursorAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        // get the action bar
        ActionBar actionBar = getActionBar();

        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);

       // Init DB Objects
        helper = new DBHelper(this);

        // Widget GUI Init
        txtEPname = (EditText) findViewById(R.id.txtEPname);

        lvJobs = (ListView) findViewById(R.id.lvJobs);

        btnAddJob = (Button) findViewById(R.id.btnAddJob);
        btnUpdateJob = (Button) findViewById(R.id.btnUpdateJob);
        btnDeleteJob = (Button) findViewById(R.id.btnDeleteJob);

        // Attached Listener
       btnAddJob.setOnClickListener(this);
       btnUpdateJob.setOnClickListener(this);
       btnDeleteJob.setOnClickListener(this);


        lvJobs.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v,
                                    int position, long id) {

                String name;

                // Display Selected Row of Listview into EditText widget

                Cursor row = (Cursor) adapter.getItemAtPosition(position);
                selected_ID = row.getString(0);
                name = row.getString(1);


                txtEPname.setText(name);

            }
        });



        // Fetch Data from database
        fetchData();
    }

    @Override
    public void onClick(View v) {

        // Perform CRUD Operation

        if (v == btnAddJob) {

            // Add Record with help of ContentValues and DBHelper class object
            ContentValues values = new ContentValues();
            values.put(Job.C_NAME, txtEPname.getText().toString());


            // Call insert method of SQLiteDatabase Class and close after
            // performing task
            db = helper.getWritableDatabase();
            db.insert(DBHelper.TABLE_JOB, null, values);
            db.close();

            clearFields();
            Toast.makeText(this, "Job Added Successfully",
                    Toast.LENGTH_LONG).show();

            // Fetch Data from database and display into listview
            fetchData();

        }
        if (v == btnUpdateJob) {

            // Update Record with help of ContentValues and DBHelper class
            // object

            ContentValues values = new ContentValues();
            values.put(Job.C_NAME, txtEPname.getText().toString());

            // Call update method of SQLiteDatabase Class and close after
            // performing task
            db = helper.getWritableDatabase();
            db.update(DBHelper.TABLE_JOB, values, Job.C_ID + "=?",
                    new String[] { selected_ID });
            db.close();

            // Fetch Data from database and display into listview
            fetchData();
            Toast.makeText(this, "Record Updated Successfully",
                    Toast.LENGTH_LONG).show();
            clearFields();

        }
        if (v == btnDeleteJob) {

            // Call delete method of SQLiteDatabase Class to delete record and
            // close after performing task
            db = helper.getWritableDatabase();
            db.delete(DBHelper.TABLE_JOB, Job.C_ID + "=?",
                    new String[] { selected_ID });
            db.close();

            // Fetch Data from database and display into listview
            fetchData();
            Toast.makeText(this, "Record Deleted Successfully",
                    Toast.LENGTH_LONG).show();
            clearFields();

        }

    }

    // Clear Fields
    private void clearFields() {
        txtEPname.setText("");

    }

    // Fetch Fresh data from database and display into listview
    private void fetchData() {
        db = helper.getReadableDatabase();
        Cursor c = db.query(DBHelper.TABLE_JOB, null, null, null, null, null, null);
      /*
      *
        Cursor c = db.query(
        TABLE_NAME,  // The table to query
        null,                                 // The columns to return
        null,                                 // The columns for the WHERE clause
        null,                                // The values for the WHERE clause
        null,                               // don't group the rows
        null,                               // don't filter by row groups
        null                               // don't sort
        );
if (c.moveToFirst()) {
    String test= c.getString(0);
    // or String test= c.getString(c.getColumnIndex("column_name"));
}
        c.close();
      * */


        //method 1
/*        String[] from=   new String[] { DBHelper.C_ENAME, DBHelper.C_SALARY,
                DBHelper.C_DESIGNATION };
        int[] to= new int[] { R.id.lblEname, R.id.lblSalary, R.id.lblDesignation };

       adapter=   new SimpleCursorAdapter(this,
               R.layout.row, c, from , to, 0);
        lvEmployees.setAdapter(adapter);*/


//method 2


// Find ListView to populate

// Setup cursor adapter using cursor from last step
        JobsCursorAdapter myAdapter = new JobsCursorAdapter(this, c);
// Attach cursor adapter to the ListView
        lvJobs.setAdapter(myAdapter);


    }


    @Override
    protected void onPause() {
        super.onPause();

        this.db.close();
    }
}