package dev.jjk.portfoliogallery.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//secret 설정파일 사용을 위해 생성한 클래스
@Configuration
@PropertySource("classpath:secrets.properties")
public class Properties {
}
