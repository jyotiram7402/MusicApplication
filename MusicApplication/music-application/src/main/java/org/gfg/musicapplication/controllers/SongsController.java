package org.gfg.musicapplication.controllers;

import org.gfg.musicapplication.requests.SongAdditionRequest;
import org.gfg.musicapplication.response.GenericMessages;
import org.gfg.musicapplication.response.GenericResponse;
import org.gfg.musicapplication.response.SongAdditionResponse;
import org.gfg.musicapplication.services.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/songs/")
public class SongsController {
    @Autowired
    private SongsService songsService;

    @PostMapping("/addition/")
    public GenericResponse<Object> songAddition(@Valid @RequestBody SongAdditionRequest songAdditionRequest){
    SongAdditionResponse songAdditionResponse = songsService.songAddition(songAdditionRequest);
        if(songAdditionResponse.getMessage().toString().equalsIgnoreCase(GenericMessages.SONGADDITIONSUCCESS.getMessage())) {
            return GenericResponse.builder().
                    code(HttpStatus.OK.value()).
                    message(GenericMessages.SONGADDITIONSUCCESS.getMessage()).
                    statusCode(0).
                    data(songAdditionResponse).build();
        }

        else if (songAdditionResponse.getMessage().equalsIgnoreCase(GenericMessages.ENTRYALREADYPRESENT.getMessage())) {
            return GenericResponse.builder().
                    code(HttpStatus.OK.value()).
                    message(GenericMessages.ENTRYALREADYPRESENT.getMessage()).
                    statusCode(1).
                    data(songAdditionResponse).build();

        }
        else if (songAdditionResponse.getMessage().equalsIgnoreCase(GenericMessages.USERNOTPRESENTTOCREATESONG.getMessage())) {
            return GenericResponse.builder().
                    code(HttpStatus.OK.value()).
                    message(GenericMessages.USERNOTPRESENTTOCREATESONG.getMessage()).
                    statusCode(1).
                    data(songAdditionResponse).build();
        }
        return GenericResponse.builder().
                code(HttpStatus.INTERNAL_SERVER_ERROR.value()).
                message(GenericMessages.SONGADDITIONFAILURE.getMessage()).
                statusCode(1).
                data(null).
                build();
    }
}
