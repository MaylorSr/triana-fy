package com.salesianostriana.dam.trianafy.dtos.playList;

import com.salesianostriana.dam.trianafy.model.Playlist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaylistResponseDTO {

    private Long id;
    private String name;
    private String description;

    public static CreatePlaylistResponseDTO of(Playlist playlist) {
        return CreatePlaylistResponseDTO.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .description(playlist.getDescription())
                .build();
    }
}
