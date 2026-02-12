package com.mymovie.api.controller;

import com.mymovie.api.dto.request.StreamingRequest;
import com.mymovie.api.dto.response.StreamingResponse;
import com.mymovie.api.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/streamings")
public class StreamingController {

    private final StreamingService streamingService;

    @PostMapping
    public ResponseEntity<StreamingResponse> create(@RequestBody StreamingRequest dto) {
        StreamingResponse response = streamingService.create(dto);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAll() {
        return ResponseEntity.ok(streamingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id) {
        StreamingResponse response = streamingService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StreamingResponse> update(
            @PathVariable Long id,
            @RequestBody StreamingRequest dto
    ) {
        StreamingResponse response = streamingService.updateById(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        streamingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
