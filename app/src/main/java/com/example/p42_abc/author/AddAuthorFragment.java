package com.example.p42_abc.author;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.p42_abc.R;
import com.example.p42_abc.author.viewModel.AuthorSharedViewModel;

public class AddAuthorFragment extends Fragment {

    private AuthorSharedViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_author, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // On récupère le même ViewModel partagé que l'activité
        viewModel = new ViewModelProvider(requireActivity()).get(AuthorSharedViewModel.class);

        EditText editTextName = view.findViewById(R.id.editTextAuthorName);
        Button btnSave = view.findViewById(R.id.buttonSaveAuthor);

        btnSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            if (!name.isEmpty()) {
                // On demande au ViewModel d'ajouter l'auteur
                viewModel.addAuthor(name);

                // On ferme le formulaire pour revenir à la liste
                getParentFragmentManager().popBackStack();
            }
        });
    }
}