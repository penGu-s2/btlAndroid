package com.example.bai9_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.bai9_sqlite.model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btAdd,btGet,btUpdate,btDelete,btAll;
    private EditText txtId,txtName,txtMark;
    private RadioButton male,female;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private SQLiteStudentOpenHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        db=new SQLiteStudentOpenHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =txtName.getText().toString();
                boolean g=false;
                if (male.isChecked()){
                    g=true;
                }
                double m=Double.parseDouble(txtMark.getText().toString());
                Student s=new Student(name,g,m);
                db.addStudent(s);
            }
        });
        btAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> list=db.getAll();
                adapter.setStudents(list);
                recyclerView.setAdapter(adapter);
            }
        });
    btGet.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                int id = Integer.parseInt(txtId.getText().toString());
                Student s=db.getStudentByID(id);
                txtName.setText(s.getName());
                txtMark.setText(s.getMark()+"");
                if(s.isGender()){
                    male.setChecked(true);
                }else{
                    female.setChecked(false);
                }
                btAdd.setEnabled(false);
                btUpdate.setEnabled(true);
                btDelete.setEnabled(true);
                txtId.setEnabled(false);
            }
            catch (Exception e){}
            }
    });
    btDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                int id=Integer.parseInt(txtId.getText().toString());
                db.deleteStudent(id);
                btAdd.setEnabled(true);
                btUpdate.setEnabled(false);
                btDelete.setEnabled(false);
                txtId.setEnabled(true);
            }catch (Exception e){}
        }
    });
    btUpdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                int id=Integer.parseInt(txtId.getText().toString());
                String name =txtName.getText().toString();
                boolean g=false;
                if (male.isChecked()){
                    g=true;
                }
                double m=Double.parseDouble(txtMark.getText().toString());
                Student s=new Student(id,name,g,m);
                db.updateStudent(s);
                btAdd.setEnabled(true);
                btUpdate.setEnabled(false);
                btDelete.setEnabled(false);
                txtId.setEnabled(true);
            }catch (Exception e){}
        }
    });
    }

    private void initView() {
        btAdd=findViewById(R.id.btAdd);
        btGet=findViewById(R.id.btGet);
        btUpdate=findViewById(R.id.btUpdate);
        btDelete=findViewById(R.id.btDelete);
        btAll=findViewById(R.id.btAll);
        txtId=findViewById(R.id.stID);
        txtName=findViewById(R.id.stName);
        txtMark=findViewById(R.id.stMark);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        btUpdate.setEnabled(false);
        btDelete.setEnabled(false);
        recyclerView=findViewById(R.id.recyclerView);

    }
    /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item=menu.findItem(R.id.mSearch);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Student> list=sqLiteHelper.getStudentByName(newText);
                adapter.setStudents(list);
                recyclerView.setAdapter(adapter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/

}