package org.gfg.musicapplication.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongAdditionResponse {
    private String songPath;
    private Integer songId;
    private String message;

}
