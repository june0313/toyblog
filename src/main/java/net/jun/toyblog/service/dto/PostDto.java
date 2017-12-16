package net.jun.toyblog.service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	private Long id;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
}
