package com.teamtreehouse.service.resttemplate;

import com.teamtreehouse.service.dto.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static com.teamtreehouse.util.WebUtils.uriEncode;

public abstract class RestApiService<T extends Dto> {
    @Autowired
    private RestTemplate restTemplate;

    protected RequestBuilder get(String uriTemplate) {
        return new RequestBuilder(uriTemplate);
    }

    public abstract String getHost();
    public abstract String getApiKey();
    public abstract Class<T> getDtoClass();

    public class RequestBuilder {
        private String uriTemplate;
        private Map<String,String> params = new HashMap<>();
        private boolean secure = true;

        protected RequestBuilder(String uriTemplate) {
            this.uriTemplate = uriTemplate;
        }

        public RequestBuilder param(String key, Object value) {
            params.put(key,uriEncode("" + value));
            return this;
        }

        public RequestBuilder withHttps() {
            secure = true;
            return this;
        }

        public RequestBuilder withHttp() {
            secure = false;
            return this;
        }

        public T execute() {
            params.put("key",getApiKey());

            UriComponents uriComponents = UriComponentsBuilder.newInstance()
                    .scheme(secure ? "https" : "http")
                    .host(getHost())
                    .path(uriTemplate)
                    .build()
                    .expand(params);
            String url = uriComponents.toUriString();
            return restTemplate.getForObject(url,getDtoClass());
        }
    }
}
