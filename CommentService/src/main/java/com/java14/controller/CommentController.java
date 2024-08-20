package com.java14.controller;

import com.java14.dto.request.CommentRequestDto;
import com.java14.dto.request.CommentUpdateRequestDto;
import com.java14.dto.response.CommentResponseDto;
import com.java14.dto.response.CompanyResponseDto;
import com.java14.dto.response.ResponseDto;
import com.java14.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.java14.constant.EndPoints.COMMENT;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/save-comment")
    public ResponseEntity<ResponseDto<Boolean>> saveComment(@RequestBody CommentRequestDto dto){
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(commentService.saveComment(dto))
                .code(200)
                .message("Succesfully saved")
                .build());
    }

    @GetMapping("/get-comment")
    public ResponseEntity<ResponseDto<CommentResponseDto>> getComment(@RequestParam String token){
        return ResponseEntity.ok(ResponseDto.<CommentResponseDto>builder()
                .data(commentService.getComment(token))
                .code(200)
                .message("Succesfully saved")
                .build());
    }

    @PostMapping("/update-comment")
    public ResponseEntity<ResponseDto<Boolean>> updateComment(@RequestBody CommentUpdateRequestDto dto){
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(commentService.updateComment(dto))
                .code(200)
                .message("Succesfully saved")
                .build());
    }
    @GetMapping("/delete-comment")
    public ResponseEntity<ResponseDto<Boolean>> deleteComment(@RequestParam String token){
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                .data(commentService.deleteComment(token))
                .code(200)
                .message("Succesfully saved")
                .build());
    }

}
