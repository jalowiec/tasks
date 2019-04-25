package com.crud.tasks.domain;




import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String content;
}
