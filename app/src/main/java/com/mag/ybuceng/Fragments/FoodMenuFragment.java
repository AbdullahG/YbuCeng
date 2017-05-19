package com.mag.ybuceng.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mag.ybuceng.R;
import com.mag.ybuceng.http.FoodListGetter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoodMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FoodMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodMenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static List<TextView> foodTextViews = new ArrayList<>();
    private static List<String> foodList = new ArrayList<>();
    public static boolean wait = true;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FoodMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodMenuFragment newInstance(String param1, String param2) {
        FoodMenuFragment fragment = new FoodMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initViews();
    }

    @Override
    public void onResume() {
        loadFoodList();
        super.onResume();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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

    public void initViews() {
        foodTextViews = new ArrayList<>();
        foodTextViews.add((TextView) getView().findViewById(R.id.food1));
        foodTextViews.add((TextView) getView().findViewById(R.id.food2));
        foodTextViews.add((TextView) getView().findViewById(R.id.food3));
        foodTextViews.add((TextView) getView().findViewById(R.id.food4));
    }

    public void loadFoodList() {
        if (foodList == null || foodList.size() == 0) {
            Thread thread = new Thread(new FoodListGetter());
            thread.start();
        } else {
            wait = false;
        }
        while (wait) ; // will be hooked from foodListGetter
        foodList = FoodListGetter.getFoodList();
        wait = true;
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < foodList.size(); i++) {
                    foodTextViews.get(i).setText(foodList.get(i));
                }
            }
        });
    }
}
