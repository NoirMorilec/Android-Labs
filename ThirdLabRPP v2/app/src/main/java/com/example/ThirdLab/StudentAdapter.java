package com.example.ThirdLab;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class StudentAdapter extends ArrayAdapter<App.Student> {

    private LayoutInflater inflater;
    private int layout;
    private List<App.Student> students;

    public StudentAdapter(Context context, int resource, List<App.Student> students) {
        super(context, resource, students);
        this.students = students;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView idView = view.findViewById(R.id.id);
        TextView nameView = view.findViewById(R.id.name);
        TextView dateView = view.findViewById(R.id.date);
        App.Student student = students.get(position);
        String id = 1 + student.id+". ";
        idView.setText(id);
        nameView.setText(student.second_name);
        dateView.setText(student.date);
        return view;
    }

}