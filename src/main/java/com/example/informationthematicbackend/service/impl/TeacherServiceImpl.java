package com.example.informationthematicbackend.service.impl;

import com.example.informationthematicbackend.common.constaint.Constants;
import com.example.informationthematicbackend.common.constaint.ErrorCode;
import com.example.informationthematicbackend.common.enums.UserRole;
import com.example.informationthematicbackend.common.exception.NotFoundException;
import com.example.informationthematicbackend.mapper.UserMapper;
import com.example.informationthematicbackend.model.dto.teacher.TeacherDTO;
import com.example.informationthematicbackend.model.entity.SchoolEntity;
import com.example.informationthematicbackend.model.entity.TeacherClassEntity;
import com.example.informationthematicbackend.model.entity.UserEntity;
import com.example.informationthematicbackend.repository.dsl.TeacherDslRepository;
import com.example.informationthematicbackend.repository.dsl.UserDslRepository;
import com.example.informationthematicbackend.repository.jpa.*;
import com.example.informationthematicbackend.request.teacher.CreateTeacherRequest;
import com.example.informationthematicbackend.request.teacher.ListTeacherRequest;
import com.example.informationthematicbackend.request.user.UpdateUserRequest;
import com.example.informationthematicbackend.response.ErrorResponse;
import com.example.informationthematicbackend.response.NoContentResponse;
import com.example.informationthematicbackend.response.OnlyIdResponse;
import com.example.informationthematicbackend.response.PageResponse;
import com.example.informationthematicbackend.response.teacher.GetTeacherResponse;
import com.example.informationthematicbackend.response.user.ListUserResponse;
import com.example.informationthematicbackend.security.UserPrincipal;
import com.example.informationthematicbackend.service.TeacherService;
import com.example.informationthematicbackend.util.RequestUtil;
import com.example.informationthematicbackend.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    private final UserDslRepository userDslRepository;
    private final UserRepository userRepository;
    private final TeacherClassRepository teacherClassRepository;
    private final TeacherDslRepository teacherDslRepository;
    private final SchoolRepository schoolRepository;
    private final RoleRepository roleRepository;
    private final SubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ListUserResponse getListTeacher(ListTeacherRequest request) {
        request.setClassId(RequestUtil.defaultIfNull(request.getClassId(), (long) -1));
        UserPrincipal principal = SecurityUtils.getPrincipal();
        List<UserEntity> teachers = teacherDslRepository.getListTeacherInSchool(request, principal);
        return ListUserResponse.builder()
                .setPageResponse(PageResponse.builder()
                        .setTotalItems((long) teachers.size())
                        .setPage(request.getPage())
                        .setSize(request.getAll() ? Integer.MAX_VALUE : request.getSize())
                        .setTotalPages(request.getAll() ? 1 : RequestUtil.getTotalPages((long) teachers.size(), request.getSize()))
                        .build())
                .setItems(teachers.stream()
                        .map(UserMapper::entity2dto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public GetTeacherResponse getTeacher(Long teacherId) {
        UserPrincipal principal = SecurityUtils.getPrincipal();
        UserEntity teacher = userRepository.findOneById(teacherId, UserRole.TEACHER_ROLE.getRoleId(), principal.getSchoolId())
                .orElseThrow(() -> new NotFoundException("Not found teacher with id " + teacherId));
        List<TeacherClassEntity> teacherClass = teacherClassRepository.findByTeacher(teacher.getUserId());
        return GetTeacherResponse.builder()
                .setSuccess(true)
                .setTeacher(TeacherDTO.builder()
                        .setTeacher(UserMapper.entity2dto(teacher))
                        .setClasses(teacherClass != null ? teacherClass.stream()
                                .map(tc -> toBuilder(tc))
                                .collect(Collectors.toList()) : Collections.emptyList())
                        .build())
                .build();
    }

    private TeacherDTO.Clazz toBuilder(TeacherClassEntity teacherClass) {
        TeacherDTO.Clazz.ClazzBuilder builder = TeacherDTO.Clazz.builder();
        builder.setClassId(teacherClass.getClazz().getClassId())
                .setClazz(teacherClass.getClazz().getClazz());
        if (teacherClass.getSemester() != null) {
            builder.setSemester(teacherClass.getSemester().getSemester());
        }
        if (teacherClass.getSchoolYear() != null) {
            builder.setSchoolYear(teacherClass.getSchoolYear().getSchoolYear());
        }
        return builder.build();
    }

    @Override
    public OnlyIdResponse createTeacher(CreateTeacherRequest request) {
        Map<String, String> errors = new HashMap<>();
        if (!StringUtils.hasText(request.getFirstName())) {
            errors.put("FirstName", ErrorCode.MISSING_VALUE.name());
        }
        if (!StringUtils.hasText(request.getLastName())) {
            errors.put("LastName", ErrorCode.MISSING_VALUE.name());
        }
        if (!StringUtils.hasText(request.getEmail())) {
            errors.put("Email", ErrorCode.MISSING_VALUE.name());
        }

        UserPrincipal principal = SecurityUtils.getPrincipal();
        Long schoolId = principal.getSchoolId();
        if (StringUtils.hasText(request.getEmail())) {
            Boolean isExistEmailInSchool = userRepository.findByEmailInSchool(request.getEmail(), schoolId).isPresent();
            if (isExistEmailInSchool) {
                errors.put("Email", ErrorCode.ALREADY_EXIST.name());
            }
        }

        if (!errors.isEmpty()) {
            return OnlyIdResponse.builder()
                    .setSuccess(false)
                    .setErrorResponse(ErrorResponse.builder()
                            .setErrors(errors)
                            .build())
                    .build();
        }

        SchoolEntity school = schoolRepository.findById(schoolId).orElseThrow(() -> new NotFoundException("Not found school with id " + schoolId));
        UserEntity teacher = new UserEntity();
        UserEntity lastTeacherInSchool = userDslRepository.getLastStudentTeacherInSchool(school.getSchoolId(), UserRole.TEACHER_ROLE.getRoleId());
        String teacherId = Constants.USERNAME_TEACHER.concat(lastTeacherInSchool != null ?
                String.valueOf(Integer.valueOf(lastTeacherInSchool.getTeacherId().replace(Constants.USERNAME_TEACHER, "")) + 1) : "1");
        String username = teacherId.concat("@").concat(school.getSchool().replace(" ", "").toLowerCase());
        teacher.setTeacherId(teacherId);
        teacher.setUsername(username);
        teacher.setPassword(passwordEncoder.encode(username));
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setSchool(school);
        teacher.setStreet(RequestUtil.blankIfNull(request.getStreet()));
        teacher.setDistrict(RequestUtil.blankIfNull(request.getDistrict()));
        teacher.setCity(RequestUtil.blankIfNull(request.getCity()));
        teacher.setRole(roleRepository.findById(UserRole.TEACHER_ROLE.getRoleId()).get());
        teacher.setWorkingPosition(RequestUtil.blankIfNull(request.getWorkingPosition()));
        if (request.getSubjectId() != null && request.getSubjectId() > -1) {
            teacher.setSubject(subjectRepository.findById(request.getSubjectId())
                    .orElseThrow(() -> new NotFoundException("Not found subject with id " + request.getSubjectId())));
        }
        teacher.setDateOfBirth(LocalDate.parse(request.getDateOfBirth() != null ? request.getDateOfBirth() : Constants.DEFAULT_DATE_OF_BIRTH));
        teacher.setNationality(RequestUtil.blankIfNull(request.getNationality()));
        teacher.setGender(Boolean.TRUE.equals(request.getGender()) ? Boolean.TRUE : Boolean.FALSE);
        userRepository.save(teacher);
        return OnlyIdResponse.builder()
                .setSuccess(true)
                .setId(teacher.getUserId())
                .setName(teacher.getLastName() + " " + teacher.getFirstName())
                .build();
    }

    @Override
    public OnlyIdResponse updateTeacher(Long teacherId, UpdateUserRequest request) {
        UserPrincipal principal = SecurityUtils.getPrincipal();
        UserEntity teacher = userRepository.findOneById(teacherId, UserRole.TEACHER_ROLE.getRoleId(), principal.getSchoolId())
                .orElseThrow(() -> new NotFoundException("Not found teacher with id " + teacherId));
        Map<String, String> errors = new HashMap<>();

        if (!StringUtils.hasText(request.getFirstName())) {
            errors.put("FirstName", ErrorCode.MISSING_VALUE.name());
        }
        if (!StringUtils.hasText(request.getLastName())) {
            errors.put("LastName", ErrorCode.MISSING_VALUE.name());
        }
        if (!StringUtils.hasText(request.getEmail())) {
            errors.put("Email", ErrorCode.MISSING_VALUE.name());
        }
        if (StringUtils.hasText(request.getEmail())) {
            boolean isExistEmail = userRepository.findByEmailInSchool(request.getEmail(), principal.getSchoolId()).isPresent()
                    && !teacher.getEmail().equals(request.getEmail());
            if (isExistEmail) {
                errors.put("Email", ErrorCode.ALREADY_EXIST.name());
            }
        }

        if (!errors.isEmpty()) {
            return OnlyIdResponse.builder()
                    .setSuccess(false)
                    .setErrorResponse(ErrorResponse.builder()
                            .setErrors(errors)
                            .build())
                    .build();
        }

        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setWorkingPosition(RequestUtil.blankIfNull(request.getWorkingPosition()));
        teacher.setStreet(RequestUtil.blankIfNull(request.getStreet()));
        teacher.setDistrict(RequestUtil.blankIfNull(request.getDistrict()));
        teacher.setCity(RequestUtil.blankIfNull(request.getCity()));
        teacher.setPhone(RequestUtil.blankIfNull(request.getPhone()));
        teacher.setRole(roleRepository.findById(request.getRoleId()).get());
        teacher.setDateOfBirth(LocalDate.parse(request.getDateOfBirth() != null ? request.getDateOfBirth() : Constants.DEFAULT_DATE_OF_BIRTH));
        teacher.setNationality(RequestUtil.blankIfNull(request.getNationality()));
        teacher.setGender(Boolean.TRUE.equals(request.getGender()) ? Boolean.TRUE : Boolean.FALSE);
        if (request.getSubjectId() != null && request.getSubjectId() > -1) {
            teacher.setSubject(subjectRepository.findById(request.getSubjectId())
                    .orElseThrow(() -> new NotFoundException("Not found subject with id " + request.getSubjectId())));
        }
        userRepository.save(teacher);
        return OnlyIdResponse.builder()
                .setSuccess(true)
                .setId(teacher.getUserId())
                .setName(teacher.getLastName() + " " + teacher.getLastName())
                .build();
    }

    @Override
    public NoContentResponse deleteTeacher(Long teacherId) {
        UserPrincipal principal = SecurityUtils.getPrincipal();
        UserEntity teacher = userRepository.findOneById(teacherId, UserRole.TEACHER_ROLE.getRoleId(), principal.getSchoolId())
                .orElseThrow(() -> new NotFoundException("Not found teacher with id " + teacherId));

        userRepository.delete(teacher);
        return NoContentResponse.builder()
                .setSuccess(true)
                .build();
    }
}