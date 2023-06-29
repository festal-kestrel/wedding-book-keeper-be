package com.kestrel.weddingbookkeeper.api.member.vo;

import com.kestrel.weddingbookkeeper.api.date.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member extends BaseEntity {

    private Integer memberId;
    private String email;
    private String name;
    private Gender gender;
}
