package mx.com.cmp.finances.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.xdty.preference.colorpicker.ColorPickerDialog;
import org.xdty.preference.colorpicker.ColorPickerSwatch;

import mx.com.cmp.finances.R;

public class ExpensesFragment extends Fragment {

    private FirebaseDatabase mFirebaseInstance;
    DatabaseReference mDatabaseReference;
    private int mSelectedColor;

    public ExpensesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expenses, container, false);

        final int[] mColors = getResources().getIntArray(R.array.default_rainbow);

        Button boton = (Button) view.findViewById(R.id.button2);
        boton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
                         mColors,
                         mSelectedColor,
                         5, // Number of columns
                         ColorPickerDialog.SIZE_SMALL,
                         true // True or False to enable or disable the serpentine effect
                         //0, // stroke width
                         //Color.BLACK // stroke color
                 );
                 dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {

                     @Override
                     public void onColorSelected(int color) {
                         mSelectedColor = color;
                     }

                 });
                 dialog.show(getActivity().getFragmentManager(), "color_dialog_test");

             }
         });


/*
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseInstance.setPersistenceEnabled(true);

        mDatabaseReference = mFirebaseInstance.getReference("categories");

        for(int x  = 0; x < 5; x++) {
            Category category = new Category();
            category.setUid(mDatabaseReference.push().getKey());
            category.setName("Expense # " + (x+1));
            category.setType(1);
            mDatabaseReference.child("expenses").child(category.getUid()).setValue(category);
        }

        for(int x  = 0; x < 5; x++) {
            Category category = new Category();
            category.setUid(mDatabaseReference.push().getKey());
            category.setName("Income # " + (x+1));
            category.setType(2);
            mDatabaseReference.child("incomes").child(category.getUid()).setValue(category);
        }

*/

        return view;
    }


}
