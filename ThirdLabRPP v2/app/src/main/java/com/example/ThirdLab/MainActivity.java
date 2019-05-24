package com.example.ThirdLab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button first;
    Button second;
    Button third;

    private String[] names = new String[]{"Хабенский Константин Юрьевич",
            "Эрнст Константин Львович", "Серебренников Кирилл Сергеевич", "Табаков Олег Павлович",
            "Познер Владимир Владимирович"};
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.StudentDatabase sb = App.getInstance().getStudentDatabase();
        final App.StudentDao sd = sb.studentDao();
        final List<App.Student> list = sd.getAll();

        if (!list.isEmpty()) {
            for (App.Student e : list) {
                sd.delete(e);
            }
        }
        list.clear();

        if (list.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                String[] separatedFio = names[new Random().nextInt(3)].split(" ");
                App.Student student = new App.Student(i,separatedFio[0],separatedFio[1],separatedFio[2], new Date().toString());
                list.add(student);
                sd.insert(student);
                Log.v("ADD", student.name);
            }
        }

        first = (Button) findViewById(R.id.first_button);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        InformationActivity.class);
                startActivity(intent);
            }
        });

        second = (Button) findViewById(R.id.second_button);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] separatedFio = names[new Random().nextInt(3)].split(" ");
                App.Student student = new App.Student(list.size(),separatedFio[0],separatedFio[1],separatedFio[2], new Date().toString());
                list.add(student);
                sd.insert(student);
                Toast toast = Toast.makeText(getApplicationContext(),
                        student.name, Toast.LENGTH_SHORT);
                toast.show();
                Log.v("ADD", student.name + " " + student.id);
            }
        });

        third = (Button) findViewById(R.id.third_button);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.Student student = list.get(list.size() - 1);
                student.second_name = "Иван";
                sd.update(student);

            }
        });
    }
}
