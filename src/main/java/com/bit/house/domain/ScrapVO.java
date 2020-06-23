package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ScrapVO extends PhotoBoardVO{
    private String memberId;
    private int photoBoardNo;
}
