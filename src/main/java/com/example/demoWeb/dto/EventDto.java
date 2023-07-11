package com.example.demoWeb.dto;
import com.example.demoWeb.model.Club;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    @NotEmpty(message = "Type should not be empty")
    private String type;
    @NotEmpty(message = "Photo Url should not be empty")
    private String photoUrl;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Club club;

}
