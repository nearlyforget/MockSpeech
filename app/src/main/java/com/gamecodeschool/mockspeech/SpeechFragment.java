package com.gamecodeschool.mockspeech;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpeechFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SpeechFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpeechFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SPEECH_ID = "speech";
    private static final String ARG_SPEECHER_ID = "speecher";
    private SpeechItem speech;
    private Speecher speecher;
    private MediaPlayerInterface parrent;


    private OnFragmentInteractionListener mListener;

    public SpeechFragment() {
        // Required empty public constructor
    }


    public static SpeechFragment newInstance() {
        SpeechFragment fragment = new SpeechFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get 2 arguments
        if (getArguments() != null) {
            speech = (SpeechItem) getArguments().getSerializable(ARG_SPEECH_ID);
            speecher = (Speecher) getArguments().getSerializable(ARG_SPEECHER_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View speechView;
        speechView = inflater.inflate(R.layout.fragment_speech, container, false);
        //Reference for each widgets
        TextView speechTitle = (TextView) speechView.findViewById(R.id.text_Title);
        TextView speechPlace = (TextView) speechView.findViewById(R.id.textPlace);
        TextView speechDate = (TextView) speechView.findViewById(R.id.textDate);
        TextView speechText = (TextView) speechView.findViewById(R.id.textSpeech);
        ImageView playImage = (ImageView) speechView.findViewById(R.id.image_playbutton);
        final ImageView pauseImage = (ImageView) speechView.findViewById(R.id.image_pausebutton);

        //Set speech content to each view.
        speechTitle.setText(speech.getSpeechName());
        speechDate.setText(speech.getSpeechDate());
        speechPlace.setText(speech.getSpeechPlace());
        speechText.setText(speech.getSpeech());

        //Get the parrent Activity
        parrent = (MediaPlayerInterface) parrent;

        //if play button is clicked, pass the audio file name to the parent activity
        //start the media if it's not already started, or resume the media if it is started before
        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parrent.playAudio(speech.getSpeechUri());
            }
        });

        //if pause button is clicked, pass the audio file name to the parent activity, and pause the player
        pauseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parrent.pauseAudio(speech.getSpeechUri());
            }
        });

        return speechView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
