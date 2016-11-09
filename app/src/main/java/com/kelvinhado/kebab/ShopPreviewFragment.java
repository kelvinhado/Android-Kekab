package com.kelvinhado.kebab;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.kelvinhado.kebab.database.ShopRepository;
import com.kelvinhado.kebab.model.Shop;


public class ShopPreviewFragment extends Fragment {
    private static final String ARG_SHOP_ID = "paramId";

    private String mParamId;
    private OnShopPreviewFragmentInteractionListener mListener;

    public ShopPreviewFragment() {
        // Required empty public constructor
    }

    public static ShopPreviewFragment newInstance(String shopId) {
        ShopPreviewFragment fragment = new ShopPreviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOP_ID, shopId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamId = getArguments().getString(ARG_SHOP_ID);
            synchronizeShopOnce(mParamId);
        }
        else{
            Log.d("Err", "couldn't instancitate the fragment.. no Id provided");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // remove floating button
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setBackgroundColor(Color.RED);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_preview, container, false);
    }

    public void synchronizeShopOnce(String shopId){
        if(shopId != null) {
            Log.d("DEBUG", shopId);
            ShopRepository.shopRef.child(shopId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                        Shop shop = dataSnapshot.getValue(Shop.class);

                        onShopSyncronized(shop);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }

    public void onShopSyncronized(Shop shop){
        TextView tvTest = (TextView) getView().findViewById(R.id.tvTest);
        tvTest.setText(shop.getAddress());

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnShopPreviewFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnShopPreviewFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnShopPreviewFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
