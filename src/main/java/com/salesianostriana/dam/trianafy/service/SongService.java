package com.salesianostriana.dam.trianafy.service;


import com.salesianostriana.dam.trianafy.dtos.song.SingleSongResponseDTO;
import com.salesianostriana.dam.trianafy.dtos.song.SongRequestDTO;
import com.salesianostriana.dam.trianafy.dtos.song.SongResponseDTO;
import com.salesianostriana.dam.trianafy.exception.GlobalEntityListNotFoundException;
import com.salesianostriana.dam.trianafy.exception.GlobalEntityNotFounException;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.repos.ArtistRepository;
import com.salesianostriana.dam.trianafy.repos.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository repository;
    private final ArtistRepository artistRepository;

    public SongResponseDTO add(SongRequestDTO songRequestDTO) {
        String message = "The artist with id %d was not found";

        return artistRepository.findById(songRequestDTO.getArtistId())
                .map(oldArtist -> {
                    Song newSong = Song.builder()
                            .title(songRequestDTO.getTitle())
                            .album(songRequestDTO.getAlbum())
                            .year(songRequestDTO.getYear())
                            .artist(oldArtist)
                            .build();
                    repository.save(newSong);
                    return SongResponseDTO.of(newSong);
                })
                .orElseThrow(() -> new GlobalEntityNotFounException(message, songRequestDTO.getArtistId()));
    }

    public Song findById(Long id) {
        String message = "The song with id %d was not found";
        return repository.findById(id).orElseThrow(() -> new GlobalEntityNotFounException(message, id));
    }

    public List<SingleSongResponseDTO> findAll() {

        String message = "The list of song was not found";

        List<Song> songList = repository.findAll();

        if (songList.isEmpty())
            throw new GlobalEntityListNotFoundException(message);

        return songList
                .stream()
                .map(SingleSongResponseDTO::of)
                .toList();
    }


    public SongResponseDTO edit(Long id, SongRequestDTO songRequestDTO) {
        String message = "The song with id %d was not found";
        String messageArt = "The artist with id %d was not found";
        return repository.findById(id)
                .map(old -> {
                    old.setTitle(songRequestDTO.getTitle());
                    old.setYear(songRequestDTO.getYear());
                    old.setAlbum(songRequestDTO.getAlbum());
                    old.setArtist(
                            artistRepository.findById(songRequestDTO.getArtistId())
                                    .orElseThrow(() -> new GlobalEntityNotFounException(messageArt, songRequestDTO.getArtistId()))
                    );
                    repository.save(old);
                    return SongResponseDTO.of(old);
                })
                .orElseThrow(() -> new GlobalEntityNotFounException(message, id));

    }

    public void delete(Song song) {
        repository.delete(song);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void deleteSong(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }


}
