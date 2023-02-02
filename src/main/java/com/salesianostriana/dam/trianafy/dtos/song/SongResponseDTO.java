package com.salesianostriana.dam.trianafy.dtos.song;

import com.salesianostriana.dam.trianafy.model.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongResponseDTO {

    private Long id;
    private String title;
    private String artist;
    private String album;
    private String year;

    public static SongResponseDTO of(Song song) {
        return SongResponseDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .artist(song.getArtist() == null ? " " : song.getArtist().getName())
                .album(song.getAlbum())
                .year(song.getYear())
                .build();
    }

}
