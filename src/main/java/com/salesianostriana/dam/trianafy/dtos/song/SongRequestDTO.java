package com.salesianostriana.dam.trianafy.dtos.song;

import com.salesianostriana.dam.trianafy.validation.annotation.UniqueSong;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongRequestDTO {
    @UniqueSong()
    @NotEmpty(message = "{postSongRequestDTOTittle.notempty}")
    private String title;
    private Long artistId;
    @NotEmpty(message = "{postSongRequesDTOAlbum.notempty}")
    private String album;
    @NotEmpty(message = "{The year was not empty}")
    private String year;
}
