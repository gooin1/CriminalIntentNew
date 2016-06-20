package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by gooin on 2016/6/15.
 */
public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleFiled;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        获取extra数据取得Crime对象
        UUID crimeId =(UUID) getActivity().getIntent().
        getSerializableExtra(CrimeActivity.EXTRA_CRAME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleFiled = (EditText) v.findViewById(R.id.crime_title);
        mTitleFiled.setText(mCrime.getTitle());

        mTitleFiled.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // This space intentionally left blank

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton=(Button) v.findViewById(R.id.crime_date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE,yy-MM-dd  HH:mm ");
        String t = simpleDateFormat.format(new Date());
        mDateButton.setText(t.toString());

        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());

        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
                mCrime.setSolved(isChecked);
            }
        });


        return v;

    }
}
