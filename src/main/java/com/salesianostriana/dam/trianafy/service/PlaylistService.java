package com.salesianostriana.dam.trianafy.service;


import com.salesianostriana.dam.trianafy.dtos.playList.AllPlaylistsResponseDTO;
import com.salesianostriana.dam.trianafy.dtos.playList.CreatePlaylistRequestDTO;
import com.salesianostriana.dam.trianafy.dtos.playList.SinglePlaylistResponseDTO;
import com.salesianostriana.dam.trianafy.exception.GlobalEntityListNotFoundException;
import com.salesianostriana.dam.trianafy.exception.GlobalEntityNotFounException;
import com.salesianostriana.dam.trianafy.model.Playlist;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.repos.PlaylistRepository;
import com.salesianostriana.dam.trianafy.repos.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository repository;
    private final SongRepository songRepository;

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

    public SinglePlaylistResponseDTO findByIdList(Long id) {
        String message = "The PlayList with id %d was not found";
        Optional<Playlist> playlist = repository.findById(id);

        return playlist.map(SinglePlaylistResponseDTO::of).orElseThrow(() -> new GlobalEntityNotFounException(message, id));

    }

    public boolean playListExist(String playListName) {
        return repository.existsByNameIgnoreCase(playListName);
    }


    public CreatePlaylistRequestDTO createPlayList(CreatePlaylistRequestDTO createPlaylistRequestDTO) {
        Playlist newPlayList = repository.save(Playlist.builder()
                .name(createPlaylistRequestDTO.getName())
                .description(createPlaylistRequestDTO.getDescription())
                .songs(createPlaylistRequestDTO.toPlaylist().getSongs())
                .build());
        createPlaylistRequestDTO.setId(newPlayList.getId());
        return createPlaylistRequestDTO;
    }

    public AllPlaylistsResponseDTO editPlayList(Long id, CreatePlaylistRequestDTO createPlaylistRequestDTO) {

        String message = "The playList was not found";
        return repository.findById(id).map(old -> {
            old.setName(createPlaylistRequestDTO.getName());
            old.setDescription(createPlaylistRequestDTO.getDescription());
            repository.save(old);

            return AllPlaylistsResponseDTO.of(old);
        }).orElseThrow(() -> new GlobalEntityNotFounException(message, id));

    }

    public void deletePlayList(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

    /******************************************************************************************************************/
    public SinglePlaylistResponseDTO findByIdListSong(Long id) {
        String message = "The List of PlayList with id %d not exists";
        return repository.findById(id)
                .map(SinglePlaylistResponseDTO::of)
                .orElseThrow(() -> new GlobalEntityNotFounException(message, id));
    }

    public SinglePlaylistResponseDTO addSongToPlayList(Long id1, Long id2) {
        String message = "The playList not exists";
        return repository.findById(id1)
                .map(old -> {
                    old.getSongs().add(songRepository.findById(id2).orElseThrow(() -> new GlobalEntityNotFounException("The song not exists", id2)));
                    repository.save(old);
                    return SinglePlaylistResponseDTO.of(old);
                })
                .orElseThrow(() -> new GlobalEntityNotFounException(message, id1));
    }

    public Song findByIdListIdSong(Long id, Long id2) {
        return repository.findById(id).map(old -> {
            return songRepository.findById(id2).orElseThrow(() -> new GlobalEntityNotFounException("The song not exists", id2));
        }).orElseThrow(() -> new GlobalEntityNotFounException("The playList not exists", id));

    }

}
