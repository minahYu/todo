package com.sparta.todo.dto;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoRequestDto {
    private String title;
    private String contents;
    private Boolean complete;

    @Builder
    public TodoRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
