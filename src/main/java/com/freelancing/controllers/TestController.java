package com.freelancing.controllers;

import com.freelancing.models.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Alaa jawhar
 */
@RestController
@Slf4j
public class TestController {

    @RequestMapping(value = "/contact-me/submit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test(@RequestBody Map<String, String> testRequest) throws Exception {
        log.info("RequestBody: {}", testRequest);
        return new ResponseEntity<>(new SuccessResponse("Successfully Saved"), HttpStatus.OK);
    }

}
