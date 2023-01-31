package com.salesianostriana.dam.trianafy.controller.song;

import com.salesianostriana.dam.trianafy.dtos.song.SingleSongResponseDTO;
import com.salesianostriana.dam.trianafy.dtos.song.SongRequestDTO;
import com.salesianostriana.dam.trianafy.dtos.song.SongResponseDTO;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/song")
public class SongController {
    private final SongService songService;


    @GetMapping("/")
    public List<SingleSongResponseDTO> findAll() {
        return songService.findAll();
    }

    @GetMapping("/{id}")
    public Song findById(@PathVariable Long id) {
        return songService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<SongResponseDTO> addSong(@Valid @RequestBody SongRequestDTO songRequestDTO) {

        SongResponseDTO created = songService.add(songRequestDTO);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(created);

    }


    @PutMapping("/{id}")
    public SongResponseDTO editSong(@Valid @RequestBody SongRequestDTO songRequestDTO, @PathVariable Long id) {
        return songService.edit(id, songRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
