package com.example.ThirdLab;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
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
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import java.util.List;

public class App extends Application {

    public static App instance;
    private StudentDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(),
                StudentDatabase.class, "students")
                .addMigrations(StudentDatabase.newversion)
                .allowMainThreadQueries()
                .build();
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
        public String second_name;
        public String patronymic;
        public String date;

        public Student(long id, String second_name, String name , String patronymic, String date) {
            this.id = id;
            this.second_name = second_name;
            this.name = name;
            this.patronymic = patronymic;
            this.date = date;
        }

        public Student() {
        }
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

    @Database(entities = {Student.class}, version = 2)
    public abstract static class StudentDatabase extends RoomDatabase {
        public abstract StudentDao studentDao();
        public static final Migration newversion = new Migration(1,2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {

                database.execSQL("CREATE TABLE 'student_new' ('id' INTEGER NOT NULL, 'second_name' TEXT"
                        + ", 'name' TEXT, 'patronymic' TEXT, 'date' TEXT NOT NULL, PRIMARY KEY('id'))");
                database.execSQL("INSERT INTO student_new (id, name, second_name, patronymic, date) "+
                        " SELECT id, substr(credentials, 1, pos - 1), substr(credentials, pos + 1), '', date FROM"+
                        "(SELECT *, instr(credentials, ' ') AS pos FROM student)");
                database.execSQL("UPDATE student_new SET patronymic = substr(second_name, instr(second_name, ' ') + 1) ");
                database.execSQL("UPDATE student_new SET second_name = substr(second_name, 1, instr(second_name, ' '))");
                database.execSQL("DROP TABLE student");
                database.execSQL("ALTER TABLE student_new RENAME TO student");
            }
        };
    }
}
