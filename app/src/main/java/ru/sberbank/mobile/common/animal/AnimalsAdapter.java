package ru.sberbank.mobile.common.animal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.sberbank.backgroundtaskssample.R;

/**
 * @author QuickNick
 */
public class AnimalsAdapter extends BaseAdapter {

    private final List<Animal> mAnimals;

    public AnimalsAdapter() {
        mAnimals = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mAnimals.size();
    }

    @Override
    public Animal getItem(int position) {
        return mAnimals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

//    private View newView(ViewGroup parent) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.)
//    }
}
