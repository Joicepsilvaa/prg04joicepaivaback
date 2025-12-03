package br.com.ifba.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ObjectMapperUtil {

    private final ModelMapper mapper = new ModelMapper();

    public <S, T> T map(S source, Class<T> target) {
        return mapper.map(source, target);
    }

    public <S, T> List<T> mapAll(List<S> source, Class<T> target) {
        return source.stream()
                .map(element -> mapper.map(element, target))
                .collect(Collectors.toList());
    }
}
