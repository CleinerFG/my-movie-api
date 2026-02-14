package com.mymovie.api.mapper;

import com.mymovie.api.dto.request.StreamingRequest;
import com.mymovie.api.dto.response.StreamingResponse;
import com.mymovie.api.entity.Streaming;
import org.springframework.stereotype.Component;

@Component
public class StreamingMapper {
    public Streaming toEntity(StreamingRequest dto) {
        return Streaming.builder()
                .name(dto.name())
                .build();
    }

    public StreamingResponse toResponseDTO(Streaming entity) {
        return StreamingResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
