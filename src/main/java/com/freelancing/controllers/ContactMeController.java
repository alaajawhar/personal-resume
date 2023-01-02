package com.freelancing.controllers;

import com.freelancing.constants.URIConstants;
import com.freelancing.models.ContactMeRequest;
import com.freelancing.services.ContactMeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Alaa jawhar
 */
@RestController
@Slf4j
@Validated
public class ContactMeController {

    @Autowired
    private ContactMeService contactMeService;

    @RequestMapping(value = URIConstants.CONTACT_ME, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> contactMe(@RequestBody @Valid ContactMeRequest contactMeRequest) throws Exception {
        return new ResponseEntity<>(contactMeService.run(contactMeRequest), HttpStatus.OK);
    }

}
