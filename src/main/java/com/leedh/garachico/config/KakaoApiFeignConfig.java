package com.leedh.garachico.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.httpclient.ApacheHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 설명: 페인을 이용한 카카오 API 연결
 * Project: garachico
 * CLASS: KakaoApiFeignConfig
 * User: 이동훈
 * Date: 2018-07-07
 */
@Slf4j
@Configuration
public class KakaoApiFeignConfig {

    private final int readTimeout;
    private final int connTimeout;
    private final Gson gson;
    private final Environment env;

    @Value("${kakao.api.key:}")
    private String kakaoApiKey;

    @Autowired
    public KakaoApiFeignConfig(Environment env) {

        /* 이름 정책을 변경하기 위해 빌더 이용*/
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

        this.gson = gsonBuilder.create();
        this.env = env;
        this.readTimeout = Integer.parseInt(env.getProperty("api.read.timeout", "100000"));
        this.connTimeout = Integer.parseInt(env.getProperty("api.connect.timeout", "3000"));
    }

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .client(client());
    }

    @Bean
    public Client client() {

        final HttpClientBuilder builder = HttpClientBuilder.create()
                .setMaxConnPerRoute(300)
                .setMaxConnTotal(300)
                .addInterceptorFirst((HttpRequestInterceptor) (request, context) -> {
                    /*인증키 삽입: 해당키 삽입이 필수 이다. */
                    request.addHeader("Authorization", kakaoApiKey);
                });

        /* 테스트 이므로 압축하지 않는다. */
        builder.disableContentCompression();


        return new ApacheHttpClient(builder.build());
    }

    @Bean
    public Decoder decoder() {
        return new GsonDecoder(gson);
    }

    @Bean
    public Encoder encoder() {
        return new GsonEncoder(gson);
    }

    @Bean
    public Request.Options options() {
        log.debug("KakaoApiFeignConfig connTimeout: {}, readTimeout: {}", connTimeout, readTimeout);
        return new Request.Options(connTimeout, readTimeout);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
