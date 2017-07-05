package com.gamecodeschool.mockspeech;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toolbar;

import static com.gamecodeschool.mockspeech.R.drawable.icon_politics;

public class MainActivity extends AppCompatActivity {

    TableLayout tl;
    TableRow tr;
    ImageView speechIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tl = (TableLayout) findViewById(R.id.BookShelf);
        tr = (TableRow) findViewById(R.id.firstRow);

        speechIcon = new ImageView(this);
        speechIcon.setImageDrawable(getResources().getDrawable(R.drawable.icon_politics));
        tr.addView(speechIcon,200,350);
    }
}
