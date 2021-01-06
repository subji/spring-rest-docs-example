package io.subji.examplesprintrestdocs.restdocs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.subji.examplesprintrestdocs.restdocs.dto.RestDocsTest;
import io.subji.examplesprintrestdocs.restdocs.service.RestDocsTestService;

@RestController
@RequestMapping("/test")
public class RestDocsRestController {

  private final RestDocsTestService restDocsTestService;

  public RestDocsRestController(RestDocsTestService restDocsTestService)  {
    this.restDocsTestService = restDocsTestService;
  }
  
  @GetMapping(value = { "" })
  public ResponseEntity<RestDocsTest> readRestDocsTest(@RequestBody(required = false) RestDocsTest restDocsTest)  {
    ResponseEntity<RestDocsTest> result = restDocsTestService.readContent(restDocsTest);
    return result;
  }

}
