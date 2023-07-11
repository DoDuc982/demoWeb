package com.example.demoWeb.service;

import com.example.demoWeb.dto.ClubDto;
import com.example.demoWeb.model.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();

    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(long clubId);

    void updateClub(ClubDto club);

    void delete(long clubId);
    List<ClubDto> searchClubs(String query);
}
