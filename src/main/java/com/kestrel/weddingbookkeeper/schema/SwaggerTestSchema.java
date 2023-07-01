package com.kestrel.weddingbookkeeper.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Tutorial Model Information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerTestSchema {

        @Schema(description = "유저명", example = "홍길동")
        private String userName;

        @Schema(description = "나이", example = "40")
        private int age;

}
