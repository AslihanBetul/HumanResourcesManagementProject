package com.java14.service;

import com.java14.dto.request.CommentRequestDto;
import com.java14.dto.request.CommentUpdateRequestDto;
import com.java14.dto.response.CommentManagerResponseDto;
import com.java14.dto.response.CommentResponseDto;
import com.java14.dto.response.CompanyResponseDto;
import com.java14.dto.response.ManagerResponseDto;
import com.java14.entity.Comment;
import com.java14.exception.CommentServiceException;
import com.java14.exception.ErrorType;
import com.java14.manager.CompanyManager;
import com.java14.manager.ManagerManager;
import com.java14.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private  final ManagerManager managerManager;
    private  final CompanyManager companyManager;


    public Boolean saveComment(CommentRequestDto dto) {
        String managerId = managerManager.getManagerIdFindByToken(dto.getToken());
        CompanyResponseDto companyResponseDto =companyManager.findManagerById(managerId);

        Comment comment = Comment.builder()
                .comment(dto.getComment())
                .managerId(managerId)
                .companyId(companyResponseDto.getId())
                .rate(dto.getRate())
                .build();
        if (commentRepository.findByCompanyId(companyResponseDto.getId()).isEmpty()) {
            commentRepository.save(comment);
            return true;
        }else {
            return false;
        }


    }

    public CommentResponseDto getComment(String token) {
        String managerID = managerManager.getManagerIdFindByToken(token);
        Comment comment = commentRepository.findByManagerId(managerID);

        if (comment == null) {
            return CommentResponseDto.builder()
                    .id("")
                    .comment("")
                    .rate(0)
                    .build();
        }

        return CommentResponseDto.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .rate(comment.getRate())
                .build();
    }
    public Boolean updateComment(CommentUpdateRequestDto dto) {
        String managerId = managerManager.getManagerIdFindByToken(dto.getToken());
        Comment comment = commentRepository.findByManagerId(managerId);

        if (comment == null) {
            return false;
        }

        comment.setComment(dto.getComment() != null ? dto.getComment() : "");
        comment.setRate(dto.getRate() != null ? dto.getRate() : 0);
        commentRepository.save(comment);
        return true;
    }



    public Boolean deleteComment(String token) {
        String managerId = managerManager.getManagerIdFindByToken(token);
        Comment comment = commentRepository.findByManagerId(managerId);
        commentRepository.delete(comment);
        return true;
    }

    public List<CommentManagerResponseDto> getCommentList(){
        List<Comment> comments = commentRepository.findAll();
        List<CommentManagerResponseDto> commentList=new ArrayList<>();
        comments.forEach(comment -> {
          CompanyResponseDto companyResponseDto=  companyManager.findManagerById(comment.getManagerId());
          ManagerResponseDto managerResponseDto =managerManager.getManagerId(comment.getManagerId());
            CommentManagerResponseDto commentManagerResponseDto = CommentManagerResponseDto.builder()
                    .comment(comment.getComment() != null ? comment.getComment() : "")
                    .managerName(managerResponseDto.getName() != null ? managerResponseDto.getName() : "")
                    .managerSurname(managerResponseDto.getSurname() != null ? managerResponseDto.getSurname() : "")
                    .ManagerAvatar(managerResponseDto.getAvatar() != null ? managerResponseDto.getAvatar() : "")
                    .companyName(companyResponseDto.getName() != null ? companyResponseDto.getName() : "")
                    .companyLogo(companyResponseDto.getLogo() != null ? companyResponseDto.getLogo() : "")
                    .sector(companyResponseDto.getSector() != null ? companyResponseDto.getSector() : "")
                    .build();
          commentList.add(commentManagerResponseDto);
        });
        return commentList;

    }
}
