package org.gfg.musicapplication.requests;

import lombok.*;
import org.gfg.musicapplication.model.SongCategory;
import org.gfg.musicapplication.model.Songs;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongAdditionRequest {

    private String songTitle;
    private SongCategory songCategory;
    private String file;
    private String songDescription;

    public Songs toSong() {
        return Songs.builder().
                songTitle(this.songTitle).
                songCategory(this.songCategory).
                songDescription(this.songDescription).
                active(true).
                build();
    }
}
