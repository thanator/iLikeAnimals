package ru.sberbank.mobile.common.animal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import ru.sberbank.backgroundtaskssample.R;

import static ru.sberbank.mobile.common.animal.VeryGlobalVariables.TYPE;
import static ru.sberbank.mobile.common.animal.VeryGlobalVariables.VICTIM;

/**
 * Created by Tan-DS on 6/10/2017.
 */

public class ChoosingWhatToDoАFragment extends DialogFragment {

    private View mView;
    private int mVictim;
    private RadioButton mRadio1, mRadio2;


    private AnimalsStorage mAnimalsStorage;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AnimalsStorageProvider provider = (AnimalsStorageProvider)getActivity().getApplication();
        mAnimalsStorage = provider.getAnimalsStorage();


        Bundle args = this.getArguments();
        mVictim = args.getInt(VICTIM);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        mView = inflater.inflate(R.layout.choosing_dialog, null);
        mRadio1 = (RadioButton) mView.findViewById(R.id.annihilate_animal);
        mRadio2 = (RadioButton) mView.findViewById(R.id.update_animal);

        builder.setView(mView)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mRadio1.isChecked()){
                            mAnimalsStorage.deleteAnimal(mVictim);
                        }else if (mRadio2.isChecked()){
                            startActivity(DoSomeThingWithAnimalActivity
                                    .newIntent(getContext())
                                    .putExtra(VICTIM, mVictim)
                                    .putExtra(TYPE, 1));
                        }
                    }
                })
                .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ChoosingWhatToDoАFragment.this.getDialog().cancel();
                    }
                });


        return builder.create();
    }
}
