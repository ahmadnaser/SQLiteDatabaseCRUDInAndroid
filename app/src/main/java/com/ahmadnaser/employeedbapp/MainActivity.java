package com.ahmadnaser.employeedbapp;


        import android.app.ActionBar;
        import android.os.Bundle;
        import android.app.Activity;
        import android.content.ContentValues;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.SimpleCursorAdapter;
        import android.widget.Toast;
        import android.widget.AdapterView.OnItemClickListener;

        import com.ahmadnaser.employeedbapp.domain.Employee;
        import com.ahmadnaser.employeedbapp.sqliteAdapters.MyCursorAdapter;

public class MainActivity extends Activity implements OnClickListener {

    // Primitive Variables
    String selected_ID = "";

    // Widget GUI Declare
    EditText txtEname, txtDesig, txtSalary;
    Button btnAddEmployee, btnUpdate, btnDelete;
    ListView lvEmployees;

    // DB Objects
    DBHelper helper;
    SQLiteDatabase db;

    // Adapter Object
    SimpleCursorAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the action bar
        ActionBar actionBar = getActionBar();

        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Init DB Objects
        helper = new DBHelper(this);

        // Widget GUI Init
        txtEname = (EditText) findViewById(R.id.txtEname);
        txtDesig = (EditText) findViewById(R.id.txtDesig);
        txtSalary = (EditText) findViewById(R.id.txtSalary);
        lvEmployees = (ListView) findViewById(R.id.lvEmployees);

        btnAddEmployee = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        // Attached Listener
        btnAddEmployee.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        lvEmployees.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v,
                                    int position, long id) {

                String name, desig, salary;

                // Display Selected Row of Listview into EditText widget

                Cursor row = (Cursor) adapter.getItemAtPosition(position);
                selected_ID = row.getString(0);
                name = row.getString(1);
                desig = row.getString(2);
                salary = row.getString(3);

                txtEname.setText(name);
                txtDesig.setText(desig);
                txtSalary.setText(salary);
            }
        });

        // Fetch Data from database
        fetchData();
    }

    @Override
    public void onClick(View v) {

        // Perform CRUD Operation

        if (v == btnAddEmployee) {

            // Add Record with help of ContentValues and DBHelper class object
            ContentValues values = new ContentValues();
            values.put(Employee.C_ENAME, txtEname.getText().toString());
            values.put(Employee.C_DESIGNATION, txtDesig.getText().toString());
            values.put(Employee.C_SALARY, txtSalary.getText().toString());

            // Call insert method of SQLiteDatabase Class and close after
            // performing task
            db = helper.getWritableDatabase();
            db.insert(DBHelper.TABLE_EMPLOYEE, null, values);
            db.close();

            clearFields();
            Toast.makeText(this, "Employee Added Successfully",
                    Toast.LENGTH_LONG).show();

            // Fetch Data from database and display into listview
            fetchData();

        }
        if (v == btnUpdate) {

            // Update Record with help of ContentValues and DBHelper class
            // object

            ContentValues values = new ContentValues();
            values.put(Employee.C_ENAME, txtEname.getText().toString());
            values.put(Employee.C_DESIGNATION, txtDesig.getText().toString());
            values.put(Employee.C_SALARY, txtSalary.getText().toString());

            // Call update method of SQLiteDatabase Class and close after
            // performing task
            db = helper.getWritableDatabase();
            db.update(DBHelper.TABLE_EMPLOYEE, values, Employee.C_ID + "=?",
                    new String[] { selected_ID });
            db.close();

            // Fetch Data from database and display into listview
            fetchData();
            Toast.makeText(this, "Record Updated Successfully",
                    Toast.LENGTH_LONG).show();
            clearFields();

        }
        if (v == btnDelete) {

            // Call delete method of SQLiteDatabase Class to delete record and
            // close after performing task
            db = helper.getWritableDatabase();
            db.delete(DBHelper.TABLE_EMPLOYEE, Employee.C_ID + "=?",
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
        txtEname.setText("");
        txtDesig.setText("");
        txtSalary.setText("");
    }

    // Fetch Fresh data from database and display into listview
    private void fetchData() {
        db = helper.getReadableDatabase();
        Cursor c = db.query(DBHelper.TABLE_EMPLOYEE, null, null, null, null, null, null);
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
        MyCursorAdapter myAdapter = new MyCursorAdapter(this, c);
// Attach cursor adapter to the ListView
        lvEmployees.setAdapter(myAdapter);


    }


    @Override
    protected void onPause() {
        super.onPause();

        this.db.close();
    }
}