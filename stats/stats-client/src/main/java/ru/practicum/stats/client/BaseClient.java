package ru.practicum.stats.client;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class BaseClient {
    protected final RestTemplate restTemplate;

    protected <TResponse> ResponseEntity<TResponse> get(
        final String path,
        final Map<String, Object> parameters,
        final Class<TResponse> responseType) throws HttpStatusCodeException {

        return makeAndSendRequest(HttpMethod.GET, path, parameters, null, responseType);
    }

    protected <TRequest, TResponse> ResponseEntity<TResponse> post(
        final String path,
        final TResponse body,
        final Class<TResponse> responseType) throws HttpStatusCodeException {

        return makeAndSendRequest(HttpMethod.POST, path, null, body, responseType);
    }

    private <TRequest, TResponse> ResponseEntity<TResponse> makeAndSendRequest(
        final HttpMethod method,
        final String path,
        @Nullable final Map<String, Object> parameters,
        @Nullable final TRequest body,
        final Class<TResponse> responseType) throws HttpStatusCodeException {

        final HttpEntity<TRequest> requestEntity = new HttpEntity<>(body, defaultHeaders());

        return parameters != null
            ? restTemplate.exchange(path, method, requestEntity, responseType, parameters)
            : restTemplate.exchange(path, method, requestEntity, responseType);
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }
}
