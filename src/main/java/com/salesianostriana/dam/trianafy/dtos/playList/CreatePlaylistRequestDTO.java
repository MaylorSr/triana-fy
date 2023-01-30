package com.salesianostriana.dam.trianafy.dtos.playList;

import com.salesianostriana.dam.trianafy.model.Playlist;
import com.salesianostriana.dam.trianafy.model.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaylistRequestDTO {
    private String name;
    private String description;

    public Playlist toPlaylist() {
        return Playlist.builder()
                .songs(new ArrayList<Song>())
                .name(this.name)
                .description(this.description)
                .build();
    }
}
