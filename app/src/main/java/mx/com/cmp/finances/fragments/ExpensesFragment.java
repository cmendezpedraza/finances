package mx.com.cmp.finances.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mx.com.cmp.finances.R;
import mx.com.cmp.finances.objects.Category;

public class ExpensesFragment extends Fragment {

    private FirebaseDatabase mFirebaseInstance;
    DatabaseReference mDatabaseReference;

    public ExpensesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        return inflater.inflate(R.layout.fragment_expenses, container, false);
    }

}
