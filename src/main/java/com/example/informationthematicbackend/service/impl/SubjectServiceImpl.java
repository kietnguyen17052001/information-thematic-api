package com.example.informationthematicbackend.service.impl;

import com.example.informationthematicbackend.model.dto.common.SubjectDTO;
import com.example.informationthematicbackend.model.entity.SubjectEntity;
import com.example.informationthematicbackend.repository.dsl.SubjectDslRepository;
import com.example.informationthematicbackend.request.subject.GetListSubjectRequest;
import com.example.informationthematicbackend.response.subject.GetListSubjectResponse;
import com.example.informationthematicbackend.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectDslRepository subjectDslRepository;

    @Override
    public GetListSubjectResponse getListSubject(GetListSubjectRequest request) {
        List<SubjectEntity> subjects = subjectDslRepository.listSubject(request);
        return GetListSubjectResponse.builder()
                .setSuccess(true)
                .setItems(subjects.stream()
                        .map(s -> SubjectDTO.builder()
                                .setSubjectId(s.getSubjectId())
                                .setSubject(s.getSubject())
                                .setCode(s.getCode())
                                .setDescription(s.getDescription())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}