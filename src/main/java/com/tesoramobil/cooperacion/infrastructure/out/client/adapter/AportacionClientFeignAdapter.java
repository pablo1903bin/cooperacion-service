package com.tesoramobil.cooperacion.infrastructure.out.client.adapter;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tesoramobil.cooperacion.domain.client.AportacionClientPort;
import com.tesoramobil.cooperacion.infrastructure.out.client.feign.AportacionClient;
import com.tesoramobil.cooperacion.infrastructure.out.client.feign.AportacionClientDto;

@Component
public class AportacionClientFeignAdapter implements AportacionClientPort {

    final private AportacionClient feign;

    public AportacionClientFeignAdapter(AportacionClient aportacionClient) {
        this.feign = aportacionClient;
    }

    @Override
    public BigDecimal obtenerPorCooperacion(Long cooperacionId) {

        var resp = feign.obtenerPorCooperacion(cooperacionId);


        List<AportacionClientDto> data = (resp == null) ? null : resp.data();

        if (data == null || data.isEmpty())
            return BigDecimal.ZERO;

        return data.stream()
                .map(dto -> toBigDecimal(dto.getMonto())) // <-- usa getMonto()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static BigDecimal toBigDecimal(Double val) {
        return (val == null) ? BigDecimal.ZERO : BigDecimal.valueOf(val);
    }
}