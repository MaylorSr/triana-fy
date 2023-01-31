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
public class SingleSongResponseDTO {

    private Long id;
    private String title;
    private String album;
    private String year;
    private SongResponseArtistDTO artist;

    public static SingleSongResponseDTO of(Song song) {
        return SingleSongResponseDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .album(song.getAlbum())
                .year(song.getYear())
                .artist(song.getArtist() == null ? null : SongResponseArtistDTO.of(song.getArtist()))
                .build();
    }
}
