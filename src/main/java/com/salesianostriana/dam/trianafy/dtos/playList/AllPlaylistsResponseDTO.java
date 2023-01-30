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
public class AllPlaylistsResponseDTO {

    private Long id;
    private String name;
    private long numberOfSongs;

    public static AllPlaylistsResponseDTO of(Playlist playlist) {
        return AllPlaylistsResponseDTO.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .numberOfSongs(playlist.getSongs().size())
                .build();
    }
}
