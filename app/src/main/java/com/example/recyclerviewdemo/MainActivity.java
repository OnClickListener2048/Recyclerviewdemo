package com.example.recyclerviewdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String url = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_bt%2F0%2F13915280964%2F641&refer=http%3A%2F%2Finews.gtimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1632499497&t=95414b5e31506703dc00f6ae876bb314";
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainAdapter = new MainAdapter();
        recyclerView.setAdapter(mainAdapter);
        List<User> users = genernateData();
        mainAdapter.setList(users);


        mainAdapter.notifyDataSetChanged();
    }

    private List<User> genernateData() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User(url, "姓名" + (i + 1));
            users.add(user);
        }
        return users;
    }

    public void notifyDataSetChange(View view) {
        mainAdapter.notifyDataSetChanged();
    }

    public static class MainAdapter extends RecyclerView.Adapter<VH>{

        private List<User> list = Collections.emptyList();

        public void setList(List<User> users) {
            list = users;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.user, parent, false);
            return new VH(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            User user = list.get(position);
            Glide.with(holder.imageView.getContext()).setDefaultRequestOptions(new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)).load(user.imageUrl).into(holder.imageView);
            holder.textView.setText(user.name);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public static class VH extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public VH(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.name);

        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public static class User {

        private String imageUrl;
        private String name;

        public User(String imageUrl, String name) {
            this.imageUrl = imageUrl;
            this.name = name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
