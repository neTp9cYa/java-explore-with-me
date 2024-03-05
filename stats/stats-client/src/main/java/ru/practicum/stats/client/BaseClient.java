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

    protected <ResponseT> ResponseEntity<ResponseT> get(
        final String path,
        final Map<String, Object> parameters,
        final Class<ResponseT> responseType) throws HttpStatusCodeException {

        return makeAndSendRequest(HttpMethod.GET, path, parameters, null, responseType);
    }

    protected <RequestT, ResponseT> ResponseEntity<ResponseT> post(
        final String path,
        final ResponseT body,
        final Class<ResponseT> responseType) throws HttpStatusCodeException {

        return makeAndSendRequest(HttpMethod.POST, path, null, body, responseType);
    }

    private <RequestT, ResponseT> ResponseEntity<ResponseT> makeAndSendRequest(
        final HttpMethod method,
        final String path,
        @Nullable final Map<String, Object> parameters,
        @Nullable final RequestT body,
        final Class<ResponseT> responseType) throws HttpStatusCodeException {

        final HttpEntity<RequestT> requestEntity = new HttpEntity<>(body, defaultHeaders());

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
