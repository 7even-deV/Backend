package com.bosonit.sa2.domain;

import com.bosonit.sa2.FileSequenceIdGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name = "Files")
@Table(name = "Files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fileId")
    @GenericGenerator(name = "fileId", strategy = "com.bosonit.sa2.FileSequenceIdGenerator", parameters = {
            @Parameter(name = FileSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = FileSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "f"),
            @Parameter(name = FileSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
    })
    private String fileId;

    @Column(name = "name")
    private String fileName;

    @Column(name = "type")
    private String fileType;

    @Column(name = "uploadDate")
    private Date uploadDate;

    @Lob
    private byte[] fileData;
}
