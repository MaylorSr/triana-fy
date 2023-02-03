package com.salesianostriana.dam.trianafy.dtos.song;

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
    @NotEmpty(message = "{postSongRequestDTOTittle.notempty}")
    private String title;
    private Long artistId;
    @NotEmpty(message = "{postSongRequesDTOAlbum.notempty}")
    private String album;
    @NotEmpty(message = "{The year was not empty}")
    private String year;
}
