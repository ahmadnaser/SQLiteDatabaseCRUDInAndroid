package com.ahmadnaser.employeedbapp;

/***
 *    Application Name : MessageBox 
 *    Author : Vimal Rughani
 *    Website : http://pulse7.net
 *    For more details visit http://pulse7.net/android/sqlite-database-android/
 */


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ahmadnaser.employeedbapp.domain.Employee;
import com.ahmadnaser.employeedbapp.domain.Job;

public class DBHelper extends SQLiteOpenHelper {

	// Static Final Variable database meta information

	static final String DATABASE = "empapp.db";
	static final int VERSION = 1;
	static final String TABLE_EMPLOYEE = "emp";
    static final String TABLE_JOB = "job";


	// Override constructor
	public DBHelper(Context context) {
		super(context, DATABASE, null, VERSION);

	}

	// Override onCreate method
	@Override
	public void onCreate(SQLiteDatabase db) {

		// Create Employee table with following fields
		// _ID, ENAME, DESIGNATION and SALARY
	db.execSQL("CREATE TABLE " + TABLE_EMPLOYEE + " ( " + Employee.C_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Employee.C_ENAME + " TEXT, "
                + Employee.C_DESIGNATION + " TEXT, " + Employee.C_SALARY + " TEXT )");


        // Create Job table with following fields
        // _ID, NAME
        db.execSQL("CREATE TABLE " + TABLE_JOB + " ( " + Job.C_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Job.C_NAME + " TEXT )");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// Drop old version table
		db.execSQL("Drop table " + TABLE_EMPLOYEE);

        // Drop old version table
        db.execSQL("Drop table " + TABLE_JOB);

		// Create New Version table
		onCreate(db);
	}

}
