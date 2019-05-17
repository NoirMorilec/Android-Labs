package com.example.ThirdLab;

import android.app.Application;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Update;
import android.arch.persistence.room.Database;

import java.util.List;

public class App extends Application {

    public static App instance;
    private StudentDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(),
                StudentDatabase.class, "students").allowMainThreadQueries().build();
    }

    public static App getInstance() {
        return instance;
    }

    public StudentDatabase getStudentDatabase() {
        return database;
    }

    @Entity
    public static class Student {
        @PrimaryKey
        public long id;
        public String name;
        public String date;
    }

    @Dao
    public interface StudentDao {
        @Query("SELECT * FROM student")
        List<Student> getAll();

        @Query("SELECT * FROM student WHERE id = :id")
        Student getById(long id);

        @Insert
        void insert(Student student);

        @Update
        void update(Student student);

        @Delete
        void delete(Student student);
    }

    @Database(entities = {Student.class}, version = 1)
    public abstract static class StudentDatabase extends RoomDatabase {
        public abstract StudentDao studentDao();
    }
}
