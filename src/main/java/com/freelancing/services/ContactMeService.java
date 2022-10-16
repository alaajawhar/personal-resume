package com.freelancing.services;

import com.freelancing.constants.URIConstants;
import com.freelancing.models.ContactMeRequest;
import com.freelancing.models.base.BaseValidation;
import com.freelancing.models.base.SuccessResponse;
import com.freelancing.models.dtos.BrowserIdMap;
import com.freelancing.models.dtos.EmailMap;
import com.freelancing.models.dtos.RequestKeyDto;
import com.freelancing.models.dtos.RequestKeyMap;
import com.freelancing.services.interfaces.IContactMeService;
import com.freelancing.services.interfaces.IEmailService;
import com.freelancing.utils.AESUtil;
import com.freelancing.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author Alaa Jawhar
 */
@Service
@Slf4j
public class ContactMeService extends ServiceManager<ContactMeRequest, SuccessResponse> implements IContactMeService {

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private IEmailService emailService;


    @Value("${email.max.count}")
    private int maxCountPerEmail;
    @Value("${browser.id.max.count}")
    private int maxRequestPerBrowserId;
    @Value("${ip.max.count}")
    private boolean isBlockById;

    @Override
    public BaseValidation validate(ContactMeRequest contactMeRequest) {
        BaseValidation baseValidation = new BaseValidation();

        String clearXpathHeader = httpServletRequest.getHeader(URIConstants.X_PATH_HEADER);
        if (RequestKeyMap.isExists(clearXpathHeader)) {
            log.debug("Relay attack");
            baseValidation.setIsError(Boolean.TRUE);
            baseValidation.setErrorMessage("Request already processed");
            return baseValidation;
        }

        Optional<String> decryptedXPath = AESUtil.decrypt(clearXpathHeader);
        log.debug("decryptedXPath: {}", decryptedXPath.get());

        if (decryptedXPath.isPresent() == Boolean.FALSE) {
            log.debug("Cannot decrypt request");
            baseValidation.setIsError(Boolean.TRUE);
            baseValidation.setErrorMessage("Decryption failure");
            return baseValidation;

        }

        RequestKeyDto requestKeyDto = JsonUtil.parseJsonToObject(decryptedXPath.get(), RequestKeyDto.class);

        EmailMap.put(contactMeRequest.getEmail());
        if (EmailMap.get(contactMeRequest.getEmail()) > maxCountPerEmail) {
            log.debug("Max count for email");
            baseValidation.setIsError(Boolean.TRUE);
            baseValidation.setErrorMessage("Maximum number per email has been reached, please wait for me to reach you");
            return baseValidation;
        }


        String ipAddress = httpServletRequest.getRemoteAddr();
        BrowserIdMap.put(ipAddress);
        if (isBlockById && BrowserIdMap.get(ipAddress) > maxRequestPerBrowserId) {
            log.debug("Max count per IP has been reached for IP: {}", ipAddress);
            baseValidation.setIsError(Boolean.TRUE);
            baseValidation.setErrorMessage("Sorry you cannot send more emails, please wait for me to reach you");
            return baseValidation;
        }

//        BrowserIdMap.put(requestKeyDto.getBrowserId());
//        if (BrowserIdMap.get(requestKeyDto.getBrowserId()) > maxRequestPerBrowserId) {
//            baseValidation.setIsError(Boolean.TRUE);
//            baseValidation.setErrorMessage("Sorry you cannot send more emails, please wait for me to reach you");
//            return baseValidation;
//        }

        log.debug(decryptedXPath.get());
        return baseValidation;
    }

    @Override
    public SuccessResponse execute(ContactMeRequest contactMeRequest) {
        String subject = contactMeRequest.getName() + " is contacting me | Personal resume";
        String Text =
                "Someone looked at your personal-resume website and sent a message to you\n" +
                        "email: " + contactMeRequest.getEmail() + "\n" +
                        "name: " + contactMeRequest.getName() + "\n" +
                        "subject: " + contactMeRequest.getSubject() + "\n" +
                        "message: " + contactMeRequest.getMessage() + "\n";

        emailService.sendSimpleMessage("alaa.jawhar.eng@gmail.com", subject, Text);

        log.debug("Returning Success Response");
        return new SuccessResponse("Successfully Saved");
    }

}
