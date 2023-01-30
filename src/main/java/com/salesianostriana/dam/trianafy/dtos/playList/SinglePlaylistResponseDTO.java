package com.salesianostriana.dam.trianafy.dtos.playList;

import com.salesianostriana.dam.trianafy.dtos.song.SongResponseDTO;
import com.salesianostriana.dam.trianafy.model.Playlist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SinglePlaylistResponseDTO {

    private Long id;
    private String name;
    private String description;
    private List<SongResponseDTO> songs;

    public static SinglePlaylistResponseDTO of(Playlist playlist) {
        return SinglePlaylistResponseDTO.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .description(playlist.getDescription())
                .songs(playlist.getSongs().stream().map(SongResponseDTO::of).toList())
                .build();
    }


}
