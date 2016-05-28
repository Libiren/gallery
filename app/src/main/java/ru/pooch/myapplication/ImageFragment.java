package ru.pooch.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;


public class ImageFragment extends Fragment {

    public ArrayList<File> files;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image, container, false);
        GridView gridView1 = (GridView) view.findViewById(R.id.gridview);
        String internalStorage = Environment.getExternalStorageDirectory().getAbsolutePath();
        files = getListFiles(new File(internalStorage));
        gridView1.setAdapter(new GridAdapter(files));
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getContext(), ImageActivity.class).putExtra("jpg", files.get(position).toString()));
            }
        });



        return view;

    }
    class GridAdapter extends BaseAdapter {
        ArrayList<File> list;

        public GridAdapter(ArrayList<File> files) {
            this.list = files;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater(null).inflate(R.layout.single_grid1, parent, false);
            ImageView iv = (ImageView) convertView.findViewById(R.id.imageView1);
            iv.setImageURI(Uri.parse(getItem(position).toString()));

            return convertView;
        }
    }

    private ArrayList<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                if (file.getName().endsWith(".jpg")) {
                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }


}
