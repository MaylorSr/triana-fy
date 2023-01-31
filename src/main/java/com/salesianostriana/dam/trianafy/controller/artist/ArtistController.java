package com.salesianostriana.dam.trianafy.controller.artist;

import com.salesianostriana.dam.trianafy.dtos.song.SongResponseArtistDTO;
import com.salesianostriana.dam.trianafy.model.Artist;
import com.salesianostriana.dam.trianafy.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;


    @GetMapping("/")
    public List<Artist> findAll() {
        return artistService.findAll();
    }


    @GetMapping("/{id}")
    public Artist findById(@PathVariable Long id) {
        return artistService.findById(id);
    }


    @PostMapping("/")
    public ResponseEntity<SongResponseArtistDTO> newArtist(@Valid @RequestBody SongResponseArtistDTO songResponseArtistDTO) {
        Artist created = artistService.add(songResponseArtistDTO);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(SongResponseArtistDTO.of(created));

    }

    @PutMapping("/{id}")
    public SongResponseArtistDTO editArtist(@Valid @RequestBody SongResponseArtistDTO songResponseArtistDTO, @PathVariable Long id) {
        Artist edited = artistService.edit(songResponseArtistDTO, id);
        return SongResponseArtistDTO.of(edited);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable Long id) {
        artistService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
