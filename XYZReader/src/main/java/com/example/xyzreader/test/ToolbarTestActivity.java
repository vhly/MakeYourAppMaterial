package com.example.xyzreader.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.xyzreader.R;

import java.util.LinkedList;
import java.util.List;

public class ToolbarTestActivity extends AppCompatActivity {

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_test);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.test_recycler_view);
        if (recyclerView != null) {

            List<String> data = new LinkedList<String>();

            for (int i = 0; i < 50; i++) {
                data.add("Java - " + i);
            }
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            adapter = new MyAdapter(this, data);

            recyclerView.setAdapter(adapter);

        }

    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(android.R.id.text1);

        }

        public void setTitle(String title) {

            if (title != null) {
                if (textView != null) {
                    textView.setText(title);
                }
            }

        }
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private Context mContext;

        private List<String> items;

        private LayoutInflater inflater;

        public MyAdapter(Context context, List<String> items) {
            this.mContext = context;
            this.items = items;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (inflater == null) {
                inflater = LayoutInflater.from(mContext);
            }

            View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);


            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            String title = items.get(position);

            holder.setTitle(title);

        }

        @Override
        public int getItemCount() {
            int ret = 0;
            if (items != null) {
                ret = items.size();
            }
            return ret;
        }
    }

}
