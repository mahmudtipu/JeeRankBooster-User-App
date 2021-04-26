package com.jeerankbooster.iithandwrittennotes;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import com.jeerankbooster.iithandwrittennotes.Common.Common;
import com.jeerankbooster.iithandwrittennotes.Interface.ItemClickListener;
import com.jeerankbooster.iithandwrittennotes.Model.Category;
import com.jeerankbooster.iithandwrittennotes.ViewHolder.CategoryViewHolder;


public class CategoryFragment extends Fragment {

    View myFragment;

    RecyclerView listCategory;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Category,CategoryViewHolder> adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();;
    DatabaseReference categories;

    public static CategoryFragment newInstance()
    {
        CategoryFragment categoryFragment = new CategoryFragment();

        return categoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categories = database.getReference("Math");
        categories.keepSynced(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_category,container,false);

        listCategory = myFragment.findViewById(R.id.listCategoryId);
        listCategory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        listCategory.setLayoutManager(layoutManager);

        loadCategories();

        return myFragment;
    }

    private void loadCategories() {
        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
                Category.class,
                R.layout.category_layout,
                CategoryViewHolder.class,
                categories
        ) {
            @Override
            protected void populateViewHolder(final CategoryViewHolder viewHolder, final Category model, int position) {
                viewHolder.category_name.setText(model.getName());

                Picasso.with(getActivity()).load(R.drawable.math).networkPolicy(NetworkPolicy.OFFLINE).into(viewHolder.category_image, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Picasso.with(getActivity())
                                        .load(R.drawable.math)
                                        .into(viewHolder.category_image);
                            }
                        });

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        Common.CategoryIndex = model.getName();

                        if(Integer.parseInt(model.getAvailability())==0)
                        {
                            Intent startGame =  new Intent(getActivity(),Start.class);
                            Common.categoryId = adapter.getRef(position).getKey();
                            startActivity(startGame);
                        }
                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);
    }
}