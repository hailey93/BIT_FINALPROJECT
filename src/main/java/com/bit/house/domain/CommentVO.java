package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
public class CommentVO {
    private String memberId;
    private Date upDate;
    private String commentContent;
    private int commentGroupNo;
    private int commentIndent;
    private int commentStep;
    private int photoBoardNo;
    private int askBoardNo;
}
