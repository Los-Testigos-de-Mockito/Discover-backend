package com.example.discoverbackend.services;

import com.example.discoverbackend.dtos.OpinionRequest;
import com.example.discoverbackend.entities.Opinion;

import java.util.List;

public interface OpinionService {
    public Opinion createOpinion(OpinionRequest opinion);
}
