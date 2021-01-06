package io.subji.examplesprintrestdocs.restdocs.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.subji.examplesprintrestdocs.restdocs.dto.RestDocsTest;

@Service
public class RestDocsTestService {
  
  public ResponseEntity<RestDocsTest> readContent(RestDocsTest restDocsTest) {
    RestDocsTest result = new RestDocsTest();
    
    try {
      result.setId("ID");
      result.setName("Name");
      result.setBody("Body");

      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
  }

}
