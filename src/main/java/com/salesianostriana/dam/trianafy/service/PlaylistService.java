package com.salesianostriana.dam.trianafy.service;


import com.salesianostriana.dam.trianafy.dtos.playList.AllPlaylistsResponseDTO;
import com.salesianostriana.dam.trianafy.dtos.playList.SinglePlaylistResponseDTO;
import com.salesianostriana.dam.trianafy.exception.GlobalEntityListNotFoundException;
import com.salesianostriana.dam.trianafy.exception.GlobalEntityNotFounException;
import com.salesianostriana.dam.trianafy.model.Playlist;
import com.salesianostriana.dam.trianafy.repos.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository repository;

    public Playlist add(Playlist playlist) {
        return repository.save(playlist);
    }

    public Optional<Playlist> findById(Long id) {
        return repository.findById(id);
    }

    public List<Playlist> findAll() {
        return repository.findAll();
    }

    public Playlist edit(Playlist playlist) {
        return repository.save(playlist);
    }

    public void delete(Playlist playlist) {
        repository.delete(playlist);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public List<AllPlaylistsResponseDTO> findAllList() {
        String message = "The list of playList was not found";
        List<Playlist> playlistList = repository.findAll();

        if (playlistList.isEmpty()) {
            throw new GlobalEntityListNotFoundException(message);
        }
        return playlistList.stream().map(AllPlaylistsResponseDTO::of).toList();
    }

    public Optional<SinglePlaylistResponseDTO> findByIdList(Long id) {
        String message = "The PlayList with id %d was not found";
        Optional<Playlist> playlist = repository.findById(id);

        if (playlist.isEmpty()) {
            new GlobalEntityNotFounException(message, id);
        }
        return playlist.map(SinglePlaylistResponseDTO::of);

    }


}
