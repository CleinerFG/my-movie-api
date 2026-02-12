package com.mymovie.api.mapper;

import com.mymovie.api.dto.request.StreamingRequest;
import com.mymovie.api.dto.response.StreamingResponse;
import com.mymovie.api.entity.Streaming;
import org.springframework.stereotype.Component;

@Component
public class StreamingMapper {
    public Streaming toEntity(StreamingRequest dto) {
        var streaming = new Streaming();
        updateEntityFromDTO(dto, streaming);
        return streaming;
    }

    public StreamingResponse toResponseDTO(Streaming entity) {
        return StreamingResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public void updateEntityFromDTO(StreamingRequest dto, Streaming entity) {
        entity.setName(dto.name());
    }

    public void patchEntityFromDTO(StreamingRequest dto, Streaming entity) {
        if (dto.name() != null) entity.setName(dto.name());
    }
}
