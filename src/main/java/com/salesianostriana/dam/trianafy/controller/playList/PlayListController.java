package com.salesianostriana.dam.trianafy.controller.playList;

import com.salesianostriana.dam.trianafy.dtos.playList.AllPlaylistsResponseDTO;
import com.salesianostriana.dam.trianafy.dtos.playList.CreatePlaylistRequestDTO;
import com.salesianostriana.dam.trianafy.dtos.playList.SinglePlaylistResponseDTO;
import com.salesianostriana.dam.trianafy.dtos.song.SongResponseArtistDTO;
import com.salesianostriana.dam.trianafy.model.Artist;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/list")
public class PlayListController {
    private final PlaylistService playlistService;


    @GetMapping("/")
    public List<AllPlaylistsResponseDTO> findAll() {
        return playlistService.findAllList();
    }

    @GetMapping("/{id}")
    public SinglePlaylistResponseDTO findById(@PathVariable Long id) {
        return playlistService.findByIdList(id);
    }

    @PostMapping("/")
    public ResponseEntity<CreatePlaylistRequestDTO> createPlayList(@Valid @RequestBody CreatePlaylistRequestDTO createPlayList) {

        CreatePlaylistRequestDTO created = playlistService.createPlayList(createPlayList);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(created);
    }

    @PutMapping("/{id}")
    public AllPlaylistsResponseDTO editPlayList(@PathVariable Long id, @RequestBody CreatePlaylistRequestDTO createPlaylistRequestDTO) {
        return playlistService.editPlayList(id, createPlaylistRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        playlistService.deletePlayList(id);
        return ResponseEntity.noContent().build();
    }

    /********************************************************************************************************************************************/

    @GetMapping("/{id}/song")
    public SinglePlaylistResponseDTO findByIdListSong(@PathVariable Long id) {
        return playlistService.findByIdListSong(id);
    }

    @PostMapping("/{id1}/song/{id2}")
    public SinglePlaylistResponseDTO addSongToPlayList(@PathVariable Long id1, @PathVariable Long id2) {
        return playlistService.addSongToPlayList(id1, id2);
    }


    @GetMapping("/{id}/song/{id2}")
    public Song findByIdListIdSong(@PathVariable Long id, @PathVariable Long id2) {
        return playlistService.findByIdListIdSong(id, id2);
    }


}
