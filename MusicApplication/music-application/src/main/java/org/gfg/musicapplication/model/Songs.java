package org.gfg.musicapplication.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Songs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer pk;

    @Column(unique = true, nullable = false)
    private String songTitle;

    @Enumerated(value = EnumType.ORDINAL)
    private SongCategory songCategory;

    private String filePath;

    private boolean active;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne
    @JoinColumn
    private User createdBy;

    private String songDescription;

    private Integer songRating;


}
