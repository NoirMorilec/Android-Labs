package com.example.SecondLab;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class PageFragment extends Fragment {
    private ImageView iconImageView;
    private TextView nameTextView;
    private TextView helpTextView;
    private String title;
    private String help;
    private String icon;
    private int page;

    public static PageFragment newInstance(int page, String title, String help, String icon){
        PageFragment templatePageFragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        args.putString("help", help);
        args.putString("icon", icon);
        templatePageFragment.setArguments(args);
        return templatePageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_item, container, false);
        nameTextView = view.findViewById(R.id.pagerName);
        helpTextView = view.findViewById(R.id.help);
        iconImageView = view.findViewById(R.id.pageIcon);
        nameTextView.setText(title);
        helpTextView.setText(help);

        Picasso.get().load(getResources().getString(R.string.img_url)
                + icon).into(iconImageView);

        return view;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page", 0);
        title = getArguments().getString("title");
        help = getArguments().getString("help");
        icon = getArguments().getString("icon");
    }

}