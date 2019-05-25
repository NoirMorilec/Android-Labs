package com.example.ThirdLab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class InformationActivity extends AppCompatActivity {

    ListView informationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        informationList = findViewById(R.id.informationList);
        App.StudentDatabase sb = App.getInstance().getStudentDatabase();
        final App.StudentDao sd = sb.studentDao();
        final List<App.Student> list = sd.getAll();

        ListView listView = findViewById(R.id.informationList);
        StudentAdapter studentAdapter = new StudentAdapter(this, R.layout.item_for_list, list);
        listView.setAdapter(studentAdapter);
    }
}
