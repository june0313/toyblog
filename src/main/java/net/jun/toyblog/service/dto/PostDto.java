package net.jun.toyblog.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PostDto {
	private String title;
	private String content;
	private String writer;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
}
