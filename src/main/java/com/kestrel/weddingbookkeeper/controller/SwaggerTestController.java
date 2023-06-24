package com.kestrel.weddingbookkeeper.controller;

import com.kestrel.weddingbookkeeper.schema.SwaggerTestSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TestAPI", description = "Tutorial management APIs")
@RestController
@RequestMapping("/api/v1")
public class SwaggerTestController {

    @Operation(summary = "테스트 1", description = "첫번째 엔드포인트", tags = {"TestAPI"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/test1")
    public ResponseEntity<Boolean> getTest1() {
        return ResponseEntity.ok(true);
    }

    @Operation(summary = "테스트 2", description = "두번째 엔드포인트", tags = {"TestAPI"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!",  content = { @Content(schema = @Schema(implementation = SwaggerTestSchema.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @Parameters({
            @Parameter(name = "userName", description = "이름 파라미터", required = true),
            @Parameter(name = "age", description = "나이 파라미터", required = true)
    })
    @GetMapping("/test2")
    public ResponseEntity<SwaggerTestSchema> getTest2(
            @RequestParam(defaultValue = "홍길동") String userName, @RequestParam(defaultValue = "20") int age) {
        SwaggerTestSchema obj = new SwaggerTestSchema(userName, age);
        return ResponseEntity.ok(obj);
    }
}
