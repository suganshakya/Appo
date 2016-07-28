package com.example.sugan.myappo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {

    Button editUpdateButton, editDeleteButton;

    EditText editIdET;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        editIdET = (EditText) view.findViewById(R.id.editing_id_editText);

        editUpdateButton = (Button) view.findViewById(R.id.edit_update_button);
        editDeleteButton = (Button) view.findViewById(R.id.edit_delete_button);

        editUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUpdateData();
            }
        });

        editDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDeleteData();
            }
        });
        return view;
    }

    private void editUpdateData() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        EditDataDialogFragment editDataDialogFragment = EditDataDialogFragment.newInstance(
                Integer.parseInt(editIdET.getText().toString())
        );
        editDataDialogFragment.show(ft, "dialog");
    }

    private void editDeleteData() {
        AppointmentDatabaseHelper appointAppointmentDatabaseHelper = AppointmentDatabaseHelper.getInstance(getContext());
        appointAppointmentDatabaseHelper.deleteAppointment(Integer.parseInt(editIdET.getText().toString()));
    }
}
