package com.gamecodeschool.mockspeech;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    private VerticalAdapter verListAdapter;
    private ArrayList<ArrayList<SpeechItem>> groupList;
    SpeechDAO speechDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		/*
         * Calling Library & BookItem classes for create list of groups
		 *  groupbyArrayBookItem return back array of array of items
		 */
        speechDAO = new SpeechDAO(getApplicationContext());

        groupList = speechDAO.getSpeechGroupList();

        verListAdapter = new VerticalAdapter(this, R.layout.row, groupList);
        setListAdapter(verListAdapter);

        verListAdapter.notifyDataSetChanged();
    }

    /**
     * This class add a list of ArrayList to ListView that it include multi
     * items as bookItem.
     */
    private class VerticalAdapter extends ArrayAdapter<ArrayList<SpeechItem>> {

        private int resource;

        public VerticalAdapter(Context _context, int _ResourceId,
                               ArrayList<ArrayList<SpeechItem>> _items) {
            super(_context, _ResourceId, _items);
            this.resource = _ResourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView;

            if (convertView == null) {
                rowView = LayoutInflater.from(getContext()).inflate(resource,
                        null);
            } else {
                rowView = convertView;
            }

            HorizontalListView hListView = (HorizontalListView) rowView
                    .findViewById(R.id.subListview);
            HorizontalAdapter horListAdapter = new HorizontalAdapter(
                    getContext(), R.layout.item, getItem(position));
            hListView.setAdapter(horListAdapter);
            hListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    HorizontalListView listview = (HorizontalListView) parent;
                    SpeechItem speechItem = (SpeechItem) listview.getItemAtPosition(position);
                    Intent intent = new Intent(MainActivity.this, SpeechContent.class);

                    // Pass 2 params for SpeechContent Activity
                    intent.putExtra("speechId", speechItem.getSpeechId());
                    intent.putExtra("speecherId", (speechDAO.getSpeecherById(speechItem.getSpeechId()).getSpeecherId()));

                    //Start SpeechContent
                    startActivity(intent);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            return rowView;
        }
    }

    /*
     * This class add some items to Horizontal ListView this ListView include
     * several bookItem.
     */
    private class HorizontalAdapter extends ArrayAdapter<SpeechItem> {

        private int resource;

        public HorizontalAdapter(Context _context, int _textViewResourceId,
                                 ArrayList<SpeechItem> _items) {
            super(_context, _textViewResourceId, _items);
            this.resource = _textViewResourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View retval = LayoutInflater.from(getContext()).inflate(
                    this.resource, null);

            TextView topText = (TextView) retval.findViewById(R.id.title);
            TextView bottomText = (TextView) retval
                    .findViewById(R.id.author);

            topText.setText(speechDAO.getSpeecherById(getItem(position).getSpeecher()).getSpeecherName());
            bottomText.setText(getItem(position).getSpeechName());

            return retval;
        }
    }
}
