package com.salesianostriana.dam.trianafy.controller.playList;

import com.salesianostriana.dam.trianafy.dtos.playList.AllPlaylistsResponseDTO;
import com.salesianostriana.dam.trianafy.dtos.playList.SinglePlaylistResponseDTO;
import com.salesianostriana.dam.trianafy.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
