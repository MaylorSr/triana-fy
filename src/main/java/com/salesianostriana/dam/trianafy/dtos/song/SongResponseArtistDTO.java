package com.salesianostriana.dam.trianafy.dtos.song;

import com.salesianostriana.dam.trianafy.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongResponseArtistDTO {

    private Long id;
    private String artist;

    public static SongResponseArtistDTO of(Artist artist) {
        return SongResponseArtistDTO.builder()
                .artist(artist.getName())
                .id(artist.getId())
                .build();
    }
}
