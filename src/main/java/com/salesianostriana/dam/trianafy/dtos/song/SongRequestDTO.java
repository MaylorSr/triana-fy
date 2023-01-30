package com.salesianostriana.dam.trianafy.dtos.song;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongRequestDTO {
    private String title;
    private Long artistId;
    private String album;
    private String year;
}
