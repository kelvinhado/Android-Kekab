package com.kelvinhado.kebab;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kelvinhado.kebab.model.Shop;
import com.kelvinhado.kebab.model.Shops;

/**
 * Created by kelvin on 03/11/2016.
 */

public class ShopListFragment extends ListFragment {

    OnShopSelectedListenner mListener = null;
    private Shops shops;                    // this will contains the list of object that we want to display
    private ShopListAdapter lvAdapter;      // => Go see the code for the adapter below


    public ShopListFragment() {
        // Required empty public constructor
    }
    public static ShopListFragment newInstance() {
        ShopListFragment myFragment = new ShopListFragment();
        Bundle args = new Bundle();
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shops = new Shops();
        lvAdapter = new ShopListAdapter(getActivity(), shops);
    }

    @Override
    public void onStart() {
        super.onStart();
        shops = MapsFragment.getDisplayedShops();
        populateListView(shops);
    }

    private void populateListView(Shops shops) {
        lvAdapter = new ShopListAdapter(getActivity(), shops);
        setListAdapter(lvAdapter);
    }


   /* To hundle interation, the activity must implements this interface */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnShopSelectedListenner) activity;
        } catch (ClassCastException e) {
            // Unchecked exception.
            throw new ClassCastException(activity.toString()
                    + " must implement onShopselectedListenner");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Send the shop to the host activity
        Shop shop = (Shop) getListView().getItemAtPosition(position);
        mListener.onShopSelectedFromList(shop.getId());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */

    public // Interface interne
    interface OnShopSelectedListenner {
        void onShopSelectedFromList(String shopId);
    }


}