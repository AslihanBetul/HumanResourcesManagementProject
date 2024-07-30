package com.java14.controller;

import com.java14.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.java14.constant.EndPoints.COMMENT;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

}
