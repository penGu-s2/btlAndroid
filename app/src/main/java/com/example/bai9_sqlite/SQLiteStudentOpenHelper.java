package com.example.bai9_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bai9_sqlite.model.Student;

import java.util.ArrayList;
import java.util.List;

public class SQLiteStudentOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="StudentDB.db";
    private static final int DATABASE_VERSION=1;
    public SQLiteStudentOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE Student(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "gender BOOLEAN," +
                "mark REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //add a student
    public long addStudent(Student s){
        ContentValues v=new ContentValues();
        v.put("name",s.getName());
        v.put("gender",s.isGender());
        v.put("mark",s.getMark());
        SQLiteDatabase st=getWritableDatabase();
        return st.insert("student",null,v);
    }
    //select*from
    public List<Student> getAll(){
        List<Student> list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("student",null,
                null,null,null,null,null);
        while(rs.moveToNext()){
            int id=rs.getInt(0);
            String name=rs.getString(1);
            boolean g=rs.getInt(2)==1;
            double mark=rs.getDouble(3);
            list.add(new Student(id,name,g,mark));
        }
        return list;
    }
    //select from  student where id
    public Student getStudentByID(int id){
        String sql="SELECT * FROM student WHERE id=?";
        String[] whereArgs={Integer.toString(id)};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.rawQuery(sql,whereArgs);
        if((rs!=null)&&(rs.moveToNext())){
            String name=rs.getString(1);
            boolean g=rs.getInt(2)==1;
            double mark=rs.getDouble(3);
            return new Student(id,name,g,mark);
        }
        return null;
    }
    //delete
    public int deleteStudent(int id){
        String whereClause="id=?";
        String[] whereAgrs={Integer.toString(id)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete("student",whereClause,whereAgrs);
    }
    //update
    public int updateStudent(Student s){
        ContentValues v=new ContentValues();
        v.put("name",s.getName());
        v.put("gender",s.isGender());
        v.put("mark",s.getMark());
        String whereClause="id=?";
        String[] whereAgrs={Integer.toString(s.getId())};
        SQLiteDatabase st=getWritableDatabase();
        return st.update("student",v,whereClause,whereAgrs);

    }
    //where like %key%
    public List<Student> searchByName(String key){
        List<Student> list=new ArrayList<>();
        String whereClause="name like ?";
        String[] whereArgs={"%"+key+"%"};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("student",null,whereClause,whereArgs,null,null,null);
        while(rs.moveToNext()){
            int id=rs.getInt(0);
            String name=rs.getString(1);
            boolean g=rs.getInt(2)==1;
            double mark=rs.getDouble(3);
            list.add(new Student(id,name,g,mark));
        }
        return list;
    }
}
