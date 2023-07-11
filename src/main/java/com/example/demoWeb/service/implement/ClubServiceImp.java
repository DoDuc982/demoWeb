package com.example.demoWeb.service.implement;

import com.example.demoWeb.dto.ClubDto;
import com.example.demoWeb.mapper.ClubMapper;
import com.example.demoWeb.model.Club;
import com.example.demoWeb.model.Event;
import com.example.demoWeb.repository.ClubRepository;
import com.example.demoWeb.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demoWeb.mapper.ClubMapper.mapToClub;
import static com.example.demoWeb.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImp implements ClubService {
    private final ClubRepository clubRepository;
    @Autowired
    public ClubServiceImp(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
    }

    @Override
    public void delete(long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

}
