package com.salesianostriana.dam.trianafy.dtos.playList;

import com.salesianostriana.dam.trianafy.model.Playlist;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.validation.annotation.UniquePlayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaylistRequestDTO {

    @Id
    @GeneratedValue
    private Long id;
    @UniquePlayList()
    @NotBlank(message = "{CreatePlaylistRequestDTOPlayListName.notempty}")
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
