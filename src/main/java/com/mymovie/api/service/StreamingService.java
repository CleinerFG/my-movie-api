package com.mymovie.api.service;

import com.mymovie.api.dto.request.StreamingRequest;
import com.mymovie.api.dto.response.StreamingResponse;
import com.mymovie.api.entity.Streaming;
import com.mymovie.api.infra.exception.ResourceNotFoundException;
import com.mymovie.api.mapper.StreamingMapper;
import com.mymovie.api.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository streamingRepository;
    private final StreamingMapper streamingMapper;

    public StreamingResponse create(StreamingRequest dto) {
        var streaming = streamingMapper.toEntity(dto);
        var savedStreaming = streamingRepository.save(streaming);

        return streamingMapper.toResponseDTO(savedStreaming);
    }

    public List<StreamingResponse> findAll() {
        var streamings = streamingRepository.findAll();

        return streamings.stream()
                .map(streamingMapper::toResponseDTO)
                .toList();
    }

    public StreamingResponse findById(Long id) {
        Optional<Streaming> optStreaming = streamingRepository.findById(id);

        return optStreaming
                .map(streamingMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("streamingNotFound"));
    }

    public StreamingResponse updateById(Long id, StreamingRequest dto) {
        Optional<Streaming> optStreaming = streamingRepository.findById(id);

        return optStreaming.map(streaming->{
            streamingMapper.patchEntityFromDTO(dto, streaming);
            var savedStreaming = streamingRepository.save(streaming);

            return streamingMapper.toResponseDTO(savedStreaming);
        }).orElseThrow(()-> new ResourceNotFoundException("streamingNotFound"));
    }

    public void deleteById(Long id) {
        if (!streamingRepository.existsById(id)){
            throw  new ResourceNotFoundException("streamingNotFound");
        }

        streamingRepository.deleteById(id);
    }
}
