package com.example.demo.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sqlparam")
@Setter
@Getter
@ToString
public class SqlParam {
    private String findAll;
    private String delete;
    private String save;
    private String findById;
    private String update;
    private String count;
}
