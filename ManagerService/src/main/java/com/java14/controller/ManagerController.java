package com.java14.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.java14.constants.EndPoints.MANAGER;

@RestController
@RequestMapping(MANAGER)
@RequiredArgsConstructor
public class ManagerController {
}
