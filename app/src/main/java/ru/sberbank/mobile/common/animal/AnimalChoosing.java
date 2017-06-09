package ru.sberbank.mobile.common.animal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.sberbank.backgroundtaskssample.R;

/**
 * Created by Tan-DS on 6/10/2017.
 */

public class AnimalChoosing extends DialogFragment {
    private TextView mTextView;
    private Button mButton;
    private EditText mEditText;
    private View mView;
    private int mCount;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle args = this.getArguments();
        mCount = args.getInt("count");

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        mView = inflater.inflate(R.layout.choosing_one_dialog, null);
        mTextView = (TextView) mView.findViewById(R.id.choosing_one_text);
        mEditText = (EditText) mView.findViewById(R.id.choosing_one_edit);

        mEditText.setHint("0 - " + mCount);
        mTextView.setText("Вам доступно " + mCount + " подопытных");

        builder.setView(mView);
        if (mCount == 0){
            Toast.makeText(getContext(), "Работать-то не с чем. Создайте сначала животных", Toast.LENGTH_SHORT).show();
            AnimalChoosing.this.dismiss();
        }
        builder.setPositiveButton(R.string.oky, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!mEditText.getText().toString().equals("")){
                    if(Integer.parseInt(mEditText.getText().toString()) > 0 && Integer.parseInt(mEditText.getText().toString()) <= mCount ){

                        DialogFragment newFragment = new ChoosingWhatToDo();
                        Bundle args = new Bundle();
                        args.putInt("victim",Integer.parseInt(mEditText.getText().toString()));
                        newFragment.setArguments(args);
                        newFragment.show(getFragmentManager(), "Choosing_v2");

                    }else{
                        Toast.makeText(getContext(), "Число должно быть между 0 и " + mCount, Toast.LENGTH_SHORT).show();
                        DialogFragment newFragment = new AnimalChoosing();
                        Bundle args = new Bundle();
                        args.putInt("count",mCount);
                        newFragment.setArguments(args);
                        newFragment.show(getFragmentManager(), "Choosing");
                    }
                }

            }
        })
        .setNegativeButton(R.string.nope, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AnimalChoosing.this.getDialog().cancel();
            }
        });




        return builder.create();
    }
}

