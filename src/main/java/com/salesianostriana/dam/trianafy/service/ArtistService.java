package com.salesianostriana.dam.trianafy.service;


import com.salesianostriana.dam.trianafy.dtos.song.SongResponseArtistDTO;
import com.salesianostriana.dam.trianafy.exception.GlobalEntityListNotFoundException;
import com.salesianostriana.dam.trianafy.exception.GlobalEntityNotFounException;
import com.salesianostriana.dam.trianafy.model.Artist;
import com.salesianostriana.dam.trianafy.repos.ArtistRepository;
import com.salesianostriana.dam.trianafy.repos.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;
    private final SongRepository songRepository;

    public Artist add(SongResponseArtistDTO songResponseArtistDTO) {
        return repository.save(
                Artist.builder()
                        .name(songResponseArtistDTO.getArtist())
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


    public Artist edit(SongResponseArtistDTO songResponseArtistDTO, Long id) {
        String message = "The artist with id %d was not found";


        return repository.findById(id)
                .map(old -> {
                    old.setName(songResponseArtistDTO.getArtist());
                    return repository.save(old);
                })
                .orElseThrow(() -> new GlobalEntityNotFounException(message, id));


    }

    public void delete(Artist artist) {
        repository.delete(artist);
    }

    public void deleteById(Long id) {
        String message = "The artist with id %d was not found";
        repository.findById(id).map(old -> {
            songRepository.findAll()
                    .stream()
                    .filter(song -> song.getArtist().equals(old))
                    .forEach(song -> {
                        song.setArtist(null);
                        songRepository.save(song);
                    });
            repository.deleteById(old.getId());
            return true;
        }).orElseThrow(() -> new GlobalEntityNotFounException(message, id));


    }

}
