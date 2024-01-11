package com.socialmedia.coreapi.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Tag(name = "Authentication", description = "Authentication for core api")
@Controller
class PseudoProxyAuthController {

    @Operation(summary = "Get token")
    @PostMapping("realms/socialmedia/protocol/openid-connect/token")
    @RequestMapping(method = RequestMethod.POST,
            value = "realms/socialmedia/protocol/openid-connect/token",
            produces = "application/json",
            consumes = "application/x-www-form-urlencoded"
    )
    ResponseEntity<String> getToken(@RequestBody AuthDataModel authDataModel) {
        ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("Use this endpoint only with authentication server url")
    }

    class AuthDataModel {
        String grant_type
        String client_id
        String username
        String password
    }
}
