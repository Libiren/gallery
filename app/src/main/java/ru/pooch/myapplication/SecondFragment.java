package ru.pooch.myapplication;


import android.content.Intent;

import android.graphics.Bitmap;

import android.media.ThumbnailUtils;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.provider.MediaStore;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


import java.io.File;
import java.util.ArrayList;


public class SecondFragment extends Fragment {


    public GridView gridView;
    public ArrayList<File> files;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);

        String internalStorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();



        files = getListFiles(new File(internalStorage));
        gridView.setAdapter(new GridAdapter(files));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getContext(), VideoActivity.class).putExtra("mp4", files.get(position).toString()));
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
            convertView = getLayoutInflater(null).inflate(R.layout.single_grid, parent, false);
            ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
            Bitmap thumb = ThumbnailUtils.createVideoThumbnail(files.get(position).getAbsolutePath(), MediaStore.Video.Thumbnails.MINI_KIND);
            iv.setImageBitmap(thumb);
            return convertView;
        }
    }

    private ArrayList<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
//                inFiles.addAll(getListFiles(file));
            } else {
                if (file.getName().endsWith(".mp4")) {
                    inFiles.add(file);
                }
            }
        }
        if (inFiles.size() != 0) {
            return inFiles;

        } else return null;
    }


}
