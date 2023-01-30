package com.salesianostriana.dam.trianafy.controller.artist;

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
    public ResponseEntity<Artist> newArtist(@Valid @RequestBody Artist artist) {
        Artist created = artistService.add(artist);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(created);

    }
}
