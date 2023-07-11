package com.example.demoWeb.mapper;

import com.example.demoWeb.dto.ClubDto;
import com.example.demoWeb.model.Club;

import java.util.stream.Collectors;

import static com.example.demoWeb.mapper.EventMapper.mapToEventDto;

public class ClubMapper {
    public static Club mapToClub(ClubDto club) {
        return Club.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
    }


    public static ClubDto mapToClubDto(Club club){
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .events(club.getEvents().stream().map(EventMapper::mapToEventDto).collect(Collectors.toList()))
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
    }
}
