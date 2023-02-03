package com.salesianostriana.dam.trianafy.dtos.song;

import com.salesianostriana.dam.trianafy.model.Artist;
import com.salesianostriana.dam.trianafy.validation.annotation.UniqueArtist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongResponseArtistDTO {

    private Long id;
    @UniqueArtist()
    @NotBlank(message = "{SongResponseArtistDTOArtist}")
    private String artist;

    public static SongResponseArtistDTO of(Artist artist) {
        return SongResponseArtistDTO.builder()
                .artist(artist.getName())
                .id(artist.getId())
                .build();
    }
}
