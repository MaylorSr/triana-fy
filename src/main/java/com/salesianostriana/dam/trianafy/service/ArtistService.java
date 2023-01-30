package com.salesianostriana.dam.trianafy.service;


import com.salesianostriana.dam.trianafy.exception.GlobalEntityListNotFoundException;
import com.salesianostriana.dam.trianafy.exception.GlobalEntityNotFounException;
import com.salesianostriana.dam.trianafy.model.Artist;
import com.salesianostriana.dam.trianafy.repos.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;


    public Artist add(Artist artist) {
        return repository.save(
                Artist.builder()
                        .name(artist.getName())
                        .build());
    }

    public Artist findById(Long id) {
        String message = "The artist with id %d was not found";

        return repository.findById(id).orElseThrow(() -> new GlobalEntityNotFounException(message, id));
    }

    public List<Artist> findAll() {
        String message = "The list was not found";

        List<Artist> artistList = repository.findAll();

        if (artistList.isEmpty())
            throw new GlobalEntityListNotFoundException(message);

        return artistList;
    }

    public boolean artistExists(String artistName) {
        return repository.existsByNameIgnoreCase(artistName);
    }


    public Artist edit(Artist artist) {
        return repository.save(artist);
    }

    public void delete(Artist artist) {
        repository.delete(artist);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
