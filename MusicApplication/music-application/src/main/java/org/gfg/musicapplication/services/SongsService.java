package org.gfg.musicapplication.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.gfg.musicapplication.model.Songs;
import org.gfg.musicapplication.model.User;
import org.gfg.musicapplication.repositories.SongCacheRepository;
import org.gfg.musicapplication.repositories.SongsRepository;
import org.gfg.musicapplication.repositories.UserCacheRepository;
import org.gfg.musicapplication.repositories.UserRepository;
import org.gfg.musicapplication.requests.SongAdditionRequest;
import org.gfg.musicapplication.response.GenericMessages;
import org.gfg.musicapplication.response.SongAdditionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SongsService {

    @Autowired
    private SongsRepository songsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongCacheRepository songCacheRepository;

    @Autowired
    AmazonS3 amazonS3;

    @Autowired
    private UserCacheRepository userCacheRepository;
    public SongAdditionResponse songAddition(SongAdditionRequest songAdditionRequest) {
        if(songsRepository.findBySongTitle(songAdditionRequest.getSongTitle()) != null){
            return SongAdditionResponse.builder().message(GenericMessages.ENTRYALREADYPRESENT.getMessage()).build();
        }
        Songs songInDb = songAdditionRequest.toSong();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =  (User) authentication.getPrincipal();
        User userFromDb = null;
        if(userCacheRepository.get(user.getMail()) != null){
            userFromDb = userCacheRepository.get(user.getMail());
        }else {
            userFromDb = userRepository.findByMail(user.getMail());
        }

        if(userFromDb != null){
            songInDb.setCreatedBy(userFromDb);
        }else{
            return SongAdditionResponse.builder().message(GenericMessages.USERNOTPRESENTTOCREATESONG.getMessage()).build();
        }
        songCacheRepository.set(songInDb);
        try{
            songInDb = songsRepository.save(songInDb);
            String path=uploadFileToAmazonS3(songAdditionRequest.getFile());
            songInDb.setFilePath(path);
        }catch (Exception e){
            e.printStackTrace();
            return SongAdditionResponse.builder().message(GenericMessages.SONGADDITIONFAILURE.getMessage()).build();
        }
        return SongAdditionResponse.builder().
                message(GenericMessages.SONGADDITIONSUCCESS.getMessage()).
                songId(songInDb.getPk()).
                songPath("").
                build();
    }

    private String uploadFileToAmazonS3(String file) {
       String bucket="musicapplication";
       String key=file;
        PutObjectResult p = amazonS3.putObject("musicapplication","file",new File(file));
        return bucket+";"+key;
    }
}
