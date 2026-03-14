package com.example.p42_abc.author;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.p42_abc.R;
import com.example.p42_abc.author.viewModel.AuthorSharedViewModel;

public class AuthorDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_author_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textName = view.findViewById(R.id.textViewDetailAuthorName);
        Button btnDelete = view.findViewById(R.id.buttonDeleteAuthor);

        // On utilise bien requireActivity() pour récupérer le MÊME ViewModel que la liste
        AuthorSharedViewModel model = new ViewModelProvider(requireActivity()).get(AuthorSharedViewModel.class);

        // On regarde ce qu'il y a dans "getSelected()"
        model.getSelected().observe(getViewLifecycleOwner(), author -> {
            if (author != null) {
                // Dès qu'on trouve l'auteur, on met son nom dans le TextView
                textName.setText(author.getName());
            }
        });

        //boutton supprimer
        btnDelete.setOnClickListener(v -> {
            // On gérera la suppression avec l'API plus tard
        });

        Button btnBack = view.findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(v -> {
            // Cette commande simule l'appui sur la touche "Retour" du téléphone
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }
}